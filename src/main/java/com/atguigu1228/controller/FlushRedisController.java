package com.atguigu1228.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu1228.bean.Datas;
import com.atguigu1228.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu1228.bean.OBJECT_T_MALL_ATTR;
import com.atguigu1228.bean.OBJECT_T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.bean.T_MALL_VALUE;
import com.atguigu1228.service.AttrServiceInf;
import com.atguigu1228.service.FlushRedisService;
import com.atguigu1228.utils.FlushRedisUtil;
import com.atguigu1228.utils.MyJedisConnectionUtil;
import com.atguigu1228.utils.MyJsonUtil;

import redis.clients.jedis.Jedis;

@Controller
public class FlushRedisController {
	@Autowired
	AttrServiceInf attrServiceImp;
	@Autowired
	FlushRedisService flushRedisService;

	@RequestMapping("goto_search_redis")
	public String goto_search_redis() {

		return "manager_search_redis";
	}

	@ResponseBody
	@RequestMapping(value = "get_attr_by_class_2_id_json", method = RequestMethod.POST)
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id_json(
			int class_2_id) {

		List<OBJECT_T_MALL_ATTR> list_object_attr_value = attrServiceImp
				.get_attr_list_by_class_2_id(class_2_id);

		return list_object_attr_value;
	}

	@ResponseBody
	@RequestMapping("get_sku_by_class_2")
	public int get_sku_by_class_2(int class_2_id) {

		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("class_2_id", class_2_id);

		paramMap.put("pp_id", 0);
		List<OBJECT_T_MALL_SKU> list_object_sku = flushRedisService
				.get_sku_by_class_2_id(paramMap);

		// redis按2级分类id刷新sku缓存

		FlushRedisUtil.flush_Redis_by_class_2_id(list_object_sku, class_2_id);

		return list_object_sku.size();

	}

	@ResponseBody
	@RequestMapping("get_sku_list_by_class_2_attr_value")
	public int get_sku_list_by_class_2_attr_value(int class_2_id, Datas datas) {

		int cnt = 0;
		Jedis jedis = MyJedisConnectionUtil.getJedis();

		if (datas.getAttrid() != null && datas.getAttrid().size() != 0) {

			for (Integer i : datas.getAttrid()) {

				List<T_MALL_VALUE> value_by_attr_id = attrServiceImp
						.get_value_by_attr_id(i);
				if (value_by_attr_id != null && value_by_attr_id.size() != 0) {

					for (T_MALL_VALUE t_MALL_VALUE : value_by_attr_id) {

						List<T_MALL_SKU_ATTR_VALUE> list_attr_value = new ArrayList<T_MALL_SKU_ATTR_VALUE>();

						T_MALL_SKU_ATTR_VALUE l = new T_MALL_SKU_ATTR_VALUE();

						l.setShxm_id(i);
						l.setShxzh_id(t_MALL_VALUE.getId());

						list_attr_value.add(l);

						List<OBJECT_T_MALL_SKU> get_sku_by_class_2_attr_value = flushRedisService
								.get_sku_by_class_2_attr_value(class_2_id, 0,
										list_attr_value);

						// redis按2级分类id属性名id属性值id刷新sku缓存

						String key = "attr_value" + "_" + class_2_id + "_" + i
								+ "_" + t_MALL_VALUE.getId();
						//刷新2级分类id属性名id属性值idsku缓存时先清理对应的key
						jedis.del(key);

						if (get_sku_by_class_2_attr_value.size() != 0
								&& get_sku_by_class_2_attr_value != null) {
							for (int j = 0; j < get_sku_by_class_2_attr_value
									.size(); j++) {

								String str = MyJsonUtil.object_to_json_str(
										get_sku_by_class_2_attr_value.get(j));

								jedis.zadd(key, j, str);
								cnt++;
							}

						}
					}
				}
			}
		}
		return cnt;
	}
}

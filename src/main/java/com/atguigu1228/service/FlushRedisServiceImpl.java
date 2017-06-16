package com.atguigu1228.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu1228.bean.OBJECT_T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.mapper.FlushRedisMapper;

@Service
public class FlushRedisServiceImpl implements FlushRedisService{
	
	@Autowired
	FlushRedisMapper flushRedisMapper;
	
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id(Map<String, Object> paramMap) {

		return flushRedisMapper.query_sku_page_by_class_2_id(paramMap);
	}

	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_attr_value(int class_2_id, int pp_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		StringBuffer sbf = new StringBuffer();

		sbf.append(" and sku.id in ");
		sbf.append(" ( ");
		sbf.append(" select sku_0.sku_id from ");
		for (int i = 0; i < list_attr_value.size(); i++) {
			sbf.append(
					" (Select sku_id from t_mall_sku_attr_value where shxm_id =" + list_attr_value.get(i).getShxm_id()
							+ " and shxzh_id=" + list_attr_value.get(i).getShxzh_id() + ") sku_" + i + " ");
			if (i < (list_attr_value.size() - 1)) {
				sbf.append(" , ");
			}
		}

		if (list_attr_value.size() > 1) {
			sbf.append(" where ");
		}

		for (int i = 0; i < list_attr_value.size(); i++) {
			if (i < (list_attr_value.size() - 1)) {
				sbf.append("sku_" + i + ".sku_id=sku_" + (i + 1) + ".sku_id");
			}

			if (i < (list_attr_value.size() - 2)) {
				sbf.append(" and ");
			}
		}
		sbf.append(" ) ");

		paramMap.put("sql", sbf.toString());

		paramMap.put("pp_id", pp_id);

		paramMap.put("class_2_id", class_2_id);

		return flushRedisMapper.query_sku_page_by_class_2_id_attr_value(paramMap);
	}


}

package com.atguigu1228.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu1228.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.bean.OBJECT_T_MALL_ATTR;
import com.atguigu1228.bean.T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_VALUE;
import com.atguigu1228.mapper.AttrMapper;
import com.atguigu1228.mapper.FlushRedisMapper;

@Service
public class AttrServiceImp implements AttrServiceInf {

	@Autowired
	AttrMapper attrMapper;
	@Autowired
	FlushRedisMapper flushRedisMapper;

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_list_by_class_2_id(int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_attr_value = attrMapper.select_attr_list_by_class_2_id(class_2_id);
		return list_attr_value;
	}

	public void add_attr(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id) {

		for (int i = 0; i < list_attr.size(); i++) {
			OBJECT_T_MALL_ATTR object_T_MALL_ATTR = list_attr.get(i);
			object_T_MALL_ATTR.setFlbh2(class_2_id);
			attrMapper.insert_attr(object_T_MALL_ATTR);

			HashMap<String, Object> hashMap = new HashMap<String, Object>();

			hashMap.put("shxm_id", object_T_MALL_ATTR.getId());
			hashMap.put("class_2_id", class_2_id);
			hashMap.put("list_value", object_T_MALL_ATTR.getList_value());
			attrMapper.insert_values(hashMap);
		}
	}
	@Override
	public List<T_MALL_VALUE> get_value_by_attr_id(int attr_id) {
		List<T_MALL_VALUE> list_value = flushRedisMapper.select_value_by_attr_id(attr_id);
		return list_value;
	}



}

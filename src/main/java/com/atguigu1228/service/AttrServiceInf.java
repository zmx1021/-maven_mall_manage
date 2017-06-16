package com.atguigu1228.service;

import java.util.List;

import com.atguigu1228.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.bean.OBJECT_T_MALL_ATTR;
import com.atguigu1228.bean.T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_VALUE;

public interface AttrServiceInf {
	
	
	public List<OBJECT_T_MALL_ATTR> get_attr_list_by_class_2_id(int class_2_id);

	public void add_attr(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id);
	
	
	public List<T_MALL_VALUE> get_value_by_attr_id(int i);

}

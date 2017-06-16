package com.atguigu1228.service;

import java.util.List;
import java.util.Map;

import com.atguigu1228.bean.OBJECT_T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.bean.T_MALL_VALUE;

public interface FlushRedisService {
	
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id(Map<String, Object> paramMap);
	
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_attr_value(int class_2_id,int pp_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value);
}

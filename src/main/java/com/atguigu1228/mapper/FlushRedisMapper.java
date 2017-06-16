package com.atguigu1228.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu1228.bean.OBJECT_T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_VALUE;

public interface FlushRedisMapper {

	List<OBJECT_T_MALL_SKU> query_sku_page_by_class_2_id(
			Map<String, Object> paramMap);

	List<T_MALL_VALUE> select_value_by_attr_id(int attr_id);

	List<OBJECT_T_MALL_SKU> query_sku_page_by_class_2_id_attr_value(
			Map<String, Object> paramMap);
}

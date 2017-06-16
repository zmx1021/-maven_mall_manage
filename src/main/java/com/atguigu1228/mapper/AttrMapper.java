package com.atguigu1228.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu1228.bean.OBJECT_T_MALL_ATTR;
import com.atguigu1228.bean.T_MALL_SKU;

public interface AttrMapper {

	List<OBJECT_T_MALL_ATTR> select_attr_list_by_class_2_id(@Param("class_2_id") int class_2_id);

	void insert_attr(OBJECT_T_MALL_ATTR attr);

	void insert_values(HashMap<String, Object> hashMap);


}

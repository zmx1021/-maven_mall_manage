package com.atguigu1228.service;

import java.util.List;

import com.atguigu1228.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.bean.T_MALL_PRODUCT;
import com.atguigu1228.bean.T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuServiceInf {

	public List<T_MALL_PRODUCT> get_spu_by_ppid_class2id(int class_1_id, int class_2_id, int pp_id);

	public void add_sku(List<T_MALL_SKU_ATTR_VALUE> list, T_MALL_SKU sku);
}

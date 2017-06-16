package com.atguigu1228.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.atguigu1228.bean.MODEL_T_MALL_SKU_ATTR_VALUE;

import redis.clients.jedis.Jedis;

public class FlushRedisUtil {

	public static <T> void flush_Redis_by_class_2_id(List<T> l, int id) {

		Jedis jedis = MyJedisConnectionUtil.getJedis();
		String key = "class_2_id" + "_" + id;
		jedis.del(key);
		for (int i = 0; i < l.size(); i++) {
			String str = MyJsonUtil.object_to_json_str(l.get(i));
			jedis.zadd(key, i, str);
		}
	}

	public static <T> List<T> redis_value_to_object(Set<String> s, Class<T> t) {

		List<T> l = new ArrayList<T>();

		Iterator<String> iterator = s.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			T object = MyJsonUtil.string_to_object(next, t);
			l.add(object);
		}
		return l;
	}

}

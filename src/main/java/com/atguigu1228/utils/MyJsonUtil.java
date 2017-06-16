package com.atguigu1228.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import net.sf.json.JSONArray;

public class MyJsonUtil {
	
	
	
	public static void main(String[] args) {
		
		List<String> l = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			l.add("打打打"+i);
		}
		
		String str = list_to_list_str(l);
		
		List<String> list = list_str_to_collection(str,String.class);
		
		System.out.println(str);
		
		
		String str2 = object_to_json_str(l.get(1));
		
		System.out.println(str2);
		
	}

	private static Gson gson = new Gson();

	public static <T> String object_to_json_str(T t) {
		String encode = "";
		if (t != null) {
			String json = gson.toJson(t);
			try {
				encode = URLEncoder.encode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return encode;
	}

	public static <T> T string_to_object(String str, Class<T> t) {

		T fromJson = null;

		if (str != null && !"".equals(str)) {
			String decode;
			try {
				decode = URLDecoder.decode(str, "utf-8");

				fromJson = gson.fromJson(decode, t);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return fromJson;
	}

	public static <T> List<T> list_str_to_collection(String str, Class<T> t) {

		List<T> list = null;

		if (str != null && !"".equals(str)) {
			
			 try {
				str = URLDecoder.decode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			JSONArray fromObject = JSONArray.fromObject(str);

			list = (List<T>) fromObject.toCollection(fromObject, t);
		}

		return list;

	}

	public static <T> String list_to_list_str(List<T> l) {
		String str = "";
		if (l.size() != 0 && l != null) {
			JSONArray fromObject = JSONArray.fromObject(l);

			str = fromObject.toString();
			
			 try {
				str = URLEncoder.encode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
}

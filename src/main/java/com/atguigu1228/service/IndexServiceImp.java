package com.atguigu1228.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu1228.mapper.TestMapper;

@Service
public class IndexServiceImp implements IndexServiceInf {

	@Autowired
	TestMapper testMapper;

	@Override
	public int testDb() {
		int select_test = testMapper.select_test();
		return select_test;
	}

}

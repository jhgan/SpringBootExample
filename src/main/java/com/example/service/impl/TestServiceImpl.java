package com.example.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.mssql.MssqlDao;
import com.example.dao.mysql.first.FirstMysqlDao;
import com.example.dao.mysql.second.SecondMysqlDao;
import com.example.dao.mysql.third.ThirdMysqlDao;
import com.example.service.TestService;

@Service("TestServiceImpl")
public class TestServiceImpl implements TestService {
	
	@Autowired
	private FirstMysqlDao firstMysqlDao;
	
	@Autowired
	private SecondMysqlDao secondMysqlDao;
	
	@Autowired
	private ThirdMysqlDao thirdMysqlDao;
	
	@Autowired
	private MssqlDao firstMssqlDao;
	
	@Override
	public Map<String, Object> mulitiDataSourceTest() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> tempMysqlFirstDataAccess = firstMysqlDao.mysqlFirstDataAccess();
		System.out.println("tempMysqlFirstDataAccess: " + tempMysqlFirstDataAccess.toString());
		result.put("mysqlFirstDataAccess", tempMysqlFirstDataAccess);
		
		Map<String, Object> tempMysqlSecondDataAccess = secondMysqlDao.mysqlSecondDataAccess();
		System.out.println("tempMysqlSecondDataAccess: " + tempMysqlSecondDataAccess.toString());
		result.put("mysqlSecondDataAccess", tempMysqlSecondDataAccess);
		
		Map<String, Object> tempMysqlThirdDataAccess = thirdMysqlDao.mysqlThirdDataAccess();
		System.out.println("tempMysqlThirdDataAccess: " + tempMysqlThirdDataAccess.toString());
		result.put("mysqlThirdDataAccess", tempMysqlThirdDataAccess);
		
		Map<String, Object> tempMssqlFirstDataAccess = firstMssqlDao.mssqlFirstDataAccess();
		System.out.println("tempMssqlFirstDataAccess: " + tempMssqlFirstDataAccess.toString());
		result.put("mssqlFirstDataAccess", tempMssqlFirstDataAccess);
		
		return result;
	}
	
}

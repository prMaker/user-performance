/*
 * Copyright (c) 2014 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.performance.dao;

import com.performance.common.Util;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @param <T> 实体
 * @param <ID> 主键
 */
public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

	@Resource 
	protected SqlSessionTemplate sqlTemplate;
	@Resource 
	protected SqlSessionTemplate batchSqlTemplate;
	
	private static final String INSERT = "insert";
	private static final String INSERT_BATCH = "insertBatch";
	private static final String DELETE_BY_ID = "deleteById";
	private static final String DELETE_BY_CONDITION = "deleteByCondition";
	private static final String UPDATE_BY_ID = "updateById";
	private static final String SELECT_LIST = "selectList";
	private static final String SELECT_BY_ID = "selectById";
	private static final String SELECT_COUNT = "selectCount";

	/**
	 * 获取命名空前前缀
	 * @param statement
	 * @return
	 */
	public abstract String getNameSpace(String statement);
	
	public int insert(T t){
		return sqlTemplate.insert(getNameSpace(INSERT), t);
	}
	
	public int insertBatch(List<T> tList){
		String sqlId = getNameSpace(INSERT_BATCH);		
		int insertCount = 0;
		int start = 0;
		int size = tList.size();
		int loop = size - Util.MAX_INSERT_ITEM;
		while(start < loop){
			insertCount += sqlTemplate.insert(sqlId,tList.subList(start,start + Util.MAX_INSERT_ITEM));
			start += Util.MAX_INSERT_ITEM;
		}
		if(start < size){
			insertCount += sqlTemplate.insert(sqlId,tList.subList(start,size));
		}
		return insertCount;
	}
	
	public int deleteById(ID id) {
		return sqlTemplate.delete(getNameSpace(DELETE_BY_ID), id);
	}
	
	public int deleteByCondition(T t) {
		return sqlTemplate.delete(getNameSpace(DELETE_BY_CONDITION), t);
	}
	
	public int updateById(T t) {
		return sqlTemplate.update(getNameSpace(UPDATE_BY_ID), t);
	}
	
	public T selectById(ID id) {
		return (T)sqlTemplate.selectOne(getNameSpace(SELECT_BY_ID), id);
	}
	
	public List<T> selectList(T t) {
		return sqlTemplate.selectList(getNameSpace(SELECT_LIST), t);
	}
	
	public T selectOne(T t) {
		return (T)sqlTemplate.selectOne(getNameSpace(SELECT_LIST), t);
	}
	
	public Long selectCount(T t) {
		return Long.valueOf(sqlTemplate.selectOne(getNameSpace(SELECT_COUNT), t) + "");
	}
}

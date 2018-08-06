/**
 * @author:郑日枋
 * @time:2017年10月17日 下午10:00:12
 * @filename:BaseService.java
 */
package com.rofe.service;

public interface BaseService<T>{
	int deleteByPrimaryKey(Long id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}

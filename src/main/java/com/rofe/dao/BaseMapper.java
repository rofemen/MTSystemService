/**
 * @author:郑日枋
 * @time:2017年10月17日 下午9:21:32
 * @filename:BaseMapper.java
 */
package com.rofe.dao;


public interface BaseMapper<T> {
	
	public int deleteByPrimaryKey(Long id);

	public int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}

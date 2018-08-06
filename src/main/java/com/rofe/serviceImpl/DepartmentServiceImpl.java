/**
 * @author:郑日枋
 * @time:2018年1月14日 下午7:32:20
 * @filename:DepartmentServiceImpl.java
 */
package com.rofe.serviceImpl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.common.PageInfo;
import com.rofe.dao.DepartmentMapper;
import com.rofe.pojo.Department;
import com.rofe.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return departmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Department record) {
		return departmentMapper.insert(record);
	}

	@Override
	public int insertSelective(Department record) {
		return departmentMapper.insertSelective(record);
	}

	@Override
	public Department selectByPrimaryKey(Long id) {
		return departmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Department record) {
		return departmentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Department record) {
		return departmentMapper.updateByPrimaryKey(record);
	}

	@Override
	public ArrayList<Department> getAllDeptInfo() {
		return departmentMapper.getAllDeptInfo();
	}

	@Override
	public ArrayList<Department> getAllDeptInfo(PageInfo<Department> pageInfo) {
		return departmentMapper.getAllDeptInfo(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()));
	}

}

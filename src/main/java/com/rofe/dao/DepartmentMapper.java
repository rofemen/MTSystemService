package com.rofe.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.Department;

public interface DepartmentMapper extends BaseMapper<Department>{
	ArrayList<Department> getAllDeptInfo();
	ArrayList<Department> getAllDeptInfo(RowBounds rowBounds);
	int getAllDeptCount();
}
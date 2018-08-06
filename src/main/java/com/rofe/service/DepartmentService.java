/**
 * @author:郑日枋
 * @time:2018年1月14日 下午7:29:40
 * @filename:DepartmentService.java
 */
package com.rofe.service;

import java.util.ArrayList;

import com.rofe.common.PageInfo;
import com.rofe.pojo.Department;

public interface DepartmentService extends BaseService<Department>{
	public ArrayList<Department> getAllDeptInfo();
	public ArrayList<Department> getAllDeptInfo(PageInfo<Department> pageInfo);
}

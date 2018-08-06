/**
 * @author:郑日枋
 * @time:2017年10月19日 上午9:58:05
 * @filename:TeacherService.java
 */
package com.rofe.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.common.PageInfo;
import com.rofe.pojo.Teacher;
import com.rofe.util.CommonData;

public interface TeacherService extends BaseService<Teacher>{
	//通过工号或用户名查询用户信息
	Teacher selectByTcNumOrUsername(Long tcNum,String tcUserName);
	int updateLockByUsername(int tcLock,String tcUsername);
	//获取教师记录记录总数
	int getTotalCountByType(int roleType,String tcName,Long tcDepartment);
	//通过角色类型获取信息
	ArrayList<Teacher> getAllTcInfoByRoleType(PageInfo<Teacher> pageInfo,int roleType,String tcName,Long tcDepartment);
	//通过筛选条件获取用户信息
	//ArrayList<Teacher> getFilterTcInfo(PageInfo<Teacher> pageInfo,String tcName,String tcDepartment,String roleType);
	//批量删除教师信息
	int batchDelTcInfo(ArrayList<Long> userIdList,int roleId);
	
	ArrayList<Teacher> getTeacherByDiIdAndRole(@Param("diId")Long diId,@Param("tcRole")int tcRole);

	ArrayList<Teacher> getTeacherByDiId(Long diId);
	
	Teacher includeSensitiveInfo(Long id);
	
	HashMap<String, Object> batchAddTcInfo(String tempFilePath,String fileName,int roleType);
	
	ArrayList<Teacher> getBatchTcInfo(ArrayList<Long> tcNum);
	
	int deleteTcInfoById(Long id,int roleId); 
}

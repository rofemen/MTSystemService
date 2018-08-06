package com.rofe.dao;

import java.awt.List;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;

public interface TeacherMapper extends BaseMapper<Teacher>{

	public Teacher selectById(Long tcNum);
	Teacher selectByTcNumOrUsername(
			@Param("tc_num")Long tcNum,
			@Param("tc_username")String tcUsername);
	int updateLockByUsername(@Param("tc_lock")int tcLock,
			@Param("tc_username")String tcUsername);
	int getTotalCountByType(@Param("roleType")int roleType,@Param("tcName")String tcName,
			@Param("tcDepartment")Long tcDepartment);
	//通过角色类型获取信息
	ArrayList<Teacher> getAllTcInfoByRoleType(RowBounds rowBounds,
			@Param("roleType")int roleType,
			@Param("tcName")String tcName,
			@Param("tcDepartment")Long tcDepartment);
	//批量删除教师信息
	int batchDelTcInfo(ArrayList<Long> userIdList);
	
	ArrayList<Teacher> getBatchTcInfo(ArrayList<Long> tcNum);
	
	//根据系别获取教师信息
	ArrayList<Teacher> getTeacherByDiIdAndRole(@Param("diId")Long diId,@Param("tcRole")int tcRole);
	
	ArrayList<Teacher> getTeacherByDiId(@Param("diId")Long diId);
	
	Teacher includeSensitiveInfo(Long id);
	//通过筛选条件获取用户信息
	/*ArrayList<Teacher> getFilterTcInfo(RowBounds rowBounds,
			@Param("tcName")String tcName,
			@Param("tcDepartment")String tcDepartment,
			@Param("roleType")String roleType);*/
}
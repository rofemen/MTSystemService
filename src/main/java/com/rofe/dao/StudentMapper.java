package com.rofe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;

public interface StudentMapper extends BaseMapper<Student>{
	Student selectByStuNumOrUsername(
			@Param("stu_num")Long stuNum,
			@Param("stu_username")String stuUsername);
	int updateLockByUsername(@Param("stu_lock")int stuLock,
			@Param("stu_username")String stuUsername);
	Long[] stuNumIsExists(String[] stuNum);
	int getTotalCountByClass(Long clCode);
	ArrayList<Student> getStudentInfoByTcNum(Long tcNum);
	ArrayList<Student> getStudentInfoByTcNumForShow(RowBounds rowBounds,@Param("tcNum")Long tcNum,@Param("stuName")String stuName);
	int getStudentInfoByTcNumForShowCount(@Param("tcNum")Long tcNum,@Param("stuName")String stuName);
	Student includeSensitiveInfo(Long id);
	ArrayList<Student> getAllStuInfo(RowBounds rowBounds,@Param("stuName")String stuName,
			@Param("stuDeptid")Long stuDeptid);
	int getAllStuCount(@Param("stuName")String stuName,
			@Param("stuDeptid")Long stuDeptid);
	int batchDelStuInfo(ArrayList<Integer> userIdList);
	
	ArrayList<Student> getBatchStuInfo(ArrayList<Long> stuNum);
	
    int deleteStuInfoByClCode(long clCode);
    
    int deleteStuInfoByClCodeList(ArrayList<Long> clCodeList);
    
    Student selectById(Long userId);
}
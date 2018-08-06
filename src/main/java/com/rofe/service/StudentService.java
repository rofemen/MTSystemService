/**
 * @author:郑日枋
 * @time:2017年10月19日 下午10:33:37
 * @filename:StudentService.java
 */
package com.rofe.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.common.PageInfo;
import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;

public interface StudentService extends BaseService<Student>{
	Student selectByStuNumOrUsername(Long stuNum,String stuUsername);
	int updateLockByUsername(int stuLock,String stuUsername);
	Long[] stuNumIsExists(String[] stuNum);
	ArrayList<Student> getStudentInfoByTcNum(Long tcNum);
	int getTotalCountByClass(Long clCode);
	Student includeSensitiveInfo(Long id);
	int getAllStuCount(String stuName,Long stuDeptid);
	ArrayList<Student> getAllStuInfo(PageInfo<Student> pageInfo,String stuName,Long stuDeptid);
	public int addStudent(Student record);
	int batchDelStuInfo(ArrayList<Integer> userIdList,ArrayList<Integer> clCodeList);
	HashMap<String, Object> batchInsertStuInfo(String tempFilePath,String fileName);
	ArrayList<Student> getBatchStuInfo(ArrayList<Long> stuNum);
	ArrayList<Student> getStudentInfoByTcNumForShow(PageInfo<Student> pageInfo,Long tcNum,String stuName);
	int getStudentInfoByTcNumForShowCount(Long tcNum,String stuName);
}

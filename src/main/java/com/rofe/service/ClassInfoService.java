/**
 * @author:郑日枋
 * @time:2017年10月17日 下午10:13:15
 * @filename:ClassInfoService.java
 */
package com.rofe.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.rofe.common.PageInfo;
import com.rofe.pojo.ClassInfo;
import com.rofe.pojo.Teacher;


public interface ClassInfoService extends BaseService<ClassInfo>{
	 ArrayList<ClassInfo> getAllClassInfo(PageInfo<Teacher> pageInfo,String clGrade);
	 
	 int getClassInfoCount(String clGrade);
	 
	 ArrayList<String> getAllGrade(String clGrade);
	 
	 int batchDelClInfo(ArrayList<Long> clCodeList);
	 
	 public ClassInfo selectByTcNum(int roleType ,Long tcNum);
	 
	ArrayList<ClassInfo> getAllClassInfoForAdd(String clGrade,Long clDpId);


}

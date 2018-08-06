package com.rofe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.ClassInfo;
import com.rofe.pojo.Teacher;

public interface ClassInfoMapper extends BaseMapper<ClassInfo>{
    ArrayList<ClassInfo> getAllClassInfo(RowBounds rowBounds,@Param("clGrade")String clGrade);
    int getClassInfoCount(@Param("clGrade")String clGrade);
    ArrayList<String> getAllGrade(String clGrade);
  	int batchDelClInfo(ArrayList<Long> clCodeList);
  	public ClassInfo selectById(Long clCode);
  	public ClassInfo selectByTcNum(@Param("tcRole")int roleType ,@Param("tcNum")Long tcNum);
    ArrayList<ClassInfo> getAllClassInfoForAdd(@Param("clGrade")String clGrade,@Param("clDpId")Long clDpId);
    ClassInfo selectByIdAndGrade(@Param("clGrade")int clGrade,@Param("clCode")Long clCode); 
    int updateInstorInfoByTcUserId(ArrayList<Long> tcUserList);
    int updateArmyInstorInfoByTcUserId(ArrayList<Long> tcUserList);
    int updateTutorInfoByTcUserId(ArrayList<Long> tcUserList);
}
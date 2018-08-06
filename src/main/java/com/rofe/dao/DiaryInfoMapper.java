package com.rofe.dao;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.DiaryInfo;

public interface DiaryInfoMapper {
    int deleteByPrimaryKey(Integer diaryId);

    int insert(DiaryInfo record);

    int insertSelective(DiaryInfo record);

    DiaryInfo selectByPrimaryKey(Integer diaryId);

    int updateByPrimaryKeySelective(DiaryInfo record);

    int updateByPrimaryKeyWithBLOBs(DiaryInfo record);

    int updateByPrimaryKey(DiaryInfo record);
    
    ArrayList<DiaryInfo> getDiaryInfoByNameOrTime(RowBounds rowBounds,@Param("clCode")int clCode,@Param("stuNum")int stuNum
    		,@Param("diaryTime")String diaryTime);
    int getDiaryInfoByNameOrTimeCount(@Param("clCode")int clCode,@Param("stuNum")int stuNum
    		,@Param("diaryTime")String diaryTime);
    
    int batchDelDiaryInfo(ArrayList<Long> diaryIdList);
    
    ArrayList<DiaryInfo> getDiaryInfoByStuNum(RowBounds rowBounds,@Param("stuNum")int stuNum,@Param("diaryTime")String diaryTime);
    
    int getDiaryInfoByStuNumCount(@Param("stuNum")int stuNum,@Param("diaryTime")String diaryTime);
    
    int delDiaryPublicer(Long stuNum);
}
/**
 * @author:郑日枋
 * @time:2018年2月27日 下午9:55:22
 * @filename:DiaryService.java
 */
package com.rofe.service;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.common.PageInfo;
import com.rofe.pojo.DiaryInfo;

public interface DiaryService {
	int deleteByPrimaryKey(int id);

	int insert(DiaryInfo record);

	int insertSelective(DiaryInfo record);

	DiaryInfo selectByPrimaryKey(int id);

	int updateByPrimaryKeySelective(DiaryInfo record);

	int updateByPrimaryKey(DiaryInfo record);
	
	ArrayList<DiaryInfo> getDiaryInfoByNameOrTime(PageInfo<DiaryInfo> pageInfo,int clCode,int stuNum
    		,String diaryTime);
	int getDiaryInfoByNameOrTimeCount(int clCode,int stuNum
    		,String diaryTime);
	int batchDelDiaryInfo(ArrayList<Long> diaryIdList);
	
    ArrayList<DiaryInfo> getDiaryInfoByStuNum(PageInfo<DiaryInfo> pageInfo,int stuNum,String diaryTime);
    
    int getDiaryInfoByStuNumCount(int stuNum,String diaryTime);
}

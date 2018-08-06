package com.rofe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.rofe.pojo.Scheduleinfo;

public interface ScheduleinfoMapper extends BaseMapper<Scheduleinfo>{
	ArrayList<Scheduleinfo> getTcSchedule(Long tcUserId);
	Long isScheduleExist(@Param("tcUserId")Long tcUserId,@Param("siIndex")int siIndex);
	int deleteTcSchedule(Long siClCode);
	ArrayList<Scheduleinfo> getStudentSchedule(Long stuId);
	ArrayList<Scheduleinfo> getTeacherSchedule(Long tcId);
	ArrayList<Scheduleinfo> getClassSchedule(Long siClCode);
	int deleteScheduleInfoByCiCode(Long siCiCode);
}
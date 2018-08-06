/**
 * @author:郑日枋
 * @time:2018年1月27日 下午2:30:40
 * @filename:ScheduleInfoService.java
 */
package com.rofe.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.rofe.pojo.Scheduleinfo;

public interface ScheduleInfoService extends BaseService<Scheduleinfo>{
	ArrayList<Scheduleinfo> getTcSchedule(Long tcUserId);
	public ArrayList<Scheduleinfo> updateScheduleByList(ArrayList<LinkedHashMap<String, Object>> list,Long siClCode,Long tcNum );
	public Long isScheduleExist(Long tcUserId,int siIndex);
	int deleteTcSchedule(Long siClCode);
	ArrayList<Scheduleinfo> getTeachedCi(Long tcUserId,Long stuId);
	ArrayList<Scheduleinfo> getStudentSchedule(Long stuId);
	ArrayList<Scheduleinfo> getTeacherSchedule(Long tcId);
	ArrayList<Scheduleinfo> getClassSchedule(Long siClCode);
}

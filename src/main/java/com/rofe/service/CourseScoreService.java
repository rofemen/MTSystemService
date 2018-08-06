/**
 * @author:郑日枋
 * @time:2018年2月5日 上午8:34:24
 * @filename:CourseScoreService.java
 */
package com.rofe.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rofe.common.PageInfo;
import com.rofe.pojo.CourseScore;

public interface CourseScoreService extends BaseService<CourseScore>{
	public ArrayList<CourseScore> getStudentScore(Long tcUserId);
	public int operateCourseScore(ArrayList<HashMap<String,Object>> scoreItem);
}

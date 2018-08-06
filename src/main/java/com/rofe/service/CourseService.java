/**
 * @author:郑日枋
 * @time:2018年1月24日 下午10:46:13
 * @filename:CourseService.java
 */
package com.rofe.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.common.PageInfo;
import com.rofe.pojo.Course;

public interface CourseService extends BaseService<Course>{
	public int undoCourseApply(Long ciCode);
	public ArrayList<Course> getCourseInfo(PageInfo<Course> pageInfo,int ciStatus);
	public int getCourseInfoCount(int ciStatus);
	public ArrayList<Course> getTcCouresInfo(Long ciTcnum);
	public ArrayList<Course> getTcTecheredCi(Long ciTcnum);
}

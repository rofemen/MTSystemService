/**
 * @author:郑日枋
 * @time:2018年1月24日 下午10:47:11
 * @filename:CourseServiceImpl.java
 */
package com.rofe.serviceImpl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.common.PageInfo;
import com.rofe.dao.CourseMapper;
import com.rofe.dao.ScheduleinfoMapper;
import com.rofe.pojo.Course;
import com.rofe.pojo.Scheduleinfo;
import com.rofe.service.CourseService;
import com.rofe.service.ScheduleInfoService;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	CourseMapper courseMapper;
	@Autowired
	ScheduleinfoMapper scheduleinfoMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
	    scheduleinfoMapper.deleteScheduleInfoByCiCode(id);
	    return courseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Course record) {
		return courseMapper.insert(record);
	}

	@Override
	public int insertSelective(Course record) {
		return courseMapper.insertSelective(record);
	}

	@Override
	public Course selectByPrimaryKey(Long id) {
		return courseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Course record) {
		return courseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Course record) {
		return courseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int undoCourseApply(Long ciCode) {
		return courseMapper.undoCourseApply(ciCode);
	}

	public ArrayList<Course> getTcCouresInfo(Long ciTcnum) {
		return courseMapper.getTcCouresInfo(ciTcnum);
	}

	@Override
	public ArrayList<Course> getTcTecheredCi(Long ciTcnum) {
		return courseMapper.getTcTecheredCi(ciTcnum);
	}

	@Override
	public ArrayList<Course> getCourseInfo(PageInfo<Course> pageInfo,
			int ciStatus) {
		return courseMapper.getCourseInfo(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()),ciStatus);
	}

	@Override
	public int getCourseInfoCount(int ciStatus) {
		return courseMapper.getCourseInfoCount(ciStatus);
	}
	
}

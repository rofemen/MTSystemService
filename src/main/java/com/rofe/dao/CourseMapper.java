package com.rofe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.Course;
import com.rofe.pojo.Teacher;

public interface CourseMapper extends BaseMapper<Course> {
   public int undoCourseApply(Long ciCode);
   public ArrayList<Course> getCourseInfo(RowBounds rowBounds,@Param("ciStatus")int ciStatus);
   public int getCourseInfoCount(int ciStatus);
   public Course selectById(Long ciCode);
   public ArrayList<Course> getTcCouresInfo(Long ciTcnum);
   public ArrayList<Course> getTcTecheredCi(Long ciTcnum);
}
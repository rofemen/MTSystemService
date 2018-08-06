package com.rofe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.CourseScore;

public interface CourseScoreMapper  extends BaseMapper<CourseScore>{
    public ArrayList<CourseScore> getStudentScore(@Param("tcUserId")Long tcUserId);
    public CourseScore operateCourseScore(@Param("stuUserId")Long stuUserId,
    		@Param("csCiCode")Long csCiCode);
    
}
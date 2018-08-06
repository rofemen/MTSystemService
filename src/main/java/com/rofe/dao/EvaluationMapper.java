package com.rofe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.Evaluation;

public interface EvaluationMapper extends BaseMapper<Evaluation>{
    public ArrayList<Evaluation> selectByStuId(Long stuId);
    public ArrayList<Evaluation> getEiInfoByTcNum(RowBounds rowBounds,@Param("tcNum")Long tcNum,@Param("ciCode")Long ciCourse);
    public int getEiInfoCountByTcNum(@Param("tcNum")Long tcNum,@Param("ciCode")Long ciCourse);
}
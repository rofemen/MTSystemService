/**
 * @author:郑日枋
 * @time:2018年2月5日 上午8:35:07
 * @filename:CourseScoreServiceImpl.java
 */
package com.rofe.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.common.PageInfo;
import com.rofe.dao.CourseScoreMapper;
import com.rofe.pojo.CourseScore;
import com.rofe.pojo.Student;
import com.rofe.service.CourseScoreService;
import com.rofe.service.StudentService;
@Service
public class CourseScoreServiceImpl implements CourseScoreService{
	@Autowired
	CourseScoreMapper courseScoreMapper;
	@Autowired
	StudentService studentService;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return courseScoreMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CourseScore record) {
		return courseScoreMapper.insert(record);
	}

	@Override
	public int insertSelective(CourseScore record) {
		return courseScoreMapper.insertSelective(record);
	}

	@Override
	public CourseScore selectByPrimaryKey(Long id) {
		return courseScoreMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CourseScore record) {
		return courseScoreMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CourseScore record) {
		return courseScoreMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ArrayList<CourseScore> getStudentScore(Long tcUserId) {
		return courseScoreMapper.getStudentScore(tcUserId);
	}

	@Override
	public int operateCourseScore(ArrayList<HashMap<String,Object>> scoreItem) {
		if(scoreItem == null){
			return -1;
		}
		int count = 0;
		try {
			for(int i=0; i<scoreItem.size(); i++){
				HashMap<String, Object> map = scoreItem.get(i);
				Long stuUserId = Long.parseLong(map.get("stuUserId").toString());
				float finalScoreNum =  Float.parseFloat(map.get("finalScoreNum").toString());
				map.remove("stuUserId");
				map.remove("finalScoreNum");
				
				boolean isChange = false;
				for (Entry<String, Object> entry : map.entrySet()) { 
					  Long ciCode = Long.parseLong(entry.getKey().toString());
					  float score = Float.parseFloat(entry.getValue().toString());
					  CourseScore courseScore = courseScoreMapper.operateCourseScore(stuUserId, ciCode);
					  if(courseScore != null){
						 if( courseScore.getCsScore() != score){
							 courseScore.setCsScore(score);
							 int result = courseScoreMapper.updateByPrimaryKeySelective(courseScore);
							 if(result != 0){
								 isChange = true;
							 }
						 }
					  }else{
						 CourseScore courseScoreItem = new CourseScore();
						 courseScoreItem.setCsCiCode(ciCode);
						 courseScoreItem.setCsStuUserid(stuUserId);
						 courseScoreItem.setCsScore(score);
						 int result = courseScoreMapper.insertSelective(courseScoreItem);
						 if(result != 0){
							 isChange = true;
						 }
					  }
					  if(isChange){
						  Student tempStu = new Student();
						  tempStu.setStuUserid(stuUserId);
						  tempStu.setStuFinalscore(finalScoreNum);
						  studentService.updateByPrimaryKeySelective(tempStu);
					  }
					  count++;  
				}
			}
		} catch (Exception e) {
			return -1;
		}
		
		return count;
	}
	
}

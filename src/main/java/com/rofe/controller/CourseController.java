/**
 * @author:郑日枋
 * @time:2018年1月21日 下午10:23:27
 * @filename:ClassInfoController.java
 */
package com.rofe.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofe.common.PageInfo;
import com.rofe.pojo.Course;
import com.rofe.pojo.Teacher;
import com.rofe.service.CourseScoreService;
import com.rofe.service.CourseService;
import com.rofe.util.CommonTools;


@Controller
@RequestMapping("/courseController")
public class CourseController {
	@Autowired
	CourseService courseService;
	@Autowired
	CourseScoreService courseScoreService;
	@RequestMapping("/addCourseInfo")
	@ResponseBody
	public HashMap<String,Object> addClassInfo(@RequestBody Course courseInfo){
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int temp=courseService.insertSelective(courseInfo);
		if(temp!=0){
			Long ciCode =courseInfo.getCiCode();
			courseInfo = courseService.selectByPrimaryKey(ciCode);
			map.put("code", "200");
			data.put("message", "添加成功");
			data.put("courseInfo", courseInfo);
			map.put("data", data);
		}else{
			map.put("code", "203");
			data.put("message", "参数异常，添加失败");
 			map.put("data", data);
		}
		return map;
	}
	
	
	
	@RequestMapping("/undoCourseApply")
	@ResponseBody
	public HashMap<String,Object> undoCourseApply(@RequestBody Map params){
		Long ciCode = CommonTools.toLong((int)params.get("ciCode"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int temp=courseService.undoCourseApply(ciCode);
		if(temp!=0){
			map.put("code", "200");
			data.put("message", "撤销申报成功");
			map.put("data", data);
		}else{
			map.put("code", "204");
			data.put("message", "该课程已经审批通过，已超过撤销时间");
 			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getCourseInfo")
	public HashMap<String,Object> getCourseInfo(@RequestBody HashMap<String,Object> params){
		int ciStatus = (int)params.get("ciStatus");
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		PageInfo<Course> pageInfo = new PageInfo<>(currentPage,pageSize);
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int records = courseService.getCourseInfoCount(ciStatus);
		if(records != 0){
			pageInfo.setTotalRecords(records);
			ArrayList<Course> result = courseService.getCourseInfo(pageInfo,ciStatus);
			if(result.size() != 0){
				map.put("code", "200");
				data.put("message", "获取课程信息成功");
				data.put("list",result);
				data.put("totalSize", records);
				data.put("currPage",currentPage);
				data.put("pageSize",pageSize);
				map.put("data",data);
			}else{
				map.put("code", 202); 
				data.put("message", "暂无记录");
				map.put("data", data);
			}
		}else{
			map.put("code", 202); 
			data.put("message", "暂无记录");
			map.put("data", data);
		}	
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping("/getTcCouresInfo")
	public HashMap<String,Object> getTcCouresInfo(@RequestBody HashMap<String,Object> params){
		Long tcNum = CommonTools.toLong((Integer)params.get("tcNum"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Course> list= courseService.getTcCouresInfo(tcNum);
		if(list.size() != 0){
			map.put("code", 200);
			data.put("message", "获取可以开启的课程");
			data.put("list",list);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "暂无课程记录");
			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getTcTecheredCi")
	public HashMap<String,Object> getTcTecheredCi(Long tcNum){
		
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Course> list= courseService.getTcTecheredCi(tcNum);
		if(list.size() != 0){
			map.put("code", 200);
			data.put("message", "获取已经开启的课程");
			data.put("list",list);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "暂无课程记录");
			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getTcTecheredCiForPost")
	public HashMap<String,Object> getTcTecheredCiForPost(@RequestBody HashMap<String,Object> params){
		Long tcNum = CommonTools.toLong((Integer)params.get("tcNum"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Course> list= courseService.getTcTecheredCi(tcNum);
		if(list.size() != 0){
			map.put("code", 200);
			data.put("message", "获取已经开启的课程");
			data.put("list",list);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "暂无课程记录");
			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/operateCourseScore")
	public HashMap<String,Object> operateCourseScore(@RequestBody HashMap<String,Object> params){
		ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String,Object>>)params.get("scoreItems");
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int count = courseScoreService.operateCourseScore(list);
		if(count != -1){
			map.put("code", 200);
			data.put("message", "录入成绩成功");
			data.put("list",list);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "更新失败，请稍后重试");
			map.put("data", data);
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("/delectCourseInfoById")
	public HashMap<String, Object> delectCourseInfoById(@RequestBody HashMap<String,Object> params){
		Long ciCode = CommonTools.toLong((int)params.get("ciCode"));
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		
		int temp = courseService.deleteByPrimaryKey(ciCode);
		if(temp != 0){
			map.put("code", "200");
			data.put("message", "删除成功");
			map.put("data",data);
		}else{
			map.put("code", "201");
			data.put("message", "删除失败");
			map.put("data",data);
		}
		return map;
	}
	@RequestMapping("/updateCourseInfo")
	@ResponseBody
	public HashMap<String,Object> updateCourseInfo(@RequestBody Course coureInfo){

		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int temp = courseService.updateByPrimaryKeySelective(coureInfo);
		if(temp != 0){
			map.put("code", "200");
			data.put("message", "更新课程信息成功");
			map.put("data", data);
		}else{
			map.put("code", "204");
			data.put("message", "更新课程信息失败");
 			map.put("data", data);
		}
		return map;
	}
}

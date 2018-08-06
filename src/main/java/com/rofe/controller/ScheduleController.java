/**
 * @author:郑日枋
 * @time:2018年1月21日 下午10:23:27
 * @filename:ClassInfoController.java
 */
package com.rofe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofe.common.PageInfo;
import com.rofe.pojo.Course;
import com.rofe.pojo.Scheduleinfo;
import com.rofe.pojo.Teacher;
import com.rofe.service.CourseService;
import com.rofe.service.ScheduleInfoService;
import com.rofe.util.CommonTools;


@Controller
@RequestMapping("/scheduleController")
public class ScheduleController {
	@Autowired
	ScheduleInfoService scheduleInfoService;
	
	
	@ResponseBody
	@RequestMapping("/getTcSchedule")
	public HashMap<String,Object> getTcSchedule(@RequestBody HashMap<String,Object> params){
		Long tcUserId = CommonTools.toLong((int)params.get("tcUserId"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Scheduleinfo> result = scheduleInfoService.getTcSchedule(tcUserId);
		if(result.size() != 0){
			map.put("code", "200");
			data.put("message", "获取课表信息成功");
			data.put("list",result);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "暂无课表信息");
			map.put("data", data);
		}	
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getTeachedCi")
	public HashMap<String,Object> getTeachedCi(@RequestBody HashMap<String,Object> params){
		Long tcUserId = CommonTools.toLong((int)params.get("tcUserId"));
		Long stuId = CommonTools.toLong((int)params.get("stuId"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Scheduleinfo> result = scheduleInfoService.getTeachedCi(tcUserId,stuId);
		if(result.size() != 0){
			map.put("code", "200");
			data.put("message", "获取信息在教课程成功");
			data.put("list",result);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "暂无在教课程信息");
			map.put("data", data);
		}	
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getTeacherSchedule")
	public HashMap<String,Object> getTeacherSchedule(@RequestBody HashMap<String,Object> params){
		Long tcId = CommonTools.toLong((int)params.get("tcId"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Scheduleinfo> result = scheduleInfoService.getTeacherSchedule(tcId);
		if(result.size() != 0){
			map.put("code", "200");
			data.put("message", "获取课程信息成功");
			data.put("list",result);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "暂课程信息");
			map.put("data", data);
		}	
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getStudentSchedule")
	public HashMap<String,Object> getStudentSchedule(@RequestBody HashMap<String,Object> params){
		Long stuId = CommonTools.toLong((int)params.get("stuId"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Scheduleinfo> result = scheduleInfoService.getStudentSchedule(stuId);
		if(result.size() != 0){
			map.put("code", "200");
			data.put("message", "获取课程信息成功");
			data.put("list",result);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "暂课程信息");
			map.put("data", data);
		}	
		return map;
	}
	@ResponseBody
	@RequestMapping("/getClassSchedule")
	public HashMap<String,Object> getClassSchedule(@RequestBody HashMap<String,Object> params){
		Long siClCode = CommonTools.toLong((int)params.get("siClCode"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Scheduleinfo> result = scheduleInfoService.getClassSchedule(siClCode);
		if(result.size() != 0){
			map.put("code", "200");
			data.put("message", "获取课程信息成功");
			data.put("list",result);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "暂无课程信息");
			map.put("data", data);
		}	
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping("/updateScheduleByList")
	public HashMap<String,Object> updateScheduleByList(@RequestBody HashMap<String,Object> params){
		ArrayList<LinkedHashMap<String, Object>> scheduleList = (ArrayList<LinkedHashMap<String, Object>>)params.get("scheduleList");
		long siClCode = CommonTools.toLong((int)params.get("siClCode"));
		long tcNum = CommonTools.toLong((int)params.get("tcNum"));
		ArrayList<Scheduleinfo> list = new ArrayList<>();
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		if(scheduleList != null && scheduleList.size()!= 0){
			list = scheduleInfoService.updateScheduleByList(scheduleList,siClCode,tcNum);
		}		
		if(scheduleList.size() != 0){
			map.put("code", "200");
			data.put("message", "更新课表信息成功");
			data.put("list",list);
			map.put("data",data);
		}else{
			map.put("code", 202); 
			data.put("message", "暂无课表信息");
			map.put("data", data);
		}	
		return map;
	}
	
}

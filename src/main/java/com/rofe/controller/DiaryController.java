/**
 * @author:郑日枋
 * @time:2017年10月19日 下午1:07:47
 * @filename:LoginController.java
 */
package com.rofe.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rofe.common.PageInfo;
import com.rofe.pojo.DiaryInfo;
import com.rofe.pojo.EmailMessage;
import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.DiaryService;
import com.rofe.service.EmailService;
import com.rofe.service.StudentService;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonData;
import com.rofe.util.CommonTools;

@Controller
@RequestMapping("/diaryController")
public class DiaryController {
	@Autowired
	DiaryService diaryService;
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/getDiaryInfoByNameOrTime")
	@ResponseBody
	public HashMap<String,Object> getDiaryInfoByNameOrTime(@RequestBody HashMap<String, Object> params) throws Exception{
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		int stuNum = 0;
		String tempStuNum = params.get("stuNum").toString();
		if(!"".equals(tempStuNum)){
			stuNum = Integer.parseInt(tempStuNum);
		}
		int clCode = (int)params.get("clCode");
		String paramTime = (String)params.get("diaryTime");
		if(!"".equals(paramTime)&& paramTime != null){
			paramTime = paramTime.replace("Z", " UTC");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
			Date d = format.parse(paramTime);
			paramTime = new SimpleDateFormat("yyyy-MM-dd").format(d);
		}
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		PageInfo<DiaryInfo> pageInfo = new PageInfo<>(currentPage,pageSize);
		int result = diaryService.getDiaryInfoByNameOrTimeCount(clCode, stuNum,paramTime);
		if(result != 0){
			pageInfo.setTotalRecords(result);
			ArrayList<DiaryInfo> list = diaryService.getDiaryInfoByNameOrTime(pageInfo,clCode, stuNum, paramTime);
			map.put("code", "200");
			data.put("list",list);
			data.put("totalSize", result);
			data.put("currPage",currentPage);
			data.put("pageSize",pageSize);
			map.put("data", data);
		}else{
			map.put("code", "203");
			data.put("message", "暂无军训日记");
 			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/delectDiaryById")
	public HashMap<String, Object> delectDiaryById(@RequestBody HashMap<String,Object> params){
		int diaryId = (int)params.get("diaryId");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		int temp = diaryService.deleteByPrimaryKey(diaryId);
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
	
	@ResponseBody
	@RequestMapping("/batchDelDiaryInfo")
	public HashMap<String, Object> batchDelDiaryInfo(@RequestBody HashMap<String,Object> params){
		ArrayList<Long> userIdList = (ArrayList<Long>)params.get("diaryIdList");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		int temp = diaryService.batchDelDiaryInfo(userIdList);
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
	
	@RequestMapping("/addDiaryInfo")
	@ResponseBody
	public HashMap<String,Object> addDiaryInfo(@RequestBody DiaryInfo diaryInfo){
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int result = diaryService.insertSelective(diaryInfo);
		if(result != 0){
			map.put("code",200);
			data.put("message","添加成功");
			map.put("data",data);
		}else{
			map.put("code",201);
			data.put("message","添加失败，请稍后重试");
			map.put("data",data);
		}
		return map;
	}
	
	@RequestMapping("/updateDiaryInfo")
	@ResponseBody
	public HashMap<String,Object> updateDiaryInfo(@RequestBody DiaryInfo diaryInfo){
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int result = diaryService.updateByPrimaryKeySelective(diaryInfo);
		if(result != 0){
			map.put("code",200);
			data.put("message","更新成功");
			map.put("data",data);
		}else{
			map.put("code",201);
			data.put("message","更新失败，请稍后重试");
			map.put("data",data);
		}
		return map;
	}
	
	@RequestMapping("/getDiaryInfoByStuNum")
	@ResponseBody
	public HashMap<String,Object> getDiaryInfoByStuNum(@RequestBody HashMap<String, Object> params) throws Exception{
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		int stuNum = 0;
		String tempStuNum = params.get("stuNum").toString();
		if(!"".equals(tempStuNum)){
			stuNum = Integer.parseInt(tempStuNum);
		}
		String paramTime = (String)params.get("diaryTime");
		if(!"".equals(paramTime)&& paramTime != null){
			paramTime = paramTime.replace("Z", " UTC");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
			Date d = format.parse(paramTime);
			paramTime = new SimpleDateFormat("yyyy-MM-dd").format(d);
		}
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		PageInfo<DiaryInfo> pageInfo = new PageInfo<>(currentPage,pageSize);
		int result = diaryService.getDiaryInfoByStuNumCount(stuNum,paramTime);
		if(result != 0){
			pageInfo.setTotalRecords(result);
			ArrayList<DiaryInfo> list = diaryService.getDiaryInfoByStuNum(pageInfo,stuNum, paramTime);
			map.put("code", "200");
			data.put("list",list);
			data.put("totalSize", result);
			data.put("currPage",currentPage);
			data.put("pageSize",pageSize);
			map.put("data", data);
		}else{
			map.put("code", "203");
			data.put("message", "暂无军训日记");
 			map.put("data", data);
		}
		return map;
	}
}

/**
 * @author:郑日枋
 * @time:2018年1月21日 下午10:23:27
 * @filename:ClassInfoController.java
 */
package com.rofe.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofe.common.PageInfo;
import com.rofe.pojo.ClassInfo;
import com.rofe.pojo.Teacher;
import com.rofe.service.ClassInfoService;
import com.rofe.util.CommonTools;


@Controller
@RequestMapping("/classInfoController")
public class ClassInfoController {
	@Autowired
	ClassInfoService classInfoService;
	
	@ResponseBody
	@RequestMapping("/getAllClassInfoForAdd")
	public HashMap<String,Object> getAllClassInfoForAdd(@RequestBody HashMap<String,Object> params){
		String clGrade = String.valueOf(params.get("clGrade"));
		Long clDpId = 0L;
		if(!"".equals(params.get("clDpId").toString())){
			clDpId = CommonTools.toLong((int)params.get("clDpId"));
		}
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<ClassInfo> result = classInfoService.getAllClassInfoForAdd(clGrade, clDpId);
		map.put("code", "200");
		data.put("message", "获取班级信息成功");
		data.put("list",result);
		map.put("data",data);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getAllClassInfo")
	public HashMap<String,Object> getAllClassInfo(@RequestBody HashMap<String,Object> params){
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		String clGrade = String.valueOf(params.get("clGrade"));
		PageInfo<Teacher> pageInfo = new PageInfo<>(currentPage,pageSize);
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int records = classInfoService.getClassInfoCount(clGrade);
		if(records != 0){
			pageInfo.setTotalRecords(records);
			ArrayList<ClassInfo> result = classInfoService.getAllClassInfo(pageInfo,clGrade);
			if(result.size() != 0){
				map.put("code", "200");
				data.put("message", "获取班级信息成功");
				data.put("list",result);
				data.put("totalSize", records);
				data.put("currPage",currentPage);
				data.put("pageSize",pageSize);
				map.put("data",data);
			}else{
				map.put("code", 202); 
				data.put("message", "暂无班级信息");
				map.put("data", data);
			}
		}else{
			map.put("code", 202); 
			data.put("message", "暂无班级信息");
			map.put("data", data);
		}	
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getAllGrade")
	public HashMap<String,Object> getAllGrade(Long uid){
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		//ArrayList<String> list= classInfoService.getAllGrade();		
		map.put("code", "200");
		data.put("message", "查询年级");
		//data.put("list",list);
		map.put("data",data);
		return map;
	}
	
	@RequestMapping("/addClassInfo")
	@ResponseBody
	public HashMap<String,Object> addClassInfo(@RequestBody ClassInfo classInfo){
		
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		
	 	Long clCode = classInfo.getClCode();
	 	if(clCode != 0){
	 		ClassInfo tempCl = classInfoService.selectByPrimaryKey(clCode);
	 		if(tempCl !=null){
	 			map.put("code", "202");
	 			data.put("message", "该班级编号已经存在");
	 			map.put("data", data);
	 			return map;
	 		}
	 	}
		int temp=classInfoService.insertSelective(classInfo);
		if(temp!=0){
			map.put("code", "200");
			data.put("message", "添加成功");
			map.put("data", data);
		}else{
			map.put("code", "203");
			data.put("message", "参数异常，添加失败");
 			map.put("data", data);
		}
		return map;
	}
	
	@RequestMapping("/updateClassInfo")
	@ResponseBody
	public HashMap<String,Object> updateClassInfo(@RequestBody ClassInfo classInfo){
		
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ClassInfo tempClass = classInfoService.selectByPrimaryKey(classInfo.getClCode());
		if(tempClass != null){
			int count = tempClass.getClCount();
			if(count > classInfo.getClMaxcount()){
				map.put("code", "203");
				data.put("message", "当前班级人数已经超过班级容量，请调整容量");
	 			map.put("data", data);
	 			return map;
			}
		}
		int temp=classInfoService.updateByPrimaryKeySelective(classInfo);
		if(temp!=0){
			map.put("code", "200");
			data.put("message", "修改信息成功");
			map.put("data", data);
		}else{
			map.put("code", "203");
			data.put("message", "信息修改失败，请稍后重试");
 			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/delectClassInfoById")
	public HashMap<String, Object> delectClassInfoById(@RequestBody HashMap<String,Object> params){
		Long clCode = CommonTools.toLong((int)params.get("clCode"));
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		int temp = classInfoService.deleteByPrimaryKey(clCode);
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
	@RequestMapping("/batchDelClInfo")
	public HashMap<String, Object> batchDelClInfo(@RequestBody HashMap<String,Object> params){
		ArrayList<Long> clCodeList = (ArrayList<Long>)params.get("clCodeList");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		int temp = classInfoService.batchDelClInfo(clCodeList);
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
	@RequestMapping("/selectByTcNum")
	public HashMap<String, Object> selectByTcNum(@RequestBody HashMap<String,Object> params){
		int roleType = (int)params.get("roleType");
		Long tcNum = CommonTools.toLong((int)params.get("tcNum"));
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,Object> data=new HashMap<>();
		ClassInfo classInfo = classInfoService.selectByTcNum(roleType,tcNum);
		if(classInfo != null){
			map.put("code", "200");
			data.put("message", "获取班级信息成功");
			data.put("classInfo",classInfo);
			map.put("data",data);
		}else{
			map.put("code", "201");
			data.put("message", "暂未被指派到指定班级");
			map.put("data",data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/selectByclCode")
	public HashMap<String, Object> selectByclCode(@RequestBody HashMap<String,Object> params){
		Long clCode = CommonTools.toLong((int)params.get("clCode"));
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,Object> data=new HashMap<>();
		ClassInfo classInfo = classInfoService.selectByPrimaryKey(clCode);
		if(classInfo != null){
			map.put("code", "200");
			data.put("message", "获取班级信息成功");
			data.put("classInfo",classInfo);
			map.put("data",data);
		}else{
			map.put("code", "201");
			data.put("message", "暂无该班级信息");
			map.put("data",data);
		}
		return map;
	}
}

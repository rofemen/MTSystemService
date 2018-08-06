/**
 * @author:郑日枋
 * @time:2017年5月21日 下午10:08:32
 * @filename:testController.java
 */
package com.rofe.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rofe.common.PageInfo;
import com.rofe.pojo.Department;
import com.rofe.pojo.Teacher;
import com.rofe.service.ClassInfoService;
import com.rofe.service.DepartmentService;
import com.rofe.service.StudentService;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonData;

@Controller
@RequestMapping("/departmentController")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	
	
	@ResponseBody
	@RequestMapping("/getAllDeptInfo")
	public HashMap<String,Object> getAllDeptInfo(){	
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Department> result = departmentService.getAllDeptInfo();
		if(result.size() != 0){
			map.put("code", "200");
			data.put("message", "成功获取连队信息");
			data.put("list",result);
			data.put("totalSize", result.size());
			map.put("data",data);
		}else{
			map.put("code", "201");
			data.put("message", "暂无连队信息");
			map.put("data",data);
		}
		
		return map;
	} 
	
	@ResponseBody
	@RequestMapping("/test1")
	public ArrayList<Department> test1()
	{	
		//System.out.println(teacherService.selectByPrimaryKey(10001L).getRole());
		PageInfo<Teacher> pageInfo = new PageInfo<>(1,10);
		//int records = teacherService.getTotalCountByType(1);
		//pageInfo.setTotalRecords(records);
		//ArrayList<Teacher> result = teacherService.getAllTcInfoByRoleType(pageInfo, roleType);
		
		return departmentService.getAllDeptInfo();
	} 
	
}

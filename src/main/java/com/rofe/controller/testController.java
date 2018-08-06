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

import com.rofe.pojo.Course;
import com.rofe.pojo.Scheduleinfo;
import com.rofe.pojo.Student;
import com.rofe.service.ClassInfoService;
import com.rofe.service.CourseService;
import com.rofe.service.ScheduleInfoService;
import com.rofe.service.StudentService;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonData;

@Controller
@RequestMapping("/testController")
public class testController {
	@Autowired
	ClassInfoService classInfoService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	StudentService studentService;
	@Autowired
	ScheduleInfoService scheduleInfoService;
	@Autowired
	CourseService courseService;
	
	@ResponseBody
	@RequestMapping("/test")
	public HashMap<String,Object> test(@RequestParam(value = "file", required = false) MultipartFile file,Long userId,String fileId,ModelMap model)
	{
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
        try {
        	String originalFilename=file.getOriginalFilename();
    		int start=originalFilename.indexOf(".");
    		String fileType=originalFilename.substring(start);
            String fileName = userId+"_"+fileId+fileType;  
            File targetFile = new File(CommonData.SHAREBASEURL, fileName);  
            if(!targetFile.exists()){  
                targetFile.mkdirs();  
            }
        	//保存文件
            file.transferTo(targetFile);  
        } catch (Exception e) {  
        	map.put("code",202);
			data.put("message","照片上传异常，请稍后重试");
			map.put("data", data);
            e.printStackTrace();  
        }  
        return map;  
	}
	@ResponseBody
	@RequestMapping("/test1")
	public String test1(HttpServletRequest request)
	{	
		return "test1";
	} 
	
}

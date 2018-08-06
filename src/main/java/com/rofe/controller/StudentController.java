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
import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.CourseScoreService;
import com.rofe.service.CourseService;
import com.rofe.service.StudentService;
import com.rofe.util.CommonTools;


@Controller
@RequestMapping("/studentController")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	
	
	
	
}

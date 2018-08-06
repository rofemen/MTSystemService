/**
 * @author:郑日枋
 * @time:2017年10月19日 下午1:07:47
 * @filename:LoginController.java
 */
package com.rofe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.subject.Subject;
import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.StudentService;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonTools;

@Controller
public class LoginController {
 /*@ResponseBody
	@RequestMapping("/login")
	public String login(Model model,HttpServletRequest req,HttpServletResponse res){
		Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
        	String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
            String error = null;
            if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error = "用户不存在";
            } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = "用户名或者密码错误";
            }else if(LockedAccountException.class.getName().equals(exceptionClassName)||
            		ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
                error = "该账户登陆错误次数过多，已经被禁用，请联系管理员";
            }else if(exceptionClassName != null) {
                error = "其他错误" + exceptionClassName;
            }
            model.addAttribute("error", error);
        	return "login";
        }else{
        	return "redirect:welcome";
        }
	}
	*/
	@Autowired
	StudentService studentService;
	@Autowired
	TeacherService teacherService;
	
	@ResponseBody
	@RequestMapping(value="/login")
	public HashMap<String, Object> login(@RequestBody Map<String,String> requestMap,HttpServletRequest req,HttpServletResponse res){
		String username = requestMap.get("username");
		String password = requestMap.get("password");
		String userType = requestMap.get("userType");
		System.out.println(username+password+userType);
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> data = new HashMap<>();
		if(!CommonTools.isEmpty(username) 
			&& !CommonTools.isEmpty(password) 
			&& !CommonTools.isEmpty(userType)){
			if("stu".equals(userType)){
				Student student = studentService.selectByStuNumOrUsername(null, username);
				String tempPassword = CommonTools.encrypt(password, username, 2);
				if(student == null){
					map.put("code", 201);
					data.put("message", "账号不存在");
					map.put("data", data);
				}else{
					if(student.getStuPassword().equals(tempPassword)){
						map.put("code", 200);
						data.put("uid", student.getStuUserid());
						data.put("sessionId", req.getSession().getId());
						map.put("data", data);
					}else{
						map.put("code", 201);
						data.put("message", "用户名或者密码错误");
						map.put("data", data);
					}
				}
			}else{
				Teacher teacher = teacherService.selectByTcNumOrUsername(null, username);
				String tempPassword = CommonTools.encrypt(password, username, 2);
				if(teacher == null){
					map.put("code", 201);
					data.put("message", "账号不存在");
					map.put("data", data);
				}else{
					if(teacher.getTcPassword().equals(tempPassword)){
						
						map.put("code", 200);
						
						data.put("uid", teacher.getTcUserid());
						data.put("sessionId", req.getSession().getId());
						map.put("data", data);
					}else{
						map.put("code", 201);
						data.put("message", "用户名或者密码错误");
						map.put("data", data);
					}
				}
			}
			return map;
		}
		map.put("code", 500);
		data.put("message", "参数错误");
		map.put("data", data);
		return map;
		
	}
	@RequestMapping("/welcome")
	public String welcome(){
		return "welcome";
	}
	
	@RequestMapping("/")
	public String index(HttpServletRequest req){

		return "welcome";
	}
	
}

/**
 * @author:郑日枋
 * @time:2017年10月19日 下午1:07:47
 * @filename:LoginController.java
 */
package com.rofe.controller;

import java.io.File;
import java.util.ArrayList;
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
import com.rofe.pojo.EmailMessage;
import com.rofe.pojo.Role;
import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.EmailService;
import com.rofe.service.RoleService;
import com.rofe.service.StudentService;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonData;
import com.rofe.util.CommonTools;

@Controller
@RequestMapping("/commonController")
public class CommonController {
	@Autowired
	TeacherService teacherService;
	@Autowired
	StudentService studentService;
	@Autowired
	EmailService emailService;
	@Autowired
	RoleService roleService;
	
	@ResponseBody
	@RequestMapping("/uploadStuIcon")
	public HashMap<String,Object> uploadStuIcon(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request,@RequestParam("userType")String userType,@RequestParam("userId")Long userId,@RequestParam("role")int role)
	{	
		
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
        try {
        	String originalFilename=file.getOriginalFilename();
    		int start=originalFilename.indexOf(".");
    		String fileType=originalFilename.substring(start);
    		String fileName = userType+"_"+userId+fileType;  
    		String finalUrl = CommonData.DOMAINURL+ request.getContextPath()+"/";
            //保存文件
            int result = 0;
    		if("stu".equals(userType)){
    			File targetFile = new File(CommonData.STUICON, fileName);  
	            if(!targetFile.exists()){  
	                targetFile.mkdirs();  
	            }  
	            file.transferTo(targetFile);
    			Student record = new Student();
    			record.setStuUserid(userId);
    			record.setStuExtra1(fileName);
    			result = studentService.updateByPrimaryKeySelective(record);
    			finalUrl += "stuIcon/"; 
     		}else{
     			File targetFile = new File(CommonData.TCICON, fileName);  
	            if(!targetFile.exists()){  
	                targetFile.mkdirs();  
	            }  
	            file.transferTo(targetFile);
     			Teacher record = new Teacher();
     			record.setTcUserid(userId);
     			record.setTcExtra1(fileName);
     			record.setTcRole(role);
     			result = teacherService.updateByPrimaryKeySelective(record);
     			finalUrl += "tcIcon/"; 
     		}
    		finalUrl += fileName;
    		if(result == 0){
    			map.put("code",201);
    			data.put("message","照片上传失败，请稍后重试");
    			data.put("userIcon",finalUrl);
    			map.put("data", data);
    		}else{
    			map.put("code",200);
    			data.put("message","头像上传成功");
    			data.put("userIcon",finalUrl);
    			map.put("data", data);
    		}
        } catch (Exception e) {  
        	map.put("code",202);
			data.put("message","照片上传异常，请稍后重试");
			map.put("data", data);
            e.printStackTrace(); 
            
        }  
    
        return map;  
	}
	
	@RequestMapping("/resetPassWord")
	@ResponseBody
	public HashMap<String,Object> resetPassWord(@RequestBody HashMap<String, Object> params){
		String userType = (String)params.get("userType");
		String prePassword = (String)params.get("prePassword");
		String newPassword = (String)params.get("newPassword");
		Long userId = CommonTools.toLong((int)params.get("userId"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int result = 0;
		if("stu".equals(userType)){
			Student preStudent = studentService.includeSensitiveInfo(userId);
			String nowPassword = preStudent.getStuPassword();
			String tempPassword = CommonTools.encrypt(prePassword, preStudent.getStuUsername(), 2);
			if(nowPassword.equals(tempPassword)){
				preStudent.setStuPrePassword(nowPassword);
				newPassword = CommonTools.encrypt(newPassword, preStudent.getStuUsername(), 2);
				preStudent.setStuPassword(newPassword);
				result = studentService.updateByPrimaryKeySelective(preStudent);
			}else{
				map.put("code", "201");
				data.put("message", "原密码错误，请重新输入");
	 			map.put("data", data);
	 			return map;
			}
		}else{
			Teacher preTeacher = teacherService.includeSensitiveInfo(userId);
			String nowPassword = preTeacher.getTcPassword();
			String tempPassword = CommonTools.encrypt(prePassword, preTeacher.getTcUsername(), 2);
			if(nowPassword.equals(tempPassword)){
				preTeacher.setTcPrePassword(nowPassword);
				newPassword = CommonTools.encrypt(newPassword, preTeacher.getTcUsername(), 2);
				preTeacher.setTcPassword(newPassword);
				result = teacherService.updateByPrimaryKeySelective(preTeacher);
			}else{
				map.put("code", "201");
				data.put("message", "原密码错误，请重新输入");
	 			map.put("data", data);
	 			return map;
			}
		}
		if(result != 0){
			map.put("code", "200");
			data.put("message", "重置密码成功");
			map.put("data", data);
		}else{
			map.put("code", "203");
			data.put("message", "重置密码失败，请稍后重试");
 			map.put("data", data);
		}
		return map;
	}
	
	@RequestMapping("/getReceiveMessage")
	@ResponseBody
	public HashMap<String,Object> getReceiveMessage(@RequestBody HashMap<String, Object> params){
		int idNum = (int)params.get("idNum");
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		String userType = (String)params.get("userType");
		PageInfo<EmailMessage> pageInfo = new PageInfo<>(currentPage,pageSize);
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int count = emailService.getReceiveMessageCount(idNum, userType);
		if(count != 0){
			ArrayList<EmailMessage> list= emailService.getReceiveMessage(pageInfo,idNum, userType);
			if(list != null){
				map.put("code",200);
				data.put("list", list);
				data.put("message","获取收件箱信息成功");
				data.put("totalSize", count);
				data.put("currPage",currentPage);
				data.put("pageSize",pageSize);
				map.put("data", data);
			}else{
				map.put("code",201);
				data.put("message","暂无收件信息");
				map.put("data", data);
			}
		}else{
			map.put("code",201);
			data.put("message","暂无收件信息");
			map.put("data", data);
		}
		return map;
	}
	
	@RequestMapping("/getSendMessage")
	@ResponseBody
	public HashMap<String,Object> getSendMessage(@RequestBody HashMap<String, Object> params){
		int idNum = (int)params.get("idNum");
		String userType = (String)params.get("userType");
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		PageInfo<EmailMessage> pageInfo = new PageInfo<>(currentPage,pageSize);
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int count = emailService.getSendMessageCount(idNum, userType);
		if(count != 0){
			ArrayList<EmailMessage> list= emailService.getSendMessage(pageInfo,idNum, userType);
			if(list != null){
				map.put("code",200);
				data.put("list", list);
				data.put("message","获取发件箱信息成功");
				data.put("totalSize", count);
				data.put("currPage",currentPage);
				data.put("pageSize",pageSize);
				map.put("data", data);
			}else{
				map.put("code",201);
				data.put("message","暂无发件信息");
				map.put("data", data);
			}
		}else{
			map.put("code",201);
			data.put("message","暂无发件信息");
			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/batchDelEmInfo")
	public HashMap<String, Object> batchDelEmInfo(@RequestBody HashMap<String,Object> params){
		ArrayList<Long> messageList = (ArrayList<Long>)params.get("messageList");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		int temp = emailService.batchDelEmInfo(messageList);
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
	
	
	@RequestMapping("/systemResetPassWord")
	@ResponseBody
	public HashMap<String,Object> systemResetPassWord(@RequestBody HashMap<String, Object> params){
		String userType = (String)params.get("userType");
		String newPassword = (String)params.get("newPassword");
		String prePassword = (String)params.get("prePassword");
		Long userId = CommonTools.toLong((int)params.get("userId"));
		String userName = (String)params.get("userName");
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int result = 0;
		if("stu".equals(userType)){
			Student student = new Student();
			student.setStuPrePassword(prePassword);
			student.setStuUserid(userId);
			newPassword = CommonTools.encrypt(newPassword,userName, 2);
			student.setStuPassword(newPassword);
			result = studentService.updateByPrimaryKeySelective(student);
		}else{
			Teacher teacher = new Teacher();
			teacher.setTcUserid(userId);
			teacher.setTcPrePassword(prePassword);
			newPassword = CommonTools.encrypt(newPassword,userName, 2);
			teacher.setTcPassword(newPassword);
			result = teacherService.updateByPrimaryKeySelective(teacher);
		}
		if(result != 0){
			map.put("code", "200");
			data.put("message", "重置密码成功");
			map.put("data", data);
		}else{
			map.put("code", "203");
			data.put("message", "重置密码失败，请稍后重试");
 			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getRolesPermission")
	public HashMap<String, Object> getRolesPermission(@RequestBody HashMap<String,Object> params){
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,Object> data=new HashMap<>();
		ArrayList<Role> list = roleService.getRofeInfo();
		if(list.size() != 0){
			map.put("code", "200");
			data.put("message", "获取权限成功");
			data.put("list", list);
			map.put("data",data);
		}else{
			map.put("code", "201");
			data.put("message", "获取权限失败，请稍后重试");
			map.put("data",data);
		}
		return map;
	}
}

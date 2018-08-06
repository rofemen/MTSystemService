/**
 * @author:郑日枋
 * @time:2017年10月19日 上午10:16:18
 * @filename:UserController.java
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rofe.common.PageInfo;
import com.rofe.pojo.ClassInfo;
import com.rofe.pojo.Role;
import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.ClassInfoService;
import com.rofe.service.RoleService;
import com.rofe.service.StudentService;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonData;
import com.rofe.util.CommonTools;

@Controller
@RequestMapping("/stuUserController")
public class StuUserController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ClassInfoService classInfoService;
	@ResponseBody
	@RequestMapping("/getStudentInfoByTcNum")
	public HashMap<String,Object> getStudentInfoByTcNum(@RequestBody HashMap<String,Object> params){
		Long tcNum = CommonTools.toLong((int)params.get("tcNum"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Student> list = studentService.getStudentInfoByTcNum(tcNum);
		if(list != null){
			map.put("code", "200");
			data.put("message", "获取班级学生信息");
			data.put("list",list);
			map.put("data",data);
		}else{
			map.put("code", 201); 
			data.put("message", "暂无学生信息");
			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getStudentInfoByTcNumForShow")
	public HashMap<String,Object> getStudentInfoByTcNumForShow(@RequestBody HashMap<String,Object> params){
		Long tcNum = CommonTools.toLong((int)params.get("tcNum"));
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		String stuName = (String)params.get("name");
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		PageInfo<Student> pageInfo = new PageInfo<>(currentPage,pageSize);
		int totalSize = studentService.getStudentInfoByTcNumForShowCount(tcNum,stuName);
		if(totalSize != 0){
			ArrayList<Student> list = studentService.getStudentInfoByTcNumForShow(pageInfo,tcNum,stuName);
			map.put("code", "200");
			data.put("message", "获取班级学生信息");
			data.put("totalSize", totalSize);
			data.put("currPage",currentPage);
			data.put("pageSize",pageSize);
			data.put("list",list);
			map.put("data",data);
		}else{
			map.put("code", 201); 
			data.put("message", "暂无学生信息");
			map.put("data", data);
		}
		return map;
	}
	
	@RequestMapping("/getStuInfo")
	@ResponseBody
	public HashMap<String,Object> getStuInfo(Long stuId){
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		if(stuId != 0){
			Student student = studentService.selectByPrimaryKey(stuId);
			if(student != null){
				Role stuRole = roleService.selectByPrimaryKey(3L);
				String authority= stuRole.getRoleAuthority();
				HashMap<String,Object> permission = new HashMap<>();
				if(authority != null){
					String tempPermission[] = authority.split(":");
					for(int i = 0;i < tempPermission.length; i++){
						permission.put(tempPermission[i],true);	
					}
				}
				map.put("code", "200");
				data.put("message", "查询用户信息");

				data.put("userInfo",student);
				data.put("userPermission",permission);
				map.put("data",data);
			}else{
				map.put("code", "201");
				data.put("message", "暂无该学生信息");
	 			map.put("data", data);
			}
		}else{
			map.put("code", "201");
			data.put("message", "参数错误");
 			map.put("data", data);
		}
		
		return map;
	}
	
	@RequestMapping("/updateStuInfo")
	@ResponseBody
	public HashMap<String,Object> updateStuInfo(@RequestBody Student student){
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int temp = studentService.updateByPrimaryKeySelective(student);
		if(temp != 0){
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
	
	@RequestMapping("/getAllStuInfo")
	@ResponseBody
	public HashMap<String,Object> getAllStuInfo(@RequestBody HashMap<String,Object> params){
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		String stuName = (String)params.get("name");
		Long stuDepartment = 0L;
		if(!params.get("deptValue").toString().equals("")){
			stuDepartment = Long.parseLong(params.get("deptValue").toString());
		}
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		PageInfo<Student> pageInfo = new PageInfo<>(currentPage,pageSize);
		int records = studentService.getAllStuCount(stuName, stuDepartment);
		if(records != 0){
			pageInfo.setTotalRecords(records);
			ArrayList<Student> result = studentService.getAllStuInfo(pageInfo, stuName, stuDepartment);
			if(result.size() != 0){
				map.put("code", "200");
				data.put("message", "获取学生信息成功");
				data.put("list",result);
				data.put("totalSize", records);
				data.put("currPage",currentPage);
				data.put("pageSize",pageSize);
				map.put("data",data);
			}else{
				map.put("code", 202); 
				data.put("message", "暂无学生信息");
				map.put("data", data);
			}
		}else{
			map.put("code", 202); 
			data.put("message", "暂无用户信息");
			map.put("data", data);
		}	
		return map;
	}
	
	@RequestMapping("/addStudentInfo")
	@ResponseBody
	public HashMap<String,Object> addStudentInfo(@RequestBody Student student){
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
	 	Long stuNum = student.getStuNum();
	 	long stuClass = student.getStuClass();
	 	ClassInfo classInfo = classInfoService.selectByPrimaryKey(stuClass);
	 	if(stuNum != 0){
	 		Student tempStu = studentService.selectByStuNumOrUsername(stuNum, null);
	 		if(tempStu !=null){
	 			map.put("code", "202");
	 			data.put("message", "该学号已经存在");
	 			map.put("data", data);
	 			return map;
	 		}
	 	}
		int temp=studentService.addStudent(student);
		int classCount = classInfo.getClCount();
		classCount++;
		classInfo.setClCount(classCount);
		classInfoService.updateByPrimaryKeySelective(classInfo);
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
	
	@RequestMapping("/updateStudentInfo")
	@ResponseBody
	public HashMap<String,Object> updateStudentInfo(@RequestBody Student student){
		
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();

		int temp = studentService.updateByPrimaryKeySelective(student);
		if(temp != 0){
			map.put("code", "200");
			data.put("message", "修改信息成功");
			//teacher = teacherService.selectByPrimaryKey(teacher.getTcUserid());
			map.put("data", data);
		}else{
			map.put("code", "203");
			data.put("message", "信息修改失败，请稍后重试");
 			map.put("data", data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/delectStudentById")
	public HashMap<String, Object> delectStudentById(@RequestBody HashMap<String,Object> params){
		Long userId = CommonTools.toLong((int)params.get("stuUserid"));
		Long stuClass = CommonTools.toLong((int)params.get("stuClass"));
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		
		int temp = studentService.deleteByPrimaryKey(userId);
		ClassInfo classInfo = classInfoService.selectByPrimaryKey(stuClass);
		int count = classInfo.getClCount();
		count--;
		classInfo.setClCount(count);
		classInfoService.updateByPrimaryKeySelective(classInfo);
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
	@RequestMapping("/batchDelStuInfo")
	public HashMap<String, Object> batchDelStuInfo(@RequestBody HashMap<String,Object> params){
		ArrayList<Integer> userIdList = (ArrayList<Integer>)params.get("userIdList");
		ArrayList<Integer> clCodeList = (ArrayList<Integer>)params.get("clCodeList");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		int temp = studentService.batchDelStuInfo(userIdList,clCodeList);
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
	@RequestMapping("/batchUploadStuInfo")
	public HashMap<String,Object> batchUploadStuInfo(@RequestParam(value = "file", required = false) MultipartFile file,String fileId)
	{
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
        try {
        	String originalFilename=file.getOriginalFilename();
    		int start=originalFilename.indexOf(".");
    		String fileType=originalFilename.substring(start);
            String fileName = fileId+fileType;  
            File targetFile = new File(CommonData.TempFilePath, fileName);  
            if(!targetFile.exists()){  
                targetFile.mkdirs();  
            }
        	//保存文件
            file.transferTo(targetFile);
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap = studentService.batchInsertStuInfo(CommonData.TempFilePath, fileName);
            map.put("code",200);
			data.put("message","批量导入完毕");
			map.put("successCount",resultMap.get("successCount"));
			ArrayList<Long> successNum = (ArrayList<Long>)resultMap.get("successNum");
			map.put("successNum",successNum);
			resultMap.remove("successNum");
			resultMap.remove("successCount");
			map.put("data", resultMap);
			return map;
        } catch (Exception e) {  
        	map.put("code",202);
			data.put("message","文件上传异常，请稍后重试");
			map.put("data", data);
            e.printStackTrace(); 
            return map;
        }  
          
	}
	
	@ResponseBody
	@RequestMapping("/getBatchStuInfo")
	public HashMap<String, Object> getBatchTcInfo(@RequestBody HashMap<String,Object> params){
		ArrayList<Long> stuNum = (ArrayList<Long>)params.get("stuNumList");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,Object> data=new HashMap<>();
		ArrayList<Student> list = studentService.getBatchStuInfo(stuNum);
		if(list.size() != 0){
			map.put("code", "200");
			data.put("message", "通过学号获取学生信息");
			data.put("list", list);
			map.put("data",data);
		}else{
			map.put("code", "201");
			data.put("message", "暂无数据");
			map.put("data",data);
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("/getStudentInfoByStuNum")
	public HashMap<String,Object> getStudentInfoByStuNum(@RequestBody HashMap<String,Object> params){
		Long stuNum = CommonTools.toLong(Integer.parseInt(params.get("num").toString()));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		Student student = studentService.selectByStuNumOrUsername(stuNum,null);
		if(student != null){
			map.put("code", "200");
			data.put("message", "查询用户信息");
			data.put("userInfo",student);
			map.put("data",data);
		}else{
			map.put("code", 201); 
			data.put("message", "暂无该用户");
			map.put("data", data);
		}
		return map;
	}
}

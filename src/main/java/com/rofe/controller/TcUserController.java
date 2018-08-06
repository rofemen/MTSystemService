/**
 * @author:郑日枋
 * @time:2017年10月19日 上午10:16:18
 * @filename:UserController.java
 */
package com.rofe.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rofe.common.PageInfo;
import com.rofe.pojo.CourseScore;
import com.rofe.pojo.Teacher;
import com.rofe.service.CourseScoreService;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonData;
import com.rofe.util.CommonTools;

@Controller
@RequestMapping("/tcUserController")
public class TcUserController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseScoreService courseScoreService;
	
	@ResponseBody
	@RequestMapping("/delectTeacherById")
	public HashMap<String, Object> delectTeacherById(@RequestBody HashMap<String,Object> params){
		Long userId = CommonTools.toLong((int)params.get("tcUserid"));
		int roleId = (int)params.get("roleId");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		int temp = teacherService.deleteTcInfoById(userId,roleId);
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
	@RequestMapping("/getTeacherInfoById")
	public HashMap<String,Object> getTeacherInfoById(Long tcId){
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		Teacher teacher = teacherService.selectByPrimaryKey(tcId);
		if(teacher != null){
			String authority= teacher.getRole().getRoleAuthority();
			HashMap<String,Object> permission = new HashMap<>();
			if(authority != null){
				String tempPermission[] = authority.split(":");
				for(int i = 0;i < tempPermission.length; i++){
					permission.put(tempPermission[i],true);	
				}
			}
			map.put("code", "200");
			data.put("message", "查询用户信息");

			data.put("userInfo",teacher);
			data.put("userPermission",permission);
			map.put("data",data);
		}else{
			map.put("code", 201); 
			data.put("message", "获取用户信息失败");
			map.put("data", data);
		}
		return map;
	}
	
	@RequestMapping("/addTeacherInfo")
	@ResponseBody
	public HashMap<String,Object> addTeacherInfo(@RequestBody Teacher teacher){
		
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		
	 	Long tcNum = teacher.getTcNum();
	 	if(tcNum != 0){
	 		Teacher tempTc = teacherService.selectByTcNumOrUsername(tcNum, null);
	 		if(tempTc !=null){
	 			map.put("code", "202");
	 			data.put("message", "该工号已经存在");
	 			map.put("data", data);
	 			return map;
	 		}
	 	}
		
		int temp=teacherService.insertSelective(teacher);
		if(temp!=0){
			map.put("code", "200");
			data.put("message", "添加成功，默认密码六个六");
			teacher = teacherService.selectByPrimaryKey(teacher.getTcUserid());
			data.put("info",teacher);
			map.put("data", data);
		}else{
			map.put("code", "203");
			data.put("message", "参数异常，添加失败");
 			map.put("data", data);
		}
		return map;
	}
	
	@RequestMapping("/updateTeacherInfo")
	@ResponseBody
	public HashMap<String,Object> updateTeacherInfo(@RequestBody Teacher teacher){
		
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();

		int temp=teacherService.updateByPrimaryKeySelective(teacher);
		if(temp!=0){
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
	@RequestMapping("/getAllTcInfoByRoleType")
	public HashMap<String,Object> getFilterTcInfo(@RequestBody HashMap<String,Object> params){
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		int roleType = (int)params.get("roleType");
		String tcName = (String)params.get("name");
		System.out.println(tcName);
		Long tcDepartment = 0L;
		if(!params.get("deptValue").toString().equals("")){
			tcDepartment = Long.parseLong(params.get("deptValue").toString());
		}
		PageInfo<Teacher> pageInfo = new PageInfo<>(currentPage,pageSize);
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int records = teacherService.getTotalCountByType(roleType,tcName,tcDepartment);
		if(records != 0){
			pageInfo.setTotalRecords(records);
			ArrayList<Teacher> result = teacherService.getAllTcInfoByRoleType(pageInfo, roleType,tcName,tcDepartment);
			if(result.size() != 0){
				map.put("code", "200");
				data.put("message", "获取指定类型用户信息成功");
				data.put("list",result);
				data.put("totalSize", records);
				data.put("currPage",currentPage);
				data.put("pageSize",pageSize);
				map.put("data",data);
			}else{
				map.put("code", 202); 
				data.put("message", "暂无用户信息");
				map.put("data", data);
			}
		}else{
			map.put("code", 202); 
			data.put("message", "暂无用户信息");
			map.put("data", data);
		}	
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/batchDelTcInfo")
	public HashMap<String, Object> batchDelTcInfo(@RequestBody HashMap<String,Object> params){
		ArrayList<Long> userIdList = (ArrayList<Long>)params.get("userIdList");
		int roleId = (int)params.get("roleId");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,String> data=new HashMap<>();
		int temp = teacherService.batchDelTcInfo(userIdList,roleId);
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
	@RequestMapping("/getTeacherByDiIdAndRole")
	public HashMap<String, Object> getTeacherByDiIdAndRole(@RequestBody HashMap<String,Object> params){
		Long diId = CommonTools.toLong((int)params.get("diId"));
		int roleType = (int)params.get("tcRole");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,Object> data=new HashMap<>();
		ArrayList<Teacher> list = teacherService.getTeacherByDiIdAndRole(diId,roleType);
		if(list.size() != 0){
			map.put("code", "200");
			data.put("message", "获取教师信息成功");
			data.put("list", list);
			map.put("data",data);
		}else{
			map.put("code", "201");
			data.put("message", "暂无教师信息");
			map.put("data",data);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getTeacherByDiId")
	public HashMap<String, Object> getTeacherByDiId(@RequestBody HashMap<String,Object> params){
		Long diId = CommonTools.toLong((int)params.get("diId"));
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,Object> data=new HashMap<>();
		ArrayList<Teacher> list = teacherService.getTeacherByDiId(diId);
		if(list.size() != 0){
			map.put("code", "200");
			data.put("message", "获取教师信息成功");
			data.put("list", list);
			map.put("data",data);
		}else{
			map.put("code", "201");
			data.put("message", "暂无教师信息");
			map.put("data",data);
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping("/getStudentScore")
	public HashMap<String, Object> getStudentScore(@RequestBody HashMap<String,Object> params){
		Long tcNum = CommonTools.toLong((int)params.get("tcNum"));
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,Object> data=new HashMap<>();
		ArrayList<CourseScore> list = courseScoreService.getStudentScore(tcNum);
		if(list.size() != 0){
			map.put("code", "200");
			data.put("message", "通过老师工号获取授课学生的成绩");
			data.put("list", list);
			map.put("data",data);
		}else{
			map.put("code", "201");
			data.put("message", "暂无个人成绩信息");
			map.put("data",data);
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("/getBatchTcInfo")
	public HashMap<String, Object> getBatchTcInfo(@RequestBody HashMap<String,Object> params){
		ArrayList<Long> tcNum = (ArrayList<Long>)params.get("tcNumList");
		HashMap<String,Object> map=new HashMap<>();
		HashMap<String,Object> data=new HashMap<>();
		ArrayList<Teacher> list = teacherService.getBatchTcInfo(tcNum);
		map.put("code", "200");
		data.put("message", "通过老师工号获取学生信息");
		data.put("list", list);
		map.put("data",data);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/batchUploadTcInfo")
	public HashMap<String,Object> batchUploadTcInfo(@RequestParam(value = "file", required = false) MultipartFile file,String fileId,int roleType)
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
            resultMap = teacherService.batchAddTcInfo(CommonData.TempFilePath, fileName,roleType);
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
	@RequestMapping("/getTeacherInfoByTcNum")
	public HashMap<String,Object> getTeacherInfoByTcNum(@RequestBody HashMap<String,Object> params){
		Long tcNum = CommonTools.toLong(Integer.parseInt(params.get("num").toString()));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		Teacher teacher = teacherService.selectByTcNumOrUsername(tcNum,null);
		if(teacher != null){
			map.put("code", "200");
			data.put("message", "查询用户信息");
			data.put("userInfo",teacher);
			map.put("data",data);
		}else{
			map.put("code", 201); 
			data.put("message", "暂无该用户");
			map.put("data", data);
		}
		return map;
	}
	
}

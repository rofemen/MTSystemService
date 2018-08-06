/**
 * @author:郑日枋
 * @time:2017年10月19日 上午9:59:43
 * @filename:TeacherService.java
 */
package com.rofe.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.common.PageInfo;
import com.rofe.dao.ClassInfoMapper;
import com.rofe.dao.TeacherMapper;
import com.rofe.pojo.Teacher;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonData;
import com.rofe.util.CommonTools;
import com.rofe.util.ExcelOperate;
import com.rofe.util.ReadExcelUtils;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	ClassInfoMapper classInfoMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return teacherMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Teacher record) {
		return teacherMapper.insert(record);
	}

	@Override
	public int insertSelective(Teacher record) {
		if(record == null){
			return 0;
		}
		String defaultPasw = CommonTools.encrypt(CommonData.DefaultPass,null,1);
		String finnalPass = CommonTools.encrypt(defaultPasw,String.valueOf(record.getTcNum()),2);
		record.setTcPassword(finnalPass);
		record.setTcPrePassword(finnalPass);
		record.setTcUsername(String.valueOf(record.getTcNum()));
		return teacherMapper.insertSelective(record);
	}

	@Override
	public Teacher selectByPrimaryKey(Long id) {
		return teacherMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Teacher record) {
		return teacherMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Teacher record) {
		return teacherMapper.updateByPrimaryKey(record);
	}

	@Override
	public Teacher selectByTcNumOrUsername(Long tcNum, String tcUserName) {
		return teacherMapper.selectByTcNumOrUsername(tcNum, tcUserName);
	}

	@Override
	public int updateLockByUsername(int tcLock, String tcUsername) {
		return teacherMapper.updateLockByUsername(tcLock, tcUsername);
	}

	@Override
	public int getTotalCountByType(int roleType,String tcName,Long tcDepartment) {
		return teacherMapper.getTotalCountByType(roleType,tcName,tcDepartment);
	}

	@Override
	public ArrayList<Teacher>getAllTcInfoByRoleType(PageInfo<Teacher> pageInfo,int roleType,String tcName,Long tcDepartment){
		
		return teacherMapper.getAllTcInfoByRoleType(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()),roleType,tcName,tcDepartment);
	}

	@Override
	public int batchDelTcInfo(ArrayList<Long> userIdList,int roleId) {
		if(roleId == 1){
			classInfoMapper.updateArmyInstorInfoByTcUserId(userIdList);
		}else if(roleId == 2){
			classInfoMapper.updateTutorInfoByTcUserId(userIdList);
		}
		return teacherMapper.batchDelTcInfo(userIdList);
	}

	@Override
	public ArrayList<Teacher> getTeacherByDiIdAndRole(Long diId, int tcRole) {
		return teacherMapper.getTeacherByDiIdAndRole(diId, tcRole);
	}

	@Override
	public ArrayList<Teacher> getTeacherByDiId(Long diId) {
		return teacherMapper.getTeacherByDiId(diId);
	}

	@Override
	public Teacher includeSensitiveInfo(Long id) {
		return teacherMapper.includeSensitiveInfo(id);
	}

	@Override
	public HashMap<String, Object> batchAddTcInfo(String tempFilePath,String fileName,int roleType) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Long> successNum = new ArrayList<Long>();
		if(fileName == null){
			return resultMap;
		}
		int successCount = 0;
		try {  
            String filepath = tempFilePath+fileName;  
            ReadExcelUtils excelReader = new ReadExcelUtils(filepath);  
            Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();  
            for (int i = 1; i <= map.size(); i++) {  
                Map<Integer,Object> temp = map.get(i);
                Teacher teacher = new Teacher();
                String strNum = (String)temp.get(0);
                Long tcNum = 0l;
                if(!"".equals(strNum)){
                	tcNum = (long)Float.parseFloat((String)temp.get(0));
         		    Teacher tc = selectByTcNumOrUsername(tcNum,null);
         		    if(tc != null){
         			   resultMap.put("errorMessage"+i, "第"+i+"条信息的工号 已经存在");
         			   continue;
         		    }
         		    teacher.setTcUsername(String.valueOf(tcNum));
         		    teacher.setTcNum(tcNum);
                }else{
                	resultMap.put("errorMessage"+i, "第"+i+"条信息工号有误");
   					continue;
                }
                
     		    String deptName=(String)temp.get(1);
	   			if(!"".equals(deptName)){
	   				switch(deptName){
	   					case "软件系":{
	   						teacher.setTcDepartment(1l);
	   						break;
	   					}
	   					case "管理系":{
	   						teacher.setTcDepartment(2l);
	   						break;
	   					}
	   					case "网络系":{
	   						teacher.setTcDepartment(3l);
	   						break;
	   					}
	   					case "电子系":{
	   						teacher.setTcDepartment(4l);
	   						break;
	   					}
	   					case "计算机系":{
	   						teacher.setTcDepartment(5l);
	   						break;
	   					}
	   					case "数码媒体系":{
	   						teacher.setTcDepartment(6l);
	   						break;
	   					}
	   					case "游戏系":{
	   						teacher.setTcDepartment(7l);
	   						break;
	   					}
	   					case "国际经贸系":{
	   						teacher.setTcDepartment(8l);
	   						break;
	   					}
	   					case "财会系":{
	   						teacher.setTcDepartment(9l);
	   						break;
	   					}
	   					case "外语系":{
	   						teacher.setTcDepartment(10l);
	   						break;
	   					}
	   					default :{
	   						teacher.setTcDepartment(-1l);
	   					} 
	   				}
	   				if(teacher.getTcDepartment() == -1){
	   					resultMap.put("errorMessage"+i, "第"+i+"条信息系别有误");
	   					continue;
	   				}
	   			}else{
	   				resultMap.put("errorMessage"+i, "第"+i+"条信息系别有误");
   					continue;
	   			}
	   			String name=(String)temp.get(2);
				if(!"".equals(name)){
					teacher.setTcName(name);
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息名字为空");
					continue;
				}
				String tel=(String)temp.get(3);
				if(!"".equals(tel)){
					Long tempTel = new BigDecimal(Double.parseDouble((String)temp.get(3))).longValue();
					tel = tempTel.toString();
					teacher.setTcTel(tel);
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息联系方式为空");
					continue;
				}

				String email=(String)temp.get(4);
				if(!"".equals(email)){
					teacher.setTcEmail(email);
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息邮箱有误");
					continue;
				}
				Date birthday = null;
				try{
					birthday = (Date)temp.get(5);	
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(birthday != null){
					teacher.setTcBirthday(birthday);
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息出生日期有误");
					continue;
				}
				String sex=(String)temp.get(6);
				if(!"".equals(sex)){
					if(sex.equals("男")){
						teacher.setTcSex((byte)0);
					}else if(sex.equals("女")){
						teacher.setTcSex((byte)1);
					}else{
		     			resultMap.put("errorMessage"+i, "第"+i+"条信息性别有误（只能是男女）");
						continue;
					}
				}else{
	     			resultMap.put("errorMessage"+i, "第"+i+"条信息性别有误（只能是男女）");
					continue;
				}
                teacher.setTcRole(roleType);
                insertSelective(teacher);
                successCount++;
                successNum.add(tcNum);
            }
            resultMap.put("successCount",successCount);
            resultMap.put("successNum",successNum);
            return resultMap;
        }catch (Exception e) {  
        	resultMap.put("errorMessage","上传的excel内容格式有错误，请下载模板内容");
        	resultMap.put("successCount",successCount);
        	e.printStackTrace();
            return resultMap;
        } 
	}

	@Override
	public ArrayList<Teacher> getBatchTcInfo(ArrayList<Long> tcNum) {
		return teacherMapper.getBatchTcInfo(tcNum);
	}

	@Override
	public int deleteTcInfoById(Long id, int roleId) {
		ArrayList<Long> userIdList = new ArrayList<>();
		if(roleId == 1){
			userIdList.add(id);
			classInfoMapper.updateArmyInstorInfoByTcUserId(userIdList);
		}else if(roleId == 2){
			userIdList.add(id);
			classInfoMapper.updateTutorInfoByTcUserId(userIdList);
		}
		return teacherMapper.deleteByPrimaryKey(id);
	}

}

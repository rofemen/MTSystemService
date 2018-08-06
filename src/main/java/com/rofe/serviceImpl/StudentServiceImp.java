/**
 * @author:郑日枋
 * @time:2017年10月19日 下午10:47:53
 * @filename:StudentServiceImp.java
 */
package com.rofe.serviceImpl;

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
import com.rofe.dao.ComentDetailMapper;
import com.rofe.dao.DiaryInfoMapper;
import com.rofe.dao.ShareMapper;
import com.rofe.dao.StudentMapper;
import com.rofe.pojo.ClassInfo;
import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.StudentService;
import com.rofe.util.CommonData;
import com.rofe.util.CommonTools;
import com.rofe.util.ReadExcelUtils;

@Service
public class StudentServiceImp implements StudentService{
	
	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	ShareMapper shareMapper;
	
	@Autowired
	ClassInfoMapper classInfoMapper;
	
	@Autowired
	ComentDetailMapper comentDetailMapper;
	
	@Autowired
	DiaryInfoMapper diaryInfoMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		shareMapper.deleteByPublisher(id);
		Student stu = studentMapper.selectById(id);
		diaryInfoMapper.delDiaryPublicer(stu.getStuNum());
		comentDetailMapper.deleteBySendIdOrReceId(id);
		return studentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Student record) {
		return studentMapper.insert(record);
	}

	@Override
	public int insertSelective(Student record) {
		return studentMapper.insertSelective(record);
	}

	@Override
	public Student selectByPrimaryKey(Long id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Student record) {
		return studentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Student record) {
		return studentMapper.updateByPrimaryKey(record);
	}

	@Override
	public Student selectByStuNumOrUsername(Long stuNum, String stuUsername) {
		
		return studentMapper.selectByStuNumOrUsername(stuNum, stuUsername);
	}

	@Override
	public int updateLockByUsername(int stuLock, String stuUsername) {
		return studentMapper.updateLockByUsername(stuLock, stuUsername);
	}

	@Override
	public Long[] stuNumIsExists(String[] stuNum) {
		return studentMapper.stuNumIsExists(stuNum);
	}

	@Override
	public ArrayList<Student> getStudentInfoByTcNum(Long tcNum) {
		return studentMapper.getStudentInfoByTcNum(tcNum);
	}

	@Override
	public int getTotalCountByClass(Long clCode) {
		return studentMapper.getTotalCountByClass(clCode);
	}

	@Override
	public Student includeSensitiveInfo(Long id) {
		return studentMapper.includeSensitiveInfo(id);
	}

	@Override
	public ArrayList<Student> getAllStuInfo(PageInfo<Student> pageInfo,String stuName, Long stuDeptid) {
		return studentMapper.getAllStuInfo(
				new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()), stuName, stuDeptid);
	}

	@Override
	public int getAllStuCount(String stuName, Long stuDeptid) {
		return studentMapper.getAllStuCount(stuName,stuDeptid);
	}
	
	@Override
	public int addStudent(Student record) {
		if(record == null){
			return 0;
		}
		String defaultPasw = CommonTools.encrypt(CommonData.DefaultPass,null,1);
		String finnalPass = CommonTools.encrypt(defaultPasw,String.valueOf(record.getStuNum()),2);
		record.setStuPassword(finnalPass);
		record.setStuPrePassword(finnalPass);
		record.setStuUsername(String.valueOf(record.getStuNum()));
		return studentMapper.insertSelective(record);
	}

	@Override
	public int batchDelStuInfo(ArrayList<Integer> userIdList,ArrayList<Integer> clCodeList) {
		for(int clCode : clCodeList){
			ClassInfo classInfo = classInfoMapper.selectById(CommonTools.toLong(clCode));
			int classCount = classInfo.getClCount();
			classCount--;
			classInfo.setClCount(classCount);
			classInfoMapper.updateByPrimaryKeySelective(classInfo);
		}
		for(int userId:userIdList){
			shareMapper.deleteByPublisher(CommonTools.toLong(userId));
			Student stu = studentMapper.selectById(CommonTools.toLong(userId));
			diaryInfoMapper.delDiaryPublicer(stu.getStuNum());
			comentDetailMapper.deleteBySendIdOrReceId(CommonTools.toLong(userId));
		}
		return studentMapper.batchDelStuInfo(userIdList);
	}

	@Override
	public HashMap<String, Object> batchInsertStuInfo(String tempFilePath,
			String fileName) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Long> successNum = new ArrayList<Long>();
		if(fileName == null || "".equals(fileName)){
			return resultMap;
		}
		int successCount = 0;
		try {  
            String filepath = tempFilePath+fileName;  
            ReadExcelUtils excelReader = new ReadExcelUtils(filepath);  
            Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();  
            for (int i = 1; i <= map.size(); i++) {  
                Map<Integer,Object> temp = map.get(i);
                Student student = new Student();
                String strNum = (String)temp.get(0);
                Long stuNum = 0l;
                if(!"".equals(strNum)){
                	stuNum = new BigDecimal(Double.parseDouble((String)temp.get(0))).longValue();
                	Student stu = selectByStuNumOrUsername(stuNum,null);
         		    if(stu != null){
         			   resultMap.put("errorMessage"+i, "第"+i+"条信息的学号已经存在");
         			   continue;
         		    }
         		   student.setStuUsername(String.valueOf(stuNum));
         		   student.setStuNum(stuNum);
                }else{
                	resultMap.put("errorMessage"+i, "第"+i+"条信息学号有误");
   					continue;
                }
                
     		    String deptName=(String)temp.get(1);
	   			if(!"".equals(deptName)){
	   				switch(deptName){
	   					case "软件系":{
	   						student.setStuDeptid(1);
	   						break;
	   					}
	   					case "管理系":{
	   						student.setStuDeptid(2);
	   						break;
	   					}
	   					case "网络系":{
	   						student.setStuDeptid(3);
	   						break;
	   					}
	   					case "电子系":{
	   						student.setStuDeptid(4);
	   						break;
	   					}
	   					case "计算机系":{
	   						student.setStuDeptid(5);
	   						break;
	   					}
	   					case "数码媒体系":{
	   						student.setStuDeptid(6);
	   						break;
	   					}
	   					case "游戏系":{
	   						student.setStuDeptid(7);
	   						break;
	   					}
	   					case "国际经贸系":{
	   						student.setStuDeptid(8);
	   						break;
	   					}
	   					case "财会系":{
	   						student.setStuDeptid(9);
	   						break;
	   					}
	   					case "外语系":{
	   						student.setStuDeptid(10);
	   						break;
	   					}
	   					default :{
	   						student.setStuDeptid(-1);
	   					} 
	   				}
	   				if(student.getStuDeptid() == -1){
	   					resultMap.put("errorMessage"+i, "第"+i+"条信息系别有误");
	   					continue;
	   				}
	   			}else{
	   				resultMap.put("errorMessage"+i, "第"+i+"条信息系别有误");
   					continue;
	   			}
	   			String grade=(String)temp.get(2);
				if(!"".equals(grade)){
					if(Float.parseFloat(grade)>=2014 && Float.parseFloat(grade)<=2019)
					{
						student.setStuGrade((int)Float.parseFloat(grade));
					}else{
						resultMap.put("errorMessage"+i, "第"+i+"条信息年级错误，现暂只支持2014~2019");
						continue;
					}
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息年级错误，现暂只支持2014~2019");
					continue;
				}
	   			
	   			String clCode = (String)temp.get(3);
				if(!"".equals(clCode)){
					long tempClCode = (long)Float.parseFloat(clCode);
					ClassInfo cl = classInfoMapper.selectByIdAndGrade((int)Float.parseFloat(grade),tempClCode);
					if(cl == null){
						resultMap.put("errorMessage"+i, "第"+i+"条信息该年级该班级编号不存在");
						continue;
					}else if(cl.getClCount() >= cl.getClMaxcount()){
						resultMap.put("errorMessage"+i, "第"+i+"条信息该班级已经满人了");
						continue;
					}
					student.setStuClass(tempClCode);
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息班级编号为空");
					continue;
				}
				String name = (String)temp.get(4);
				if(!"".equals(name)){
					student.setStuName(name);
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息姓名为空");
					continue;
				}
				String tel=(String)temp.get(5);
				if(!"".equals(tel)){
					Long tempTel = new BigDecimal(Double.parseDouble((String)temp.get(5))).longValue();
					tel = tempTel.toString();
					student.setStuTel(tel);
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息联系方式为空");
					continue;
				}
				String email=(String)temp.get(6);
				if(!"".equals(email)){
					student.setStuEmail(email);
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息邮箱为空");
					continue;
				}
				Date birthday = null;
				try{
					birthday = (Date)temp.get(7);	
				}catch(Exception e){
					e.printStackTrace();
				}

				if(birthday != null){
					student.setStuBirthday(birthday);
				}else{
					resultMap.put("errorMessage"+i, "第"+i+"条信息出生日期有误");
					continue;
				}
				String sex=(String)temp.get(8);
				if(!"".equals(sex)){
					if(sex.equals("男")){
						student.setStuSex((byte)0);
					}else if(sex.equals("女")){
						student.setStuSex((byte)1);
					}else{
		     			resultMap.put("errorMessage"+i, "第"+i+"条信息性别有误（只能是男女）");
						continue;
					}
				}else{
	     			resultMap.put("errorMessage"+i, "第"+i+"条信息性别有误（只能是男女）");
					continue;
				}
			 	ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(student.getStuClass());
			 	int classCount = classInfo.getClCount();
				classCount++;
				classInfo.setClCount(classCount);
				classInfoMapper.updateByPrimaryKeySelective(classInfo);
			 	addStudent(student);
                successCount++;
                successNum.add(stuNum);
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
	public ArrayList<Student> getBatchStuInfo(ArrayList<Long> stuNum) {
		return studentMapper.getBatchStuInfo(stuNum);
	}

	@Override
	public ArrayList<Student> getStudentInfoByTcNumForShow(
			PageInfo<Student> pageInfo, Long tcNum, String stuName) {
		return studentMapper.getStudentInfoByTcNumForShow(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()),tcNum,stuName);
	}

	@Override
	public int getStudentInfoByTcNumForShowCount(Long tcNum, String stuName) {
		return studentMapper.getStudentInfoByTcNumForShowCount(tcNum,stuName);
	}
}

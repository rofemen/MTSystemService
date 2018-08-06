/**
 * @author:郑日枋
 * @time:2017年10月17日 下午10:15:33
 * @filename:ClassInfoServiceImpl.java
 */
package com.rofe.serviceImpl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.common.PageInfo;
import com.rofe.dao.ClassInfoMapper;
import com.rofe.dao.StudentMapper;
import com.rofe.pojo.ClassInfo;
import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.ClassInfoService;

@Service
public class ClassInfoServiceImpl implements ClassInfoService{
	@Autowired
	ClassInfoMapper classInfoMapper;
	@Autowired
	StudentMapper studentMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		studentMapper.deleteStuInfoByClCode(id);
		return classInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ClassInfo record) {
		return classInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(ClassInfo record) {
		return classInfoMapper.insertSelective(record);
	}

	@Override
	public ClassInfo selectByPrimaryKey(Long id) {
		return classInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ClassInfo record) {
		return classInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ClassInfo record) {
		return classInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public ArrayList<ClassInfo> getAllClassInfo(PageInfo<Teacher> pageInfo,String clGrade) {
		return classInfoMapper.getAllClassInfo(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()),clGrade);
	}

	@Override
	public int getClassInfoCount(String clGrade) {
		return classInfoMapper.getClassInfoCount(clGrade);
	}

	@Override
	public ArrayList<String> getAllGrade(String clGrade) {
		return classInfoMapper.getAllGrade(clGrade);
	}

	@Override
	public int batchDelClInfo(ArrayList<Long> clCodeList) {
		studentMapper.deleteStuInfoByClCodeList(clCodeList);
		return classInfoMapper.batchDelClInfo(clCodeList);
	}

	@Override
	public ClassInfo selectByTcNum(int roleType, Long tcNum) {
		return classInfoMapper.selectByTcNum(roleType, tcNum);
	}

	@Override
	public ArrayList<ClassInfo> getAllClassInfoForAdd(String clGrade,
			Long clDpId) {
		return classInfoMapper.getAllClassInfoForAdd(clGrade, clDpId);
	}
}

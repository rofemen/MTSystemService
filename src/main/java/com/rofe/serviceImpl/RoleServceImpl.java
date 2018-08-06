/**
 * @author:郑日枋
 * @time:2018年2月9日 上午10:49:21
 * @filename:RoleServceImpl.java
 */
package com.rofe.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.dao.RoleMapper;
import com.rofe.pojo.Role;
import com.rofe.service.RoleService;
@Service
public class RoleServceImpl implements RoleService{
	@Autowired
	RoleMapper roleMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Role record) {
		return roleMapper.insert(record);
	}

	@Override
	public int insertSelective(Role record) {
		return roleMapper.insertSelective(record);
	}

	@Override
	public Role selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		return roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		return roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public ArrayList<Role> getRofeInfo() {
		
		return roleMapper.getRofeInfo();
	}
}

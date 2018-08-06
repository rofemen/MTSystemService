/**
 * @author:郑日枋
 * @time:2018年2月9日 上午10:47:53
 * @filename:RoleService.java
 */
package com.rofe.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rofe.pojo.Role;

public interface RoleService extends BaseService<Role>{
	ArrayList<Role> getRofeInfo();
}

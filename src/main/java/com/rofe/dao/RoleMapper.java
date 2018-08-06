package com.rofe.dao;

import java.util.ArrayList;

import com.rofe.pojo.Role;

public interface RoleMapper extends BaseMapper<Role>{
    ArrayList<Role> getRofeInfo();
}
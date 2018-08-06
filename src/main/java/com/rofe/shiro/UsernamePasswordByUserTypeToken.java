/**
 * @author:郑日枋
 * @time:2017年10月19日 下午4:29:54
 * @filename:UsernamePasswordByUserTypeToken.java
 */
package com.rofe.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;


public class UsernamePasswordByUserTypeToken extends UsernamePasswordToken {  
  
    private static final long serialVersionUID = 1L;
	/* 
     * 用户类型 
     * tc:教师用户
     * stu:学生用户 
     */  
    private String userType;  
  
    public String getUserType() {  
        return userType;  
    }  
  
    public void setUserType(String userType) {  
        this.userType = userType;  
    }  
  

    
    public UsernamePasswordByUserTypeToken(String username,char[] password,
            boolean rememberMe,String host,String userType){  
        super(username, password,rememberMe,host); 
        this.userType = userType;  
    }  
}  

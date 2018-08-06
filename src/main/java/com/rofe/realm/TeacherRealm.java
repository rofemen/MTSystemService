package com.rofe.realm;


import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.rofe.pojo.Teacher;
import com.rofe.service.TeacherService;
import com.rofe.shiro.UsernamePasswordByUserTypeToken;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class TeacherRealm extends AuthorizingRealm {

    @Autowired
    private TeacherService teacherService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

    	/*
    	String username = (String)principals.getPrimaryPrincipal();

        Set<String> roles=new HashSet<>();
        	roles.add("tc");
         	authorizationInfo.setRoles(roles);
        	//authorizationInfo.setStringPermissions(userService.findPermissions(username));
       */
        return authorizationInfo;
    }

	@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	
    	UsernamePasswordByUserTypeToken myToken=(UsernamePasswordByUserTypeToken)token;
        String username = (String)token.getPrincipal();

        Teacher user = teacherService.selectByTcNumOrUsername(null, username);
        
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        
        if(1==user.getTcLock()) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getTcUsername()+"_tc", //用户名
                user.getTcPassword(), //密码
                ByteSource.Util.bytes(user.getTcUsername()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}

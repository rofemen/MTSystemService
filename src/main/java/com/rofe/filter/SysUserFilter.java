package com.rofe.filter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.rofe.service.StudentService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {
	
	@Autowired
    private StudentService userService;
	
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        System.out.println(request.getServletContext().getAttribute(username+"userType"));
       // String userType = (String)request.getParameter("userType");
        //request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}

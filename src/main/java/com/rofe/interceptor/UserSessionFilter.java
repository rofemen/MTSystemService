package com.rofe.interceptor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.StudentService;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonData;

public class UserSessionFilter extends AccessControlFilter {
	@Autowired
	TeacherService teacherService;
	@Autowired
	StudentService studentService;
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		Subject subject = getSubject(request, response);
		if (subject == null) {
			// 没有登录
			return false;
		}
		HttpSession session = WebUtils.toHttp(request).getSession();
		Object sessionUsername = session.getAttribute(CommonData.CURRENTUSER);
		if (sessionUsername == null) {
			Subject currentUser = SecurityUtils.getSubject();
	    	String username = (String)currentUser.getPrincipal();
	    	//System.out.println("--------------"+username);
	    	String username_type[]=username.split("_");
	    	if("stu".equals(username_type[1])){
				Student user=studentService.selectByStuNumOrUsername(null,username);
				session.setAttribute("userType", "stu");
				session.setAttribute(CommonData.CURRENTUSER,user);

			}else if("tc".equals(username_type[1])){
				Teacher user=teacherService.selectByTcNumOrUsername(null, username);
				session.setAttribute("userType", "tc");
				session.setAttribute(CommonData.CURRENTUSER,user);
			}
		}
		return true;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		return true;
	}
}
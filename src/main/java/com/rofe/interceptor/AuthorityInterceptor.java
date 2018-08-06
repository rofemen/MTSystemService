/**
 * @author:郑日枋
 * @time:2017年3月19日 下午7:58:02
 * @filename:AuthorityInterceptor.java
 */
package com.rofe.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthorityInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		boolean isOpenSession=false;
		if(isOpenSession){
			HttpSession session = request.getSession();
			if (session.getAttribute("sessionId") == null|| 
				!session.getId().equals(session.getAttribute("sessionId"))) {
				System.out.println("没登陆");
				session.setAttribute("sessionId",session.getId());
				PrintWriter out=response.getWriter();
				out.println("{'code':'200'}");
				return false;
			}else{
				int i=0;
				session.setAttribute("index", i);
				//System.out.println(session.getAttribute("sessionId"));
				return true;
			}
		}
		return true;
	}
	
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	

}

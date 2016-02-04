package com.henry.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor {
	
	/**
	 * 在调用目标方法之前执行。
	 * 若返回false,则后续拦截器和方法都不会执行。
	 * 返回true，则继续执行。
	 * 
	 * 可以做权限，日志等。
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("preHandle");
		return true;
	}
	
	/**
	 * 在调用目标方法之后执行。
	 * 在渲染视图之前执行。 
	 * 
	 * 可以对域中的属性或者视图做修改。
	 */
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
	}
	
	/*
	 * 在渲染视图之后执行。
	 * 
	 * 可以释放资源。
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
	}


	

}

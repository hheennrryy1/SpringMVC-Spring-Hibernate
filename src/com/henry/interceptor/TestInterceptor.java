package com.henry.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor {
	
	/**
	 * �ڵ���Ŀ�귽��֮ǰִ�С�
	 * ������false,������������ͷ���������ִ�С�
	 * ����true�������ִ�С�
	 * 
	 * ������Ȩ�ޣ���־�ȡ�
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("preHandle");
		return true;
	}
	
	/**
	 * �ڵ���Ŀ�귽��֮��ִ�С�
	 * ����Ⱦ��ͼ֮ǰִ�С� 
	 * 
	 * ���Զ����е����Ի�����ͼ���޸ġ�
	 */
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
	}
	
	/*
	 * ����Ⱦ��ͼ֮��ִ�С�
	 * 
	 * �����ͷ���Դ��
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
	}


	

}

package com.henry.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithmeticException(Exception e) {
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("exception", e);
		return mav;
	}

	@ExceptionHandler
	public ModelAndView handleException(Exception e) {
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("exception", e);
		return mav;		
	}
}

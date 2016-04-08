package com.henry.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.henry.entity.User;
import com.henry.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getUser")
	public ModelAndView getUser(ModelAndView mav) {
		mav.setViewName("userList");
		mav.addObject("userList", userService.get());
		return mav;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") int id) {
		User user = userService.getById(id);
		userService.delete(user);
		return "redirect:/user/getUser";
	}
	
	/**
	 *	���saveUser,��ת���������ݵ�ҳ��  
	 */
	@RequestMapping("/inputUser") 
	public ModelAndView inputUser(ModelAndView mav) {
		mav.setViewName("inputUser");
		User user = new User();
		mav.addObject("user", user); //form����ǩҪ��ʹ��ǰ�����б�����һ�������������ͱ�ǩ�󶨣������ݵĻ�����ԡ�
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result) {
		if(result.hasErrors()) {
			for(FieldError error:result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "inputUser";
		}
		userService.save(user);
		return "redirect:/user/getUser";
	}
	
	/**
	 * ���update��ת���������,��ʱҪ���ݻ��ԣ���˰�user�Ž�����
	 */
	@RequestMapping(value="/{id}")
	public ModelAndView inputUser(@PathVariable("id")int id, ModelAndView mav) {
		mav.setViewName("inputUser");
		mav.addObject("user", userService.getById(id));
		return mav;
	}
	
	/**
	 *  ModelAttribute��ǵķ����� ÿһ�η��� �κ�һ��controller�ķ���ǰ����ִ�У�ע����ÿһ�Σ�
	 *  ע��required,���ûid,�Զ���ֵΪnull,��˲�����int
	 *  �������Ϊ�����password,�ϲ�����.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id", required=false)Integer id, Map<String, Object> map) {
		if(id!=null) {
			map.put("user", userService.getById(id));
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public String updateUser(User user) {
		userService.update(user);
		return "redirect:/user/getUser";
	}
	
	/**
	 * �������ExceptionHandler��HandlerExceptionResolver(DispatcherServletĬ��װ��)
	 * 
	 * 1. ����ڵ�ǰcontroller���Ҳ���@ExceptionHandler,��ȥ @ControllerAdvice ��ǵ������� @ExceptionHandler.
	 * 2. @ExceptionHandler ��ǵķ�����β���ΪMap��ModelAndView�ȵ�,����newһ��ModelAndView.
	 */
	
	/*
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithmeticException(Exception e) {
		ModelAndView mav = new ModelAndView("error");
		mav.setViewName("error");
		mav.addObject("exception", e);
		return mav;
	}
	*/
	
	@RequestMapping("/testHandlerExceptionResolver")
	public String testHandlerExceptionResolver(@RequestParam("i")int i) {
		System.out.println(10 / i);
		return "redirect:/user/getUser";
	}
	
	/**
	 *@ResponseStatusҲ���Է���������� 
	 */
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="no reason")
	@RequestMapping("/testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver() {
		return "redirect:/user/getUser";
	}
	
	/**
	 *���� SimpleMappingExceptionResolver
	 */
	@RequestMapping("/testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i") int i){
		String [] vals = new String[10];
		System.out.println(vals[i]);
		return "success";
	}
}

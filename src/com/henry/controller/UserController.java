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
	 *	点击saveUser,跳转到输入数据的页面  
	 */
	@RequestMapping("/inputUser") 
	public ModelAndView inputUser(ModelAndView mav) {
		mav.setViewName("inputUser");
		User user = new User();
		mav.addObject("user", user); //form表单标签要求使用前在域中必须有一个对象，这个对象和标签绑定，有数据的话会回显。
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
	 * 点击update后转向输入界面,这时要数据回显，因此把user放进域里
	 */
	@RequestMapping(value="/{id}")
	public ModelAndView inputUser(@PathVariable("id")int id, ModelAndView mav) {
		mav.setViewName("inputUser");
		mav.addObject("user", userService.getById(id));
		return mav;
	}
	
	/**
	 *  ModelAttribute标记的方法在 每一次访问 任何一个controller的方法前会先执行，注意是每一次！
	 *  注意required,如果没id,自动赋值为null,因此不能用int
	 *  这个方法为了填充password,合并对象.
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
	 * 下面测试ExceptionHandler和HandlerExceptionResolver(DispatcherServlet默认装配)
	 * 
	 * 1. 如果在当前controller中找不到@ExceptionHandler,则去 @ControllerAdvice 标记的类中找 @ExceptionHandler.
	 * 2. @ExceptionHandler 标记的方法入参不能为Map和ModelAndView等等,可以new一个ModelAndView.
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
	 *@ResponseStatus也可以放在类的上面 
	 */
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="no reason")
	@RequestMapping("/testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver() {
		return "redirect:/user/getUser";
	}
	
	/**
	 *测试 SimpleMappingExceptionResolver
	 */
	@RequestMapping("/testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i") int i){
		String [] vals = new String[10];
		System.out.println(vals[i]);
		return "success";
	}
}

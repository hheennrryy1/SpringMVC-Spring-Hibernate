package com.henry.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		mav.addObject("user", new User()); //form表单标签要求使用前在域中必须有一个对象，这个对象和标签绑定，有数据的话会回显。
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result) {
		if(result.hasErrors()) {
			for(FieldError error:result.getFieldErrors()){
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
	 *  ModelAttribute标记的方法在 每一次访问 任何一个controller的方法前回先执行，注意是每一次！
	 *  注意required,如果没id,自动赋值为null,因此不能用int
	 *  这个方法为了填充password,合并对象.
	 */
	@ModelAttribute("user")
	public User getUser(@RequestParam(value="id",required=false)Integer id) {
		if(id!=null) {
			return userService.getById(id); 
		}
			return null;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public String updateUser(User user) {
		userService.update(user);
		return "redirect:/user/getUser";
	}
}

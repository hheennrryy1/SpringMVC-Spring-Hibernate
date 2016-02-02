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
	 *	���saveUser,��ת���������ݵ�ҳ��  
	 */
	@RequestMapping("/inputUser") 
	public ModelAndView inputUser(ModelAndView mav) {
		mav.setViewName("inputUser");
		mav.addObject("user", new User()); //form����ǩҪ��ʹ��ǰ�����б�����һ�������������ͱ�ǩ�󶨣������ݵĻ�����ԡ�
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

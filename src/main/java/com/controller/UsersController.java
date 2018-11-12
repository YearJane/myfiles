package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.entity.Users;
import com.mapper.UsersMapper;
import com.service.UsersService;

/**
 * user的控制层
 * 
 * @author 陈小锋
 *
 */
@Controller
@RequestMapping
public class UsersController {
	@Autowired
	private UsersService userservice;
	@Autowired
	private UsersMapper usersmapper;

	/**
	 * 验证用户登录
	 */
	@RequestMapping("user/login")
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		ModelAndView m = new ModelAndView();
		Users u=null;
		String loginMessage = "";
		if (userservice.login(username, password)) {
			loginMessage = "登录成功";
			// 保存用户信息(不规范的写法，不推荐)
			 u=usersmapper.login(username);
			//将私密属性置空
			//u.setFilespath(null);
			u.setPassword(null);
			session.setAttribute("userInfo", u);
			m.addObject("username", u.getUsername());
			m.addObject("imagpath", u.getImagpath());
		} else
			loginMessage = "登录失败";
		System.out.println(userservice.login(username, password));
		m.addObject("loginMessage", loginMessage);

		m.setView(new MappingJackson2JsonView());
		return m;
	}

	/**
	 * 用户的注销
	 */
	@RequestMapping("user/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView m = new ModelAndView();
		m.addObject("logoutMessage", "注销成功");
		m.setView(new MappingJackson2JsonView());
		return m;
	}
}

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
 * user�Ŀ��Ʋ�
 * 
 * @author ��С��
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
	 * ��֤�û���¼
	 */
	@RequestMapping("user/login")
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		ModelAndView m = new ModelAndView();
		Users u=null;
		String loginMessage = "";
		if (userservice.login(username, password)) {
			loginMessage = "��¼�ɹ�";
			// �����û���Ϣ(���淶��д�������Ƽ�)
			 u=usersmapper.login(username);
			//��˽�������ÿ�
			//u.setFilespath(null);
			u.setPassword(null);
			session.setAttribute("userInfo", u);
			m.addObject("username", u.getUsername());
			m.addObject("imagpath", u.getImagpath());
		} else
			loginMessage = "��¼ʧ��";
		System.out.println(userservice.login(username, password));
		m.addObject("loginMessage", loginMessage);

		m.setView(new MappingJackson2JsonView());
		return m;
	}

	/**
	 * �û���ע��
	 */
	@RequestMapping("user/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView m = new ModelAndView();
		m.addObject("logoutMessage", "ע���ɹ�");
		m.setView(new MappingJackson2JsonView());
		return m;
	}
}

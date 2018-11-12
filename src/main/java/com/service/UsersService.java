package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Users;
import com.mapper.UsersMapper;
/**
 * Users的Service
 * @author 陈小锋
 *
 */
@Service
public class UsersService {
	/**
	 * 验证用户登录
	 */
	@Autowired
	private UsersMapper usersmapper;
	public boolean login(String username,String password) {
		Users u=null;
		u=usersmapper.login(username);
		if(null!=u&&password.equals(u.getPassword()))
			return true;
		return false;
	}
}

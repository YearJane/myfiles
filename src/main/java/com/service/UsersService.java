package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Users;
import com.mapper.UsersMapper;
/**
 * Users��Service
 * @author ��С��
 *
 */
@Service
public class UsersService {
	/**
	 * ��֤�û���¼
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

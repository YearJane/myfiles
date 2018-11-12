package com.mapper;

import org.apache.ibatis.annotations.Param;

import com.entity.Users;

/**
 * 用户的mapper
 * @author 陈小锋
 *
 */
public interface UsersMapper {
   /**
    * 验证用户登录
    */
	public Users login(@Param("username") String username) ;
}

package com.mapper;

import org.apache.ibatis.annotations.Param;

import com.entity.Users;

/**
 * �û���mapper
 * @author ��С��
 *
 */
public interface UsersMapper {
   /**
    * ��֤�û���¼
    */
	public Users login(@Param("username") String username) ;
}

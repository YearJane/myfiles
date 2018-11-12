package com.entity;

import java.io.Serializable;

public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//用户的id
	private Integer uid;
	//用户名
	private String username;
	//密码
	private String password;
	//头像地址
	private String imagpath;
	//个性签名
	private String sign;
	//文件夹路径
	private  String filespath;
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getImagpath() {
		return imagpath;
	}

	public void setImagpath(String imagpath) {
		this.imagpath = imagpath;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getFilespath() {
		return filespath;
	}

	public void setFilespath(String filespath) {
		this.filespath = filespath;
	}
	 
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", password=" + password + ", imagpath=" + imagpath
				+ ", sign=" + sign + ", filespath=" + filespath + "]";
	}
	

}

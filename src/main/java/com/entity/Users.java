package com.entity;

import java.io.Serializable;

public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//�û���id
	private Integer uid;
	//�û���
	private String username;
	//����
	private String password;
	//ͷ���ַ
	private String imagpath;
	//����ǩ��
	private String sign;
	//�ļ���·��
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

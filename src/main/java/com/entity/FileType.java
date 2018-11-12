package com.entity;

import java.io.Serializable;

public class FileType implements Serializable {

	private static final long serialVersionUID = 1L;
	//类型id
	private Integer typeid;
	//类型的所属人
	private Integer uid;
	//类型名字
	private String typename;
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Override
	public String toString() {
		return "FileType [typeid=" + typeid + ", uid=" + uid + ", typename=" + typename + "]";
	}
	
}

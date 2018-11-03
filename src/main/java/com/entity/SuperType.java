package com.entity;

public class SuperType {
	private Integer sid;
	private String typename;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public SuperType(Integer sid, String typename) {
		super();
		this.sid = sid;
		this.typename = typename;
	}

	public SuperType() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SuperType [sid=" + sid + ", typename=" + typename + "]";
	}

}

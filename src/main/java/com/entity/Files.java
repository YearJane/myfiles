package com.entity;

import java.io.Serializable;

/**
 * 文件类
 * @author 陈小锋
 *
 */
public class Files implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//文件的id
	private Integer fid;
	//文件所属人
	private Integer uid;
	//文件的真实名字
	private String reallyname;
	//文件的虚拟名字
	private String fakename;
	//文件的类型
	private FileType filetype;
	//文件的权重（默认为0，权重越高，显示越靠前）
	private Integer weight;
	//文件的上传时间
	private String intime;
	//文件是否在回收站（0代表不在回收站，1代表在回收站，默认为0）
	private Integer isrecycle;
	public Integer getIsrecycle() {
		return isrecycle;
	}

	public void setIsrecycle(Integer isrecycle) {
		this.isrecycle = isrecycle;
	}

	//文件的说明
	private  String introduce;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getReallyname() {
		return reallyname;
	}

	public void setReallyname(String reallyname) {
		this.reallyname = reallyname;
	}

	public String getFakename() {
		return fakename;
	}

	public void setFakename(String fakename) {
		this.fakename = fakename;
	}

	public FileType getFiletype() {
		return filetype;
	}

	public void setFiletype(FileType filetype) {
		this.filetype = filetype;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Files [fid=" + fid + ", uid=" + uid + ", reallyname=" + reallyname + ", fakename=" + fakename
				+ ", filetype=" + filetype + ", weight=" + weight + ", intime=" + intime + ", isrecycle=" + isrecycle
				+ ", introduce=" + introduce + "]";
	}
	
	
}

package com.entity;

import java.io.Serializable;

/**
 * �ļ���
 * @author ��С��
 *
 */
public class Files implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//�ļ���id
	private Integer fid;
	//�ļ�������
	private Integer uid;
	//�ļ�����ʵ����
	private String reallyname;
	//�ļ�����������
	private String fakename;
	//�ļ�������
	private FileType filetype;
	//�ļ���Ȩ�أ�Ĭ��Ϊ0��Ȩ��Խ�ߣ���ʾԽ��ǰ��
	private Integer weight;
	//�ļ����ϴ�ʱ��
	private String intime;
	//�ļ��Ƿ��ڻ���վ��0�����ڻ���վ��1�����ڻ���վ��Ĭ��Ϊ0��
	private Integer isrecycle;
	public Integer getIsrecycle() {
		return isrecycle;
	}

	public void setIsrecycle(Integer isrecycle) {
		this.isrecycle = isrecycle;
	}

	//�ļ���˵��
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

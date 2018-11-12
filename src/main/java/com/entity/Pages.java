package com.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ��ҳ
 * 
 * @author ��С��
 *
 */
public class Pages implements Serializable {

	private static final long serialVersionUID = 1L;
	// ��ʼ����
	private Integer fontindex;
	// ��������
	private Integer lastindex;
	// ��ǰҳ��
	private Integer currentpage;
	// ��ҳ��
	private Integer numpages;
	// �ܼ�¼����
	private Integer totalrecords;
	// ҳ�������
	private List<Files> list;
	// ÿҳ��ʾ�ļ�¼��
	private Integer pagesize;

	public Integer getFontindex() {
		return fontindex;
	}

	public void setFontindex(Integer fontindex) {
		this.fontindex = fontindex;
	}

	public Integer getLastindex() {
		return lastindex;
	}

	public void setLastindex(Integer lastindex) {
		this.lastindex = lastindex;
	}

	public Integer getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}

	public Integer getNumpages() {
		return numpages;
	}

	public void setNumpages(Integer numpages) {
		this.numpages = numpages;
	}

	public Integer getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(Integer totalrecords) {
		this.totalrecords = totalrecords;
	}

	public List<Files> getList() {
		return list;
	}

	public void setList(List<Files> list) {
		this.list = list;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	@Override
	public String toString() {
		return "Pages [fontindex=" + fontindex + ", lastindex=" + lastindex + ", currentpage=" + currentpage
				+ ", numpages=" + numpages + ", totalrecords=" + totalrecords + ", list=" + list + ", pagesize="
				+ pagesize + "]";
	}

}

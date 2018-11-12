package com.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * 
 * @author 陈小锋
 *
 */
public class Pages implements Serializable {

	private static final long serialVersionUID = 1L;
	// 起始索引
	private Integer fontindex;
	// 结束索引
	private Integer lastindex;
	// 当前页数
	private Integer currentpage;
	// 总页数
	private Integer numpages;
	// 总记录条数
	private Integer totalrecords;
	// 页面的内容
	private List<Files> list;
	// 每页显示的记录数
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

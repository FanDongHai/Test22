package com.xx.vo;

import java.util.List;

// 分页查询的工具类
public class PageBean<T> {

	// 设置总记录数
	private Integer count;
	// 设置当前页数
	private Integer page;
	// 设置每页查询多少
	private Integer limit;
	// 分页查询到的数据
	private List<T> pageInfos;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<T> getPageInfos() {
		return pageInfos;
	}

	public void setPageInfos(List<T> pageInfos) {
		this.pageInfos = pageInfos;
	}

}

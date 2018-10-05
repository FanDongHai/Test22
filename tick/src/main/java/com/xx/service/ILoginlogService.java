package com.xx.service;

import com.xx.entity.Loginlog;
import com.xx.vo.PageBean;

public interface ILoginlogService {
	
	// 根据页码查询分页的数据
	public PageBean<Loginlog> findLogByPage(int page, int limit);
	
	// 添加
	public void addLoginlog(Loginlog loginlog);

}

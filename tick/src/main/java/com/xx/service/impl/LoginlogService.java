package com.xx.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.dao.ILoginlogDao;
import com.xx.entity.Loginlog;
import com.xx.service.ILoginlogService;
import com.xx.vo.PageBean;

@Service
public class LoginlogService implements ILoginlogService {
	
	@Autowired
	private ILoginlogDao loginlogDao;

	// page是当前页,limit是每页查询多少条数据
	@Override
	public PageBean<Loginlog> findLogByPage(int page, int limit) {
		
		// 查询到的总数据
		PageBean<Loginlog> pageinfo = new PageBean<>();	
		// 获取表单中的总记录数
		int count = loginlogDao.count();
		// 根据页码计算分页查询时的开始索引 (例如 第一页就是 (1-1)*10 就是从0开始查第一页的数据 )
		int index = (page - 1) * limit;
		// 设置参数用来给数据库传入数据
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", limit);
		//调用dao层方法查询数据
		List<Loginlog> loginlogList = loginlogDao.findByIndexAndSize(map);
		// 给pagebean对象赋值
		pageinfo.setPageInfos(loginlogList);
		pageinfo.setCount(count);
		pageinfo.setPage(page);
		pageinfo.setLimit(limit);

		return pageinfo;
	}

	@Override
	public void addLoginlog(Loginlog loginlog) {
		try {
			loginlogDao.addLoginlog(loginlog);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}	

}

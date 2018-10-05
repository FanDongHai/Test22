package com.xx.dao;

import java.util.List;
import java.util.Map;

import com.xx.entity.Loginlog;

public interface ILoginlogDao {
	
	//查询个数
	public int count();
	
	// 分页查询
	public List<Loginlog> findByIndexAndSize(Map<String, Object> map);
	
	// 添加方法
	public void addLoginlog(Loginlog loginlog);
}

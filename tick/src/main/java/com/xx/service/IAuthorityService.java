package com.xx.service;

import java.util.List;
import java.util.Map;

import com.xx.entity.Authority;
import com.xx.vo.PageBean;

public interface IAuthorityService {
	
	// 查找权限
	public List<Authority> findAuthorityByUnoAndParentId(Map<String, Object> map);
	
	// 分页查询所有权限
	public PageBean<Authority> findAllAutoPages(Integer page, Integer limit);
	
	// 删除
	public void deleteAuthoById(Integer id);

	// 查找
	public Authority findAuthoById(Integer id);
	
	// 修改
	public void updateAuthoById(Authority authority);
	
	// 添加
	public void addAuth(Authority authority);
	
	// 查询一级
	public List<Authority> findAuthById2();
}

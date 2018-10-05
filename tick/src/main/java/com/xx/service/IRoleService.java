package com.xx.service;

import java.util.List;

import com.xx.entity.Role;
import com.xx.vo.PageBean;

public interface IRoleService {
	
	// 分页查询
	public PageBean<Role> findAllRolePage(Integer page, Integer limit, String rname);
	
	// 删除权限
	public void deleteRoleById(Integer roid);
	
	// 查询roid对应的权限
	public List<Role> findRoleAutoById();
	
	// 查询roid对应的权限
	public List<Role> findRoleAutoById1(Integer roid);
	
	// 添加roid对应的权限
	public void addAuthByRoleId(String rid, String aid);
}

package com.xx.dao;

import java.util.List;
import java.util.Map;

import com.xx.entity.Role;

public interface IRoleDao {
	
	// 根据用户表的no获取角色列表
	public List<String> findRoleByUno(String no); 
	
	// 分页查询
	public List<Role> findAllRolePage(Map<String, Object> map);
	
	// 查询数据总个数
	public Integer count();
	
	// 删除权限
	public void deleteRole(Integer roid);
	
	// 根据id查看权限
	public List<Role> findRoleAutoById();
	
	// 根据id查看权限
	public List<Role> findRoleAutoById1(Integer roid);
	
	// 修改权限
	//public void updateRoleAuthoById(Map<String, Object> map);
	
	// 添加权限
	public void addAuthByRoleId(List<String> item);
	
}

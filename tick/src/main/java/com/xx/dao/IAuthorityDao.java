package com.xx.dao;

import java.util.List;
import java.util.Map;

import com.xx.entity.Authority;

public interface IAuthorityDao {

	// 根据用户表的no获取权限列表
	public List<String> findAuthorityByUno(String no);
	
	// 根据用户表的no和权限表中的父类Id获取权限信息,传参为map集合,返回值是Authority对象列表
	public List<Authority> findAuthorityByUnoAndParentId(Map<String, Object> map);
	
	// 分页查询全部权限
	public List<Authority> findAllAutoPage(Map<String, Object> map);
	
	// 查询数据个数
	public Integer count(); 
	
	// 根据Id查询方法
	public Authority findById(Integer id);
	
	// 根据Id删除方法
	public void deleteById(Integer id);
	
	// 根据Id修改
	public void updateById(Authority authority);
	
	// 添加权限
	public void addAuth(Authority authority);
	
	// 查找全部2级信息
	public List<Authority> findById2();
	
}

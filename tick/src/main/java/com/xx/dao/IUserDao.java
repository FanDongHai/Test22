package com.xx.dao;

import java.util.List;
import java.util.Map;

import com.xx.entity.User;

public interface IUserDao {

	// 查询方法，用来验证用户登录
	public User findByNo(String no);
	
	//查询用户个数
	public int count();
	
	// 分页查询全部用户
	public List<User> findByIndexAndSize(Map<String, Object> map);
	
	// 根据用户id查找用户
	public User findById(Integer id);
	
	// 根据用户id修改用户
	public void updateById(User user);
	
	// 跟据用户id删除用户
	public void deleteById(Integer id);
}

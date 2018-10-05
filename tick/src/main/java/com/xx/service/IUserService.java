package com.xx.service;

import com.xx.entity.User;
import com.xx.vo.PageBean;

public interface IUserService {
	
	public User findUserByNo(String no);
	
	public PageBean<User> findUserByPage(Integer page, Integer limit, String no, Integer flag);
	
	public User findUserById(Integer id);
	
	public void updateUserById(User user);
	
	public void deleteUserById(Integer id);
}

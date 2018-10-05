package com.xx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.dao.IUserDao;
import com.xx.entity.User;
import com.xx.service.IUserService;
import com.xx.vo.PageBean;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	// 通过用户名查询用户信息
	@Override
	public User findUserByNo(String no) {
		
		User user = null;
		try {
			user = userDao.findByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return user;
	}

	@Override
	public PageBean<User> findUserByPage(Integer page, Integer limit, String no, Integer flag) {
		// 创建pagebean对象
		PageBean<User> pageBean = new PageBean<>();
		// 获取调用Dao方法的参数
		int count = userDao.count();
		// 根据页数算出从那开始查询
		int index = (page - 1) * limit;
		// 查询pageBean对象中的数据集合
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", limit);
		map.put("no", no);
		map.put("flag", flag);
		// 查询User数据集合
		List<User> findByIndexAndSize = userDao.findByIndexAndSize(map);
		// 把查询到的pagebean集合
		pageBean.setPageInfos(findByIndexAndSize);
		pageBean.setCount(count);
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		return pageBean;
	}

	@Override
	public void updateUserById(User user) {
		try {
			userDao.updateById(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteUserById(Integer id) {
		try {
			userDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public User findUserById(Integer id) {
		User user = null;
		try {
			user = userDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return user;
	}
}

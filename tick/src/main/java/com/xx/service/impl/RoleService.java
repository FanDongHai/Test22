package com.xx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.dao.IRoleDao;
import com.xx.entity.Role;
import com.xx.service.IRoleService;
import com.xx.vo.PageBean;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	
	@Override
	public PageBean<Role> findAllRolePage(Integer page, Integer limit, String rname) {
		
		// 创建map集合
		Map<String, Object> map = new HashMap<>();
		map.put("rname", rname);
		map.put("size", limit);
		Integer index = (page - 1) * limit;
		map.put("index", index);
		List<Role> list = null;
		
		try {
			 list = roleDao.findAllRolePage(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 创建bean对象
		PageBean<Role> roles = new PageBean<>();
		Integer count = roleDao.count();
		roles.setCount(count);
		roles.setLimit(limit);
		roles.setPage(page);
		roles.setPageInfos(list);
		
		return roles;
	}


	@Override
	public void deleteRoleById(Integer roid) {
		try {
			roleDao.deleteRole(roid);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	@Override
	public List<Role> findRoleAutoById() {
		
		List<Role> list = null;
		try {
			 list = roleDao.findRoleAutoById();
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}


	@Override
	public List<Role> findRoleAutoById1(Integer roid) {

		List<Role> list = null;
		try {
			 list = roleDao.findRoleAutoById1(roid);
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}


	@Override
	public void addAuthByRoleId(String rid, String aid) {
		List<String> item = new ArrayList<>();
		item.add(rid);
		item.add(aid);
		try {
			roleDao.addAuthByRoleId(item);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}

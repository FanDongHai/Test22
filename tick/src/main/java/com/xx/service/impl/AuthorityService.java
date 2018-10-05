package com.xx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.dao.IAuthorityDao;
import com.xx.entity.Authority;
import com.xx.service.IAuthorityService;
import com.xx.vo.PageBean;

@Service
public class AuthorityService implements IAuthorityService {

	@Autowired
	private IAuthorityDao authorityDao;
	
	@Override
	public List<Authority> findAuthorityByUnoAndParentId(Map<String, Object> map) {
		
		List<Authority> authority = null;
	
		try {
			authority = authorityDao.findAuthorityByUnoAndParentId(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return authority;
	}

	@Override
	public PageBean<Authority> findAllAutoPages(Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		Integer index = (page - 1) * limit;
		map.put("index", index);
		map.put("size", limit);
		List<Authority> list = authorityDao.findAllAutoPage(map);
		
		
		PageBean<Authority> auths = new PageBean<>();
		Integer count = authorityDao.count();
		auths.setCount(count);
		auths.setLimit(limit);
		auths.setPage(page);
		auths.setPageInfos(list);
		
		return auths;
	}

	@Override
	public void deleteAuthoById(Integer id) {
		try {
			authorityDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Authority findAuthoById(Integer id) {
		
		Authority authority = null;
		try {
			authority = authorityDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return authority;
	}

	@Override
	public void updateAuthoById(Authority authority) {
			try {
				authorityDao.updateById(authority);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
	}

	@Override
	public void addAuth(Authority authority) {
		
		try {
			authorityDao.addAuth(authority);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Authority> findAuthById2() {
		List<Authority> autho2 = null;
		try {
			autho2 = authorityDao.findById2();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return autho2;
	}

}

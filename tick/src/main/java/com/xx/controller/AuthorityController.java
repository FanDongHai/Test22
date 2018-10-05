package com.xx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xx.entity.Authority;
import com.xx.service.IAuthorityService;
import com.xx.utils.JsonBeanUtil;
import com.xx.vo.JsonBean;
import com.xx.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class AuthorityController {

	@Autowired
	private IAuthorityService authorityService;

	@RequestMapping("/Sidebar")
	@ResponseBody
	public JsonBean sidebar(HttpServletRequest request) {
		// 获取存在session中的用户no值
		String no = (String) request.getSession(false).getAttribute("userno");
		// 查询出全部的一级权限
		Map<String, Object> oneMap = new HashMap<>();
		oneMap.put("no", no);
		oneMap.put("parentId", 0);
		// 调用方法查询
		List<Authority> parentAuth = authorityService.findAuthorityByUnoAndParentId(oneMap);
	
		List<Map<String, Object>> allAuth = new ArrayList<>();
		for (Authority pAuth : parentAuth) {
			// 把遍历出来的一级权限放到集合中
			Map<String, Object> map1 = new HashMap<>();
			map1.put("parentAuth", pAuth);

			// 通过一级权限的ID查询二级权限
			Map<String, Object> map2 = new HashMap<>();
			map2.put("no", no);
			Integer parentId = pAuth.getId();
			map2.put("parentId", parentId);
			// 执行查询方法
			List<Authority> cherAuth = authorityService.findAuthorityByUnoAndParentId(map2);

			// 把对应的二级权限放到相应的一级权限下
			map1.put("child", cherAuth);
			// 把每组权限放到创建的List集合里
			allAuth.add(map1);
		}
		
		return JsonBeanUtil.shiFtJsonBean(1, allAuth);
	}
	
	@RequestMapping("/findAuthPage")
	@ResponseBody
	public Map<String, Object> findAllAuthPage(Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
			PageBean<Authority> authPageBean = authorityService.findAllAutoPages(page, limit);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", authPageBean.getCount());
			map.put("data", authPageBean.getPageInfos());
	
		return map;
	}
	
	@RequestMapping("/deleteAuth")
	@ResponseBody
	public JsonBean deleteAuthById(Integer id) {
		try {
			authorityService.deleteAuthoById(id);
			return JsonBeanUtil.shiFtJsonBean(1, null);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/updateAuth")
	@ResponseBody
	public JsonBean updateAuth(Authority authority) {
		try {
			authorityService.updateAuthoById(authority);
			return JsonBeanUtil.shiFtJsonBean(1, null);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
	
	
	@RequestMapping("/findAuthById")
	@ResponseBody
	public JsonBean findAuthById(Integer id) {
		try {
			Authority authority = authorityService.findAuthoById(id);
			return JsonBeanUtil.shiFtJsonBean(1, authority);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/findAuthById2")
	@ResponseBody
	public JsonBean findAuthById2() {
		try {
			List<Authority> auth2 = authorityService.findAuthById2();
			return JsonBeanUtil.shiFtJsonBean(1, auth2);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/addAuth")
	@ResponseBody
	public JsonBean addAuth(Authority authority) {
		
		try {
			authorityService.addAuth(authority);
			return JsonBeanUtil.shiFtJsonBean(1, null);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
	
	
}

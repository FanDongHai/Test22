package com.xx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xx.entity.Role;
import com.xx.service.IRoleService;
import com.xx.utils.JsonBeanUtil;
import com.xx.vo.JsonBean;
import com.xx.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	// 分页查询
	@RequestMapping("/findAllRole")
	@ResponseBody
	public Map<String, Object> findAllRolePage(Integer page, Integer limit, String rname) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (rname != null && rname.equals("")) {
			rname = null;
		}

		try {
			PageBean<Role> rolePageBean = roleService.findAllRolePage(page, limit, rname);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", rolePageBean.getCount());
			map.put("data", rolePageBean.getPageInfos());
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return map;
		}
	}

	@RequestMapping("/deleteRole")
	@ResponseBody
	public JsonBean deleteRoleById(Integer roid) {
		try {
			roleService.deleteRoleById(roid);
			return JsonBeanUtil.shiFtJsonBean(1, null);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}

	@RequestMapping("/findRoleAuto")
	@ResponseBody
	public JsonBean findRoleAutoById() {
		List<Role> role = null;
		try {
			role = roleService.findRoleAutoById();
			return JsonBeanUtil.shiFtJsonBean(1, role);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/findRoleAuto1")
	@ResponseBody
	public JsonBean findRoleAutoById(Integer roid) {
		List<Role> role = null;
		try {
			role = roleService.findRoleAutoById1(roid);
			return JsonBeanUtil.shiFtJsonBean(1, role);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/addAuthoByRole")
	@ResponseBody
	public JsonBean addAuthByRoleId(String rid, String aid) {
		try {
			System.out.println(rid);
			System.out.println(aid);
			roleService.addAuthByRoleId(rid, aid);
			return JsonBeanUtil.shiFtJsonBean(1, null);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}

}
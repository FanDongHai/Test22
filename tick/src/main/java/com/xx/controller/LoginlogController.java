package com.xx.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xx.entity.Loginlog;
import com.xx.service.ILoginlogService;
import com.xx.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class LoginlogController {

	@Autowired
	private ILoginlogService loginlogService;

	@RequestMapping("/loginlogPagesss")
	@ResponseBody
	public Map<String, Object> paging(int page, int limit) {

		Map<String, Object> map = new HashMap<String, Object>();
		PageBean<Loginlog> loginlogpageBean;
		try {
			loginlogpageBean = loginlogService.findLogByPage(page, limit);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", loginlogpageBean.getCount());
			map.put("data", loginlogpageBean.getPageInfos());
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return map;
		}
	}

}

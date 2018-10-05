package com.xx.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xx.entity.Loginlog;
import com.xx.entity.User;
import com.xx.service.ILoginlogService;
import com.xx.service.IUserService;
import com.xx.utils.AddressUtils;
import com.xx.utils.JsonBeanUtil;
import com.xx.vo.JsonBean;
import com.xx.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILoginlogService loginlogService;

	// 用户登录(使用MD5密文方式)
	@RequestMapping("/Userlogins")
	@ResponseBody
	public JsonBean login(String no, String password, HttpServletRequest request) {

		// 设置token标记，存储用户账号和密码
		UsernamePasswordToken token = new UsernamePasswordToken(no, password);
		// 获取subject主体，用于登录操作
		Subject subject = SecurityUtils.getSubject();
		try {
			// 进行登录操作，会将token标记传到MyRelam中，进行比较
			subject.login(token);
			// 获取session域对象,存储信息
			request.getSession().setAttribute("userno", no);
			
			// 创建时间对象
			Date createtime = new Date();
			request.getSession().setAttribute("createtime", createtime);
			// 调用工具添加Loginlog数据
			String ip = AddressUtils.getIpAddr(request);
			String location = null;
			try {
				location = AddressUtils.getAddresses("ip=" + ip, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 调用方法添加
			Loginlog loginlog = new Loginlog();
			loginlog.setCreatetime(createtime);
			loginlog.setIp(ip);
			loginlog.setLocation(location);
			loginlog.setNo(no);
			loginlogService.addLoginlog(loginlog);
			
			return JsonBeanUtil.shiFtJsonBean(1, null);
		} catch (AuthenticationException e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}

	// 根据用户no查询用户
	@RequestMapping("/findUsers")
	@ResponseBody
	public JsonBean findUserByNo(HttpServletRequest request) {
		// 获取域中的数据
		String no = (String) request.getSession().getAttribute("userno");
		User user = null;
		try {
			user = userService.findUserByNo(no);
			return JsonBeanUtil.shiFtJsonBean(1, user);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}

	// 注销登录
	@RequestMapping("/Userlogouts")
	@ResponseBody
	public JsonBean logout(HttpServletRequest request) {
		// 是用户失效
		request.getSession(false).invalidate();

		return JsonBeanUtil.shiFtJsonBean(1, null);
	}

	@RequestMapping("/UserfindAllPage")
	@ResponseBody
	public Map<String, Object> findUserPage(Integer page, Integer limit, String no, Integer flag) {
		Map<String, Object> map = new HashMap<>();
		// 获取用到的参数,判断是否为空，为空的话，直接赋值
		if (no != null && no.equals("")) {
			no = null;
		}

		try {
			PageBean<User> userPageBean = userService.findUserByPage(page, limit, no, flag);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", userPageBean.getCount());
			map.put("data", userPageBean.getPageInfos());
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return map;
		}
	}

	@RequestMapping("/updateUser")
	@ResponseBody
	public JsonBean updateUserById(User user) {
		try {
			userService.updateUserById(user);
			return JsonBeanUtil.shiFtJsonBean(1, null);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
	
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public JsonBean deleteUserById(Integer id) {
		try {
			userService.deleteUserById(id);
			return JsonBeanUtil.shiFtJsonBean(1, null);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/findUser")
	@ResponseBody
	public JsonBean findUserById(Integer id) {
		
		User user = null;
		try {
			user = userService.findUserById(id);
			return JsonBeanUtil.shiFtJsonBean(1, user);
		} catch (Exception e) {
			return JsonBeanUtil.shiFtJsonBean(0, e.getMessage());
		}
	}
}

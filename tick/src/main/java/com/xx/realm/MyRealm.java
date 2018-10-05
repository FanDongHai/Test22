package com.xx.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xx.dao.IAuthorityDao;
import com.xx.dao.IRoleDao;
import com.xx.dao.IUserDao;
import com.xx.entity.User;

public class MyRealm extends AuthorizingRealm{
	
	// 为了获取用户信息
	@Autowired
	private IUserDao userDao;
	
	// 为了获取权限
	@Autowired
	private IAuthorityDao authorityDao;
	
	// 获取角色信息
	private IRoleDao roleDao;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 利用主体获取输入的no
		String no = (String) principals.getPrimaryPrincipal();
	
		// 从数据库获取用户的角色列表
		List<String> roleList = roleDao.findRoleByUno(no);
	
		// 从数据库获取用户权限列表
		List<String> authorityList = authorityDao.findAuthorityByUno(no);
		
		// 因为设置时需要set集合,需要转换
		Set<String> roleSet = new HashSet<>(roleList);
		Set<String> authoritySet = new HashSet<>(authorityList);
		
		// 获取授权信息的对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 添加对应的角色
		info.setRoles(roleSet);
		// 添加对应的权限
		info.setStringPermissions(authoritySet);
		
		return info;
	}
	
	// 认证,用于判断用户登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		// 通过token标记获取用户no
		String no = (String) token.getPrincipal();
		// 判断用户是否存在,并且获取用户no跟密码
		User user = userDao.findByNo(no);
		if (user == null) {
			throw new UnknownAccountException();
		}
		String password = user.getPassword();
		
		// 创建安全认证信息，并且进行判断(MD5加密)
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(no, password, this.getName());
		
		return info;
	}

}

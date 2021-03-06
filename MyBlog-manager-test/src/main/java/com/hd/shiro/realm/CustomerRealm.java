package com.hd.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.myblog.service.AdminService;

import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomerRealm extends AuthorizingRealm {
	@Autowired
	AdminService adminService;
	

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getrolesdByUsername(username);
        Set<String>permission = getPermissionByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permission);

        return simpleAuthorizationInfo;
    }

    private Set getPermissionByUsername(String username) {
        Set<String> sets = new HashSet();
        sets.add("user:delete");
        sets.add("admin1:add");

        return    sets;
    }

    private Set getrolesdByUsername(String username) {
    	
        Set sets = new HashSet();
        sets.add("admin1");
        sets.add("user");
        return sets;
    }

    //认证s,主体认证信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       //1.从主体传过来的认证信息获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //2.通过用户名获取数据库凭证
        String password = getpasswordByUsername(username);
        if(password ==null){
            return null;//密码没有是没有账号
        }
       SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,"customerRealm"); //正确的和错误的对比
       // authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("hujf"));
        return authenticationInfo;
    }

    private String getpasswordByUsername(String username) {
    	System.out.println("从数据库中获取相应密码");
    	adminService.getById(Integer.parseInt(username));
        return adminService.getpasswordByUsername(username);
    }


}

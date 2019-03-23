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
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomerRealm extends AuthorizingRealm {
    Map<String,String> map = new HashMap();
    {
        map.put("001","1234");
    }
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
        sets.add("admin:add");

        return    sets;
    }

    private Set getrolesdByUsername(String username) {
        Set sets = new HashSet();
        sets.add("admin");
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
            return null;
        }
       SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("001",password,"customerRealm");
//        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("hujf"));
        return authenticationInfo;
    }

    private String getpasswordByUsername(String username) {
        return map.get(username);
    }

    public static void main(String []args){
        Md5Hash mad = new Md5Hash("1234","hujf");
        System.out.println(mad.toString());
    }

}

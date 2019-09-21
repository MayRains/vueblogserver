package com.tzbfine.vueblogserver.Utils.shiro;


import com.tzbfine.vueblogserver.Service.RolesService;
import com.tzbfine.vueblogserver.Service.UserService;
import com.tzbfine.vueblogserver.dao.RolesDao;
import com.tzbfine.vueblogserver.model.Roles;
import com.tzbfine.vueblogserver.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    UserService userService;
    @Resource
    RolesService rolesService;
    //授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        User user = (User) principalCollection.getPrimaryPrincipal();
        // 获取该用户的角色
        List<Roles> list = rolesService.getRolesList(user.getId());
        for (Roles role:list
             ) {
            info.addRole(role.getName());
        }
        return info;
    }

    //认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  throws AuthenticationException  {
        System.out.println("step 3");

//        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
//        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro 自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = userService.getUserByName(username);
//        System.out.println("----->>userInfo="+userInfo);
        if (userInfo == null) {
            return null;
        }
        if (userInfo.getEnabled() == 0) { //账户冻结
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),//用户名
                 //salt=username+salt
                getName()  //realm name
        );
//        System.out.println(authenticationInfo.toString());
        // 保存登录信息
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession",userInfo);
        session.setAttribute("userSessionId",userInfo.getId());
//        session.setAttribute("userSessionName",userInfo.getUsername());
        return authenticationInfo;
    }

}


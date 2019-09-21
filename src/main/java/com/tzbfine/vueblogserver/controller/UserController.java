package com.tzbfine.vueblogserver.controller;

import com.google.gson.Gson;
import com.tzbfine.vueblogserver.Service.RolesService;
import com.tzbfine.vueblogserver.Service.UserService;
import com.tzbfine.vueblogserver.Utils.AjaxResponse;
import com.tzbfine.vueblogserver.Utils.CurUserUtils;
import com.tzbfine.vueblogserver.model.Roles;
import com.tzbfine.vueblogserver.model.User;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.ajp.AjpAprProtocol;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Api(value = "user")
@Slf4j
@RestController
public class UserController {
    @Resource
    UserService userService;
    @Resource
    RolesService rolesService;

//    private User CUR_USER = CurUserUtils.getCurUser();

    @GetMapping(value = "/getUser/{id}", produces = "application/json")
    public @ResponseBody
    AjaxResponse getCurrentUser(@PathVariable int id) {

        User tempUser = userService.getUserById(id);
        if (tempUser == null) {
            return AjaxResponse.fail();
        } else {
            log.info("查询到用户", tempUser);
            return AjaxResponse.success(tempUser, "查询到当前用户成功");
        }
    }

    @GetMapping(value = "/getAllUsers", produces = "application/json")
    public @ResponseBody
    AjaxResponse queryAllUsers() {
        List<User> curList = userService.getAllUser();
//        Gson gson = new Gson();
//        String resStr = gson.toJson(curList);
        log.info("查询到所有用户", curList);
        return AjaxResponse.success(curList, "查询到所有用户成功");
    }

    @PostMapping(value = "/ajaxLogin", produces = "application/json")
    @ResponseBody
    public AjaxResponse userLogin(User user) {
        Subject subject = SecurityUtils.getSubject();

        String pwd = user.getPassword();
        String md5pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), md5pwd);

        try {
            subject.login(token);
            System.out.println("验证通过");
            return AjaxResponse.success(user, "登陆成功");
        } catch (Exception e) {
            return AjaxResponse.fail(e, "用户名密码错误");
        }
    }


    @GetMapping(value = "/login_error", produces = "application/json")
    public AjaxResponse loginError() {
        return AjaxResponse.fail();
    }

    @GetMapping(value = "/getUserRole/{id}", produces = "application/json")
    public AjaxResponse getUserRole(@PathVariable int id) {
        List<Roles> res = userService.getUserRole(id);
        if (res != null) {
            return AjaxResponse.success(res, "查询成功");
        } else {
            return AjaxResponse.fail();
        }
    }

    @GetMapping(value = "/getRoleList", produces = "application/json")
    public AjaxResponse getRoleList() {
        Session session = SecurityUtils.getSubject().getSession();
        int uid = (int) session.getAttribute("userSessionId");
        List<Roles> res = rolesService.getRolesList(uid);
        if (res != null) {
            return AjaxResponse.success(res, "查询成功");
        } else {
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/logout", produces = "application/json")
    public AjaxResponse logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return AjaxResponse.success();
    }

    @RequestMapping(value = "/unauth")
    @ResponseBody
    public AjaxResponse unauth() {
        String str = "未登录";
        return AjaxResponse.fail(null, str);
    }

    @GetMapping(value = "/isAdmin", produces = "application/json")
    @ResponseBody
    public AjaxResponse isAdmin() {
        Session session = SecurityUtils.getSubject().getSession();
        int id = (int) session.getAttribute("userSessionId");
        List<Roles> list = rolesService.getRolesList(id);
        HashMap<String, String> map = new HashMap<>();
        if (list != null) {
            for (Roles item : list) {
                map.put(item.getName(), "");
            }
            if (map.containsKey("admin")) {
                return AjaxResponse.success(list, "该用户为管理员");
            } else {
                return AjaxResponse.fail(list, "该用户不是管理员");
            }
        } else {
            return AjaxResponse.fail(null, "未查询到用户权限");
        }
    }

    @GetMapping(value = "/currentUserNickName", produces = "application/json")
    public @ResponseBody
    AjaxResponse getCurrentUserName() {
        int id = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        User user = userService.getUserById(id);
        String name = user.getNickname();
        return AjaxResponse.success(null, name);
    }

    @PutMapping(value = "/updateUserEmail", produces = "application/json")
    public @ResponseBody
    AjaxResponse updateUserEmail(String email) {
        int id = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        if (userService.updateUserEmail(email, id) == 1) {
            return AjaxResponse.success(null, "更新邮箱成功");
        } else {
            return AjaxResponse.fail(null, "更新失败");
        }
    }

    @PostMapping(value = "/insertUser", produces = "application/json")
    public @ResponseBody
    AjaxResponse insertUser(@RequestBody User user) {
        if (userService.getUserByName(user.getUsername()) != null) {
            return AjaxResponse.fail(user, "用户名重复");
        } else {
            userService.insertUser(user);
            int rid = 2;
            rolesService.addRole(rid, user.getId());
            List<Roles> curRole = rolesService.getRolesList(user.getId());
            user.setRoles(curRole);
            return AjaxResponse.success(user, "注册成功");
        }
    }

    @DeleteMapping(value = "/deleteUserRoleById", produces = "application/json")
    public @ResponseBody
    AjaxResponse deleteUserRoleById() {
        int uid = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        if (userService.deleteUserRoleById(uid) == 1) {
            return AjaxResponse.success(null, "删除用户角色成功");
        } else {
            return AjaxResponse.fail();
        }
    }

    //    pass 有外键错误
    @PostMapping(value = "/addRoles/{rid}")
    public @ResponseBody
    AjaxResponse addRole(@PathVariable int rid) {
        int uid = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        if (rolesService.addRole(uid, rid) == 1) {
            return AjaxResponse.success(null, "添加成功");
        } else {
            return AjaxResponse.fail(null, "添加失败");
        }
    }


}

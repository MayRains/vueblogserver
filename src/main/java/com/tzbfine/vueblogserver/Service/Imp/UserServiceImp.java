package com.tzbfine.vueblogserver.Service.Imp;

import com.tzbfine.vueblogserver.Service.UserService;
import com.tzbfine.vueblogserver.Utils.CurUserUtils;
import com.tzbfine.vueblogserver.dao.RolesDao;
import com.tzbfine.vueblogserver.dao.UserDao;
import com.tzbfine.vueblogserver.model.Roles;
import com.tzbfine.vueblogserver.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Resource
    UserDao userDao;
    @Resource
    RolesDao rolesDao;

    @Override
    public User getUserById(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.selectByName(name);
    }

    @Override
    public List<Roles> getUserRole(int id) {
        return userDao.getUserRole(id);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUserS();
    }

    @Override
    public int updateUserEmail(String email, int id) {
        return userDao.updateUserEmail(email,id);
    }

    @Override
    public List<String> getUserName() {
        return userDao.getNickNameList();
    }

    @Override
    public int insertUser(User user) {
            user.setId(user.getId());
            user.setUsername(user.getUsername());
            user.setNickname(user.getNickname());
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            user.setEnabled(1);
            user.setEmail(user.getEmail());
            user.setUserface(user.getUserface());
            user.setRegtime(new Date());
            return userDao.insertUser(user);
       }

    @Override
    public int deleteUserRoleById(int id) {
        return rolesDao.deleteRole(id);
    }

}

package com.tzbfine.vueblogserver.Service;

import com.tzbfine.vueblogserver.model.Roles;
import com.tzbfine.vueblogserver.model.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    User getUserByName(String name);
    List<Roles> getUserRole(int id);
    List<User> getAllUser();
    int updateUserEmail(String email,int id);
    List<String> getUserName();
    int insertUser(User user);
    int deleteUserRoleById(int id);
}

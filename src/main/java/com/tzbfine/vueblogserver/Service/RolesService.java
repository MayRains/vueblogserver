package com.tzbfine.vueblogserver.Service;


import com.tzbfine.vueblogserver.model.Roles;

import java.util.List;

public interface RolesService {
    List<Roles> getRolesList(int uid);
    int addRole(int rid,int uid);
}

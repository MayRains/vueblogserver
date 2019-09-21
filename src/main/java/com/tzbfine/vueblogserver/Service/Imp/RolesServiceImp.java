package com.tzbfine.vueblogserver.Service.Imp;

import com.tzbfine.vueblogserver.Service.RolesService;
import com.tzbfine.vueblogserver.dao.RolesDao;
import com.tzbfine.vueblogserver.model.Roles;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolesServiceImp implements RolesService {
    @Resource
    RolesDao rolesDao;

    @Override
    public List<Roles> getRolesList(int uid) {
        return rolesDao.getRoleList(uid);
    }

    @Override
    public int addRole(int rid, int uid) {
        return rolesDao.addRole(rid,uid);
    }
}

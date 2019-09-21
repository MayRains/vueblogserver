package com.tzbfine.vueblogserver.dao;

import com.tzbfine.vueblogserver.model.Roles;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RolesDao {

//    @Select("select name from roles where id = #{id}")
//     SELECT r.* FROM roles r,roles_user ru WHERE r.`id`=ru.`rid` AND ru.`uid`=#{uid}
    @Select("select r.* from roles r,roles_user ru where r.id = ru.rid and ru.uid = #{uid} ")
    List<Roles> getRoleList(int uid);

    @Insert("insert roles_user values(null,#{rid},#{uid})")
    int addRole(int rid,int uid);

    @Delete("delete from roles_user where uid = #{uid}")
    int deleteRole(int uid);

}

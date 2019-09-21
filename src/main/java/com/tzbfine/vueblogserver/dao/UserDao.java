package com.tzbfine.vueblogserver.dao;

import com.tzbfine.vueblogserver.model.Roles;
import com.tzbfine.vueblogserver.model.User;

import java.util.List;

import org.apache.ibatis.annotations.*;

import javax.management.relation.Role;

@Mapper
public interface UserDao {


    @Select("select * from user where id =#{id}")
    User selectByPrimaryKey(Integer id);

    @Select("select * from user where username =#{username}")
    User selectByName(String username);

    @Select("select * from user")
    List<User> getAllUserS();

//     UPDATE user set email=#{email} WHERE id=#{id}
    @Update("update user set email= #{email} where id= #{id}")
    int updateUserEmail(String email,int id);

//    @Select("select * from orders o,member m,product p  where o.memberid=m.id and o.productid=p.id and o.id=#{id}")
//    select * from 权限表 where 权限id in
//(select 权限id  from 角色表 where 角色ID in (select 角色ID from 用户表 where 用户ID=999))

    String sql = "select * from roles  where id in (select rid from roles_user where uid in (select id from user where id = #{id}))";
    @Select(sql)
    List<Roles> getUserRole(int id);

//  @Insert({ "insert into sys_role(id, role_name, enabled, create_by, create_time) values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})" })
    @Insert("insert into user(username,nickname,password,enabled,email,regTime) values(#{username},#{nickname},#{password},#{enabled},#{email},#{regtime,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    int insertUser(User user);

    @Select("select username from user")
    List<String> getNickNameList();


}
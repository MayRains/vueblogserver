package com.tzbfine.vueblogserver.controller;

import com.alibaba.fastjson.JSON;
import com.tzbfine.vueblogserver.model.User;

import java.util.Date;

public class Json {
    public static void main(String[] args) {
        /*
        * username,nickname,password,enabled,email,regTime*/
        User user = new User();
        user.setUsername("Richard");
        user.setNickname("理查德");
        user.setPassword("123");
        user.setEnabled(1);
        user.setEmail("DuckTest@qq.com");
        user.setRegtime(new Date());
        String s = JSON.toJSONString(user);
        System.out.println(s);
    }
}

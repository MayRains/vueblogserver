package com.tzbfine.vueblogserver.Utils;

import com.tzbfine.vueblogserver.model.User;
import org.apache.shiro.SecurityUtils;

public class CurUserUtils {
    public static Object getCurUser(){
        return  SecurityUtils.getSubject().getPrincipal();
    }
}

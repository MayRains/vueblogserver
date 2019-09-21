package com.tzbfine.vueblogserver.Utils;

import io.swagger.annotations.ApiModelProperty;

public class AjaxResponse {

    @ApiModelProperty(value = "是否请求成功")
    private boolean isok;

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty("响应信息")
    private String message;

    @ApiModelProperty("响应数据")
    private Object data;

//    private Class AjaxResponse(){}
// getter and setter

    public boolean isIsok() {
        return isok;
    }

    public void setIsok(boolean isok) {
        this.isok = isok;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //无参构造函数
    private AjaxResponse(){}

    // 无参数
    public static AjaxResponse success(){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    // 有参数
    public static AjaxResponse success(Object data,String message){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }

    public static AjaxResponse fail(){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(404);
        resultBean.setMessage("404 NOT FOUND");
        return resultBean;
    }

    public static AjaxResponse fail(Object data,String message){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(404);
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }
}

package com.util;

public class JsonUtil {
    /**
     * 返回给前端的错误码：0  表示正常   非零值表示异常
     */
    private Integer code=0;
    /**
     * 错误消息，如果错误码为0，错误消息填空值  错误码为非零时，用文本描述错误消息(如：用户名或密码错误等)
     */
    private String msg;



    /**
     * 数据data是包含数据表格的数据，用count表示数据条数
     */
    private int count;

    /**
     * 带回给前端的数据:如数据库表中的数据或一个JavaBean等
     */
    private Object data;//List<Person>   Person

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

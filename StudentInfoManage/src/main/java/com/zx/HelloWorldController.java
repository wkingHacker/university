package com.zx;


import com.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/HelloWorld")
public class HelloWorldController {

    //url:  http://127.0.0.1:8080/HelloWorld/hello
    @RequestMapping("/hello")
    public void hello(){
        System.out.println("hello");
    }

    @RequestMapping("/getJson")
    @ResponseBody  //将方法的返回值转换为json格式数据发给请向方
    public JsonUtil getJson(){
        JsonUtil ju = new JsonUtil();
        ju.setCode(1);
        ju.setMsg("用户名或密码错误");
        return ju;
    }
}

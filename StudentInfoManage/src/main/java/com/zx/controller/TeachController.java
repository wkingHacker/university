package com.zx.controller;

import com.util.JsonUtil;
import com.util.JwtToken;
import com.zx.bean.TeachBeanKey;
import com.zx.bean.TeacherInfoBean;
import com.zx.dao.TeachBeanMapper;
import com.zx.service.imp.TeachServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

;

/**
 * @ClassName TeachController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/8/17 21:42
 * @Version 1.0
 **/
@RequestMapping("/teach")
@Controller
public class TeachController {
    @Autowired
    TeachServiceImpl teachService;

    @RequestMapping("/delete")
    @ResponseBody
    public JsonUtil delete(String cid){


        JsonUtil jsonUtil = new JsonUtil();
        try {

            teachService.deleteTeachByCid(cid);


            jsonUtil.setCode(0);
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;
    }

    @RequestMapping("/add")
    @ResponseBody
    public JsonUtil add(TeachBeanKey teachBeanKey){

        JsonUtil jsonUtil = new JsonUtil();
        try {
            //检查登录名否已存在，存在不允许写入

            //MVC  View  Controller Model(Service,Dao[mapper])

            teachService.insert(teachBeanKey);
            jsonUtil.setCode(0);
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;
    }


    @RequestMapping("/edit")
    @ResponseBody
    public JsonUtil edit(TeachBeanKey teachBeanKey, HttpServletRequest request) throws AuthenticationException {

        JsonUtil jsonUtil = new JsonUtil();


        try{

            teachService.update(teachBeanKey);


        }catch (Exception e){
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }
        jsonUtil.setCode(0);
        jsonUtil.setMsg("修改用户成功");
        return jsonUtil;
    }


    //目前缺少service层，之后再补充
    //先完成 查询操作,调用 Mapper接口的查询方法
    @Autowired
    TeachBeanMapper teachBeanMapper;

    //获得所有的操作员的信息
    @RequestMapping("/list")
    @ResponseBody
    public JsonUtil list(TeachBeanKey teachBeanKey){


        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.setCode(0);
        jsonUtil.setMsg("查询成功");


        List<TeachBeanKey> beanList =  teachService.selectByExample(teachBeanKey);// operatorBeanMapper.selectByExample(null);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jsonUtil.setCount(beanList.size());
        jsonUtil.setData(beanList);
        return jsonUtil;

    }
}

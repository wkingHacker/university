package com.zx.controller;


import com.util.JsonUtil;
import com.util.JwtToken;
import com.zx.bean.OperatorBean;
import com.zx.bean.TeacherInfoBean;
import com.zx.dao.TeacherInfoBeanMapper;
import com.zx.service.imp.TeacherInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TeacherInfoController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/8/17 18:06
 * @Version 1.0
 **/

@RequestMapping("/teacherInfo")
@Controller
public class TeacherInfoController {
    @Autowired
    TeacherInfoServiceImpl teacherInfoService;

    @RequestMapping("/delete")
    @ResponseBody
    public JsonUtil delete(String tid){


        JsonUtil jsonUtil = new JsonUtil();
        try {

            teacherInfoService.deleteTeachByTid(tid);


            jsonUtil.setCode(0);
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;
    }

    @RequestMapping("/deleteUserAll")
    @ResponseBody
    public JsonUtil deleteUserAllById(String tid){
        JsonUtil jsonUtil = new JsonUtil();
        try {
            List idList = new ArrayList();
            String[] strs = tid.split(",");
            for (String str:strs){
                idList.add(str);
            }
            teacherInfoService.deleteTeachByTid(tid);


            jsonUtil.setCode(0);
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;
    }
    @RequestMapping("/add")
    @ResponseBody
    public JsonUtil add(TeacherInfoBean teacherInfoBean, HttpServletRequest request) throws AuthenticationException {


        JsonUtil jsonUtil = new JsonUtil();
        JwtToken jwt=new JwtToken();
        String loginId=jwt.getLoginUserId(request);
        System.out.println(loginId);
        try {
            //检查登录名否已存在，存在不允许写入

            //MVC  View  Controller Model(Service,Dao[mapper])

            teacherInfoBean.setLoginid(loginId);
            teacherInfoService.insert(teacherInfoBean);

            jsonUtil.setCode(0);
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;
    }


    @RequestMapping("/edit")
    @ResponseBody
    public JsonUtil edit(TeacherInfoBean teacherInfoBean, HttpServletRequest request) throws AuthenticationException {
        JwtToken jwtToken = new JwtToken();
        String loginid = jwtToken.getLoginUserId(request);
        JsonUtil jsonUtil = new JsonUtil();

        System.out.println("loginid: "+loginid);
        try{
            teacherInfoBean.setLoginid(loginid);
            teacherInfoService.update(teacherInfoBean);


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
    TeacherInfoBeanMapper teacherInfoBeanMapper;

    //获得所有的操作员的信息
    @RequestMapping("/list")
    @ResponseBody
    public JsonUtil list(TeacherInfoBean teacherInfoBean){


        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.setCode(0);
        jsonUtil.setMsg("查询成功");


        List<TeacherInfoBean> beanList =  teacherInfoService.selectByExample(teacherInfoBean);// operatorBeanMapper.selectByExample(null);
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

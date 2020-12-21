package com.zx.controller;

import com.util.JsonUtil;
import com.zx.bean.StudentInfoBean;
import com.zx.service.imp.StudentInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author WCY
 * @create 2020-08-18 14:27
 */

@RequestMapping("/studentinfo")
@Controller
public class StudentInfoController {

    //增加学生
    @RequestMapping("/addStudent")
    @ResponseBody
    public JsonUtil addStudent(StudentInfoBean studentInfoBean){
        JsonUtil jsonUtil = new JsonUtil();
        try {
            //检查登录名否已存在，存在不允许写入
                /* 测试用例
                studentInfoBean.setSid("2002");
                studentInfoBean.setSname("taylor");
                studentInfoBean.setSex("女");
                studentInfoBean.setLoginid("2");
                */

            //MVC  View  Controller Model(Service,Dao[mapper])
            studentInfoService.insert(studentInfoBean);
            jsonUtil.setCode(0);
        }catch (Exception e){
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }
        return  jsonUtil;
    }

    //根据学号删除学生
    @Autowired
    StudentInfoServiceImpl studentInfoService;
    @RequestMapping("/deleteStudent")
    @ResponseBody
    public JsonUtil deleteStudent(String tid){
        JsonUtil jsonUtil = new JsonUtil();
        try {
            /* 测试用例
            tid = "2002";
            */

            //根据学生的tid删除学生信息，级联删除
            studentInfoService.deleteByPrimaryKey(tid);
            jsonUtil.setCode(0);
        } catch (Exception e){
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }
        return jsonUtil;
    }

    //根据学号查询学生信息
    @RequestMapping("/queryStudentList")
    @ResponseBody
    public JsonUtil queryStudentList(StudentInfoBean studentInfoBean){
        JsonUtil jsonUtil = new JsonUtil();
        try {
            /* 测试用例
            studentInfoBean.setSid("2001");
            studentInfoBean.setSname("taylor");
            studentInfoBean.setSex("女");
            studentInfoBean.setLoginid("2");
            */

            List<StudentInfoBean> allList = studentInfoService.selectByExample(null);
            jsonUtil.setCode(0);
            jsonUtil.setMsg("查询成功");
            jsonUtil.setData(allList);
        } catch (Exception e){
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }
        return jsonUtil;
    }

    //根据学号修改学生信息 ！报错
    @RequestMapping("/editStudentMessage")
    @ResponseBody
    public JsonUtil editStudentMessage(StudentInfoBean studentInfoBean){
        JsonUtil jsonUtil = new JsonUtil();
        try {
            /* 测试用例
            studentInfoBean.setSid("2001");
            studentInfoBean.setSname("taylor swift");
            studentInfoBean.setSex("男");
            studentInfoBean.setLoginid("123456");
            */

            studentInfoService.update(studentInfoBean);
            jsonUtil.setCode(0);
        } catch (Exception e) {
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }
        return jsonUtil;
    }

    //学生信息浏览
    @RequestMapping("/list")
    @ResponseBody
    public JsonUtil list(StudentInfoBean studentInfoBean){
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.setCode(0);
        jsonUtil.setMsg("查询成功");

        List<StudentInfoBean> beanList=  studentInfoService.selectByExample(studentInfoBean);
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



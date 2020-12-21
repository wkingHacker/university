package com.zx.controller;

import com.util.JsonUtil;
import com.zx.bean.TeachBeanKey;
import com.zx.bean.TeacherInfoBean;
import com.zx.service.imp.AdminClassImpl;
import com.zx.service.imp.AdminTeachImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author gwl
 * @create 2020-08-20 上午 11:44
 */
@RequestMapping("/adminClass")
@Controller
public class AdminClassController {

    @Autowired
    AdminClassImpl adminClassService;
    //TeachBeanKey
    @RequestMapping("/addClass")
    @ResponseBody
    public JsonUtil addClass(TeachBeanKey teachBeanKey){

        JsonUtil jsonUtil = new JsonUtil();
        try {
          /* 案例尝试
           teachBeanKey.setCid("1002");
            teachBeanKey.setTid("1007");
           */

            // //检查插入的老师的课程是否已有了，存在不允许写入
            adminClassService.insertClassInfo(teachBeanKey);


            jsonUtil.setCode(0);
            jsonUtil.setMsg("插入成功");
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;

    }


    @RequestMapping("/deleteClass")
    @ResponseBody
    public JsonUtil deleteClass(TeachBeanKey teachBeanKey){

        JsonUtil jsonUtil = new JsonUtil();
        try {
          /* 案例尝试
            teachBeanKey.setTid("1007");
            teachBeanKey.setCid("1002");
         * */
            /*根据老师的tid和课程cid删除*/

            adminClassService.deleteTeachByTeachBean(teachBeanKey);
            jsonUtil.setCode(0);
            jsonUtil.setMsg("删除成功");
        }catch (Exception e){
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;

    }

    /*sql语句不好实现
    @RequestMapping("/editTeachClass")
    @ResponseBody
    public JsonUtil editTeachClass(TeachBeanKey teachBeanKey){

        JsonUtil jsonUtil = new JsonUtil();
        try {
          *//* 案例尝试

         * *//*
          teachBeanKey.setTid("1007");
            teachBeanKey.setCid("1004");
            *//*根据传入的bean更新老师的信息*//*
            adminClassService.editTeachClassByTeachBean(teachBeanKey);
            jsonUtil.setCode(0);
            jsonUtil.setMsg("更新成功");
        }catch (Exception e){
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;

    }*/

    @RequestMapping("/queryTeachClassList")
    @ResponseBody
    public JsonUtil queryTeachClassList(TeachBeanKey teachBeanKey){


        JsonUtil jsonUtil = new JsonUtil();
        try {


          /* 案例尝试
            teachBeanKey.setTid("1007");
            teachBeanKey.setCid("1003");
         * */

            /*根据传入的bean更新老师的信息*/
            List<TeachBeanKey> allList= adminClassService.selectTeachByExample(teachBeanKey);
            jsonUtil.setCode(0);
            jsonUtil.setMsg("查询成功");
            jsonUtil.setCount(allList.size());
            jsonUtil.setData(allList);
        }catch (Exception e){
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;

    }
}

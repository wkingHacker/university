package com.zx.controller;

import com.util.JsonUtil;
import com.util.JwtToken;
import com.zx.bean.OperatorBean;
import com.zx.bean.TeacherInfoBean;
import com.zx.dao.OperatorBeanMapper;
import com.zx.service.imp.AdminTeachImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author gwl
 * @create 2020-08-17 下午 7:11
 */
@RequestMapping("/adminTeach")
@Controller
public class AdminTeachController {

    @Autowired
    AdminTeachImpl adminTeachService;




        @RequestMapping("/addTeacher")
        @ResponseBody
    public JsonUtil addTeacher(TeacherInfoBean teacherInfoBean, HttpServletRequest request) throws AuthenticationException {

        JsonUtil jsonUtil = new JsonUtil();
        JwtToken jwt=new JwtToken();
          String loginId=jwt.getLoginUserId(request);
            System.out.println(loginId);
        try {
          /* 案例尝试
           teacherInfoBean.setLoginid("c506ae45934947379f0ffb4c3d60af07");
            teacherInfoBean.setTid("1003");
            teacherInfoBean.setSex("女");
            teacherInfoBean.setTname("swift2");*/

            // //检查插入的老师是否已有了，存在不允许写入

            teacherInfoBean.setLoginid(loginId);
            adminTeachService.insertTeach(teacherInfoBean);


            jsonUtil.setCode(0);
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;

    }

    @RequestMapping("/deleteTeacher")
    @ResponseBody
    public JsonUtil deleteTeacher(String tid){

        JsonUtil jsonUtil = new JsonUtil();
        try {
          /* 案例尝试
       tid ="1001";
         * */
          /*根据老师的tid删除老师信息，级联删除*/
            adminTeachService.deleteTeachByTid(tid);
            jsonUtil.setCode(0);
        }catch (Exception e){
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;

    }

    @RequestMapping("/editTeacherName")
    @ResponseBody
    public JsonUtil editTeacherName(TeacherInfoBean teacherInfoBean){

        JsonUtil jsonUtil = new JsonUtil();
        try {
          /* 案例尝试
           teacherInfoBean.setTid("1003");
            teacherInfoBean.setTname("taylor swift");
            teacherInfoBean.setSex("男");
            teacherInfoBean.setLoginid("c506ae45934947379f0ffb4c3d60af07");
         * */

            /*根据传入的bean更新老师的信息*/
            adminTeachService.updateTeacherName(teacherInfoBean);
            jsonUtil.setCode(0);
        }catch (Exception e){
            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;

    }

    @RequestMapping("/queryTeacherList")
    @ResponseBody
    public JsonUtil queryTeacherList(TeacherInfoBean teacherInfoBean){


        JsonUtil jsonUtil = new JsonUtil();
        try {

           /* teacherInfoBean.setTname("taylor");*/
          /* 案例尝试
           teacherInfoBean.setTid("1003");
            teacherInfoBean.setTname("taylor swift");
            teacherInfoBean.setSex("男");
            teacherInfoBean.setLoginid("c506ae45934947379f0ffb4c3d60af07");
         * */

            /*根据传入的bean更新老师的信息*/
          List<TeacherInfoBean>  allList= adminTeachService.selectTeacherByExample(teacherInfoBean);
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

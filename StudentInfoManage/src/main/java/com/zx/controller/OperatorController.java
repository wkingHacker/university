package com.zx.controller;


import com.util.*;

import com.zx.bean.ManagerBean;
import com.zx.bean.OperatorBean;
import com.zx.bean.MenuBean;
import com.zx.bean.Photo;
import com.zx.dao.ManagerBeanMapper;
import com.zx.dao.OperatorBeanMapper;
import com.zx.dao.PhotoMapper;
import com.zx.service.OperatorService;
import com.zx.service.imp.OperatorServiceImpl;
import io.jsonwebtoken.Claims;

import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/operator")
@Controller

public class OperatorController {

    @Autowired
    ManagerBeanMapper managerBeanMapper;

    @RequestMapping("/getLoginUserMenu")
    @ResponseBody
    public JsonUtil getLoginUserMenu(HttpServletRequest request){

        JsonUtil jsonUtil = new JsonUtil();
        List<MenuLeave1Bean> leave1MenuTree = new ArrayList<>();
        try {
            JwtToken jwtToken = new JwtToken();
            String operatorId = jwtToken.getLoginUserId(request);
            jwtToken.getClaimByToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjNTA2YWU0NTkzNDk0NzM3OWYwZmZiNGMzZDYwYWYwNyIsImlhdCI6MTU5NzczMzM0OSwiZXhwIjoxNTk3NzYyMTQ5fQ.sWko2QCD_Ok8UNnFjIOVnjYLwxTC02Ej14vILm3DrHlis-f7wYefbY0oyNOZu0aCE5QTwK_6Lza59B4cQ49uhg");
            //获得登录用户能够访问的菜单列表
            //返回一个与menu.js文件中格式相同的 json格式字符串
            System.out.println("当前登录用户的id为:"+operatorId);

            List<MenuBean> menuBeans = operatorService.getLoginUserMenu(operatorId);

            System.out.println("当前登录用户能够访问菜单 数量 为:"+menuBeans.size());
            System.out.println(menuBeans.get(1).getMenuurl());
            //获得所有的parentmenuid 为空的MenuBean对象 做为一级菜单
            //再找出 parentmenuid=一级菜单的menuid相同的数据



            for (MenuBean m : menuBeans) {

                if (m.getParentmenuid() == null || m.getParentmenuid().equals("")) {
                    //一级菜单（一级菜单可能有N个，因此用List集合存储）
                    MenuLeave1Bean mt = new MenuLeave1Bean();

                  /*  mt.setIcon(m.getIcon());*/
                    mt.setTitle(m.getTitle());

                    mt.setIcon("menu-icon");
                    leave1MenuTree.add(mt);//把一级菜单加到List集合中
                    ///  mt.setList();//二级菜单
                    //再次遍历menuBeans集合 ，获得Parentmenuid=m.getMenuid的MenuBean对象
                    List<MenuLeave2Bean> leave2List = new ArrayList<>();
                    for (MenuBean m2 : menuBeans) {
                        if (m.getMenuid().equals(m2.getParentid())) {
                            MenuLeave2Bean mtLeave2 = new MenuLeave2Bean();
                          /*  mtLeave2.setJump(m2.getJump());*/
                            System.out.println("URL:s"+m2.getMenuurl());
                            mtLeave2.setJump(m2.getMenuurl());
                            mtLeave2.setTitle(m2.getTitle());
                            leave2List.add(mtLeave2);//把二级菜单 对象 加到leave2List集合中
                        }
                    }
                    mt.setList(leave2List);//添加二级菜单集合
                }

            }
        }catch (Exception e){
            jsonUtil.setCode(1);
            jsonUtil.setMsg("系统 错误");
        }




        jsonUtil.setData(leave1MenuTree);

        return jsonUtil;
    }

    @RequestMapping("/getTestLoginUserMenu")
    @ResponseBody
    public JsonUtil getTestLoginUserMenu(HttpServletRequest request){

        JsonUtil jsonUtil = new JsonUtil();
        List<MenuLeave1Bean> leave1MenuTree = new ArrayList<>();
        try {

            JwtToken jwtToken = new JwtToken();
            String operatorId = jwtToken.getLoginUserId(request);
            //获得登录用户能够访问的菜单列表
            //返回一个与menu.js文件中格式相同的 json格式字符串
            request.getParameter("login");

            List<MenuBean> menuBeans = operatorService.getLoginUserMenu(operatorId);
            System.out.println("当前用户id： "+operatorId);

            System.out.println("当前登录用户能够访问菜单 数量 为:"+menuBeans.size());
            System.out.println(menuBeans.get(1).getMenuurl());
            //获得所有的parentmenuid 为空的MenuBean对象 做为一级菜单
            //再找出 parentmenuid=一级菜单的menuid相同的数据



            for (MenuBean m : menuBeans) {
                System.out.println(m.getMenuid() + " "+ m.getParentmenuid());

                if (m.getParentid() == null || m.getParentid().equals("")) {
                    //一级菜单（一级菜单可能有N个，因此用List集合存储）
                    MenuLeave1Bean mt = new MenuLeave1Bean();

                     mt.setIcon("menu-icon");
                    mt.setTitle(m.getTitle());
                    leave1MenuTree.add(mt);//把一级菜单加到List集合中
                    ///  mt.setList();//二级菜单
                    //再次遍历menuBeans集合 ，获得Parentmenuid=m.getMenuid的MenuBean对象
                    List<MenuLeave2Bean> leave2List = new ArrayList<>();
                    for (MenuBean m2 : menuBeans) {
                        if (m.getMenuid().equals(m2.getParentid())) {
                            MenuLeave2Bean mtLeave2 = new MenuLeave2Bean();
                            /*  mtLeave2.setJump(m2.getJump());*/
                            mtLeave2.setTitle(m2.getTitle());
                            mtLeave2.setJump(m2.getMenuurl());

                            leave2List.add(mtLeave2);//把二级菜单 对象 加到leave2List集合中
                        }
                    }
                    mt.setList(leave2List);//添加二级菜单集合
                }

            }
        }catch (Exception e){
            jsonUtil.setCode(1);
            jsonUtil.setMsg("系统 错误");
        }





        jsonUtil.setData(leave1MenuTree);

        return jsonUtil;
    }

    @Autowired
    OperatorServiceImpl operatorService;
    @RequestMapping("/userLogin")
    @ResponseBody
    public JsonUtil userLogin(OperatorBean  operatorBean, HttpServletRequest request) {
        JsonUtil jsonUtil = new JsonUtil();
        try {
            //获得用户名密码，在数据库中查询对应的数据，找到了表示用户名密码正确
            String ip = request.getRemoteAddr();//客户端电脑的IP
            //
            OperatorBean loginInfo = operatorService.userLogin(operatorBean);
            if (loginInfo != null) {
               /* loginInfo.setLastloginip(ip);
                loginInfo.setLastlogintime(new Date());*/
                operatorService.update(loginInfo);
                jsonUtil.setCode(0);
                jsonUtil.setMsg("登入成功");

                JwtToken jwtToken = new JwtToken();
                //把登录用户的id 加密生成JWT TOKEN值
                String accessToken =  jwtToken.generateToken(loginInfo.getLoginid());

                jsonUtil.setData(accessToken);
                //request.getSession().setAttribute("xxx");
            } else {
                //返回用户名或密码错误
                jsonUtil.setCode(1);
                jsonUtil.setMsg("用户名或密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonUtil.setCode(500);
            jsonUtil.setMsg("系统 错误");
        }
        return jsonUtil;
    }


    @RequestMapping("/getName")
    @ResponseBody
    public JsonUtil getOperator(HttpServletRequest request) throws AuthenticationException {
        JsonUtil jsonUtil = new JsonUtil();
        try {
            JwtToken jwtToken=new JwtToken();
            String loginUserId = jwtToken.getLoginUserId(request);
            OperatorBean operatorBean = operatorService.selectByPrimaryKey(loginUserId);
            List<Photo> psrc = operatorService.getPsrc(operatorBean.getLoginname());
            operatorBean.setSrc(psrc.get(0).getSrc());
            jsonUtil.setData(operatorBean);
            jsonUtil.setCode(0);
            jsonUtil.setMsg("用户名查询成功");

        }
        catch (Exception e){
            jsonUtil.setCode(500);
        jsonUtil.setMsg(e.getMessage());
        }
        return jsonUtil;
    }


    @RequestMapping("/add")
    @ResponseBody
    public JsonUtil add( OperatorBean  operatorBean,HttpServletRequest request){

        JsonUtil jsonUtil = new JsonUtil();
        try {
            //检查登录名否已存在，存在不允许写入

            //MVC  View  Controller Model(Service,Dao[mapper])
            System.out.println(operatorBean.getLoginname());
            operatorBean.setLastLoginIp(request.getRemoteAddr());
            operatorService.insert(operatorBean);
            jsonUtil.setCode(0);
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;
    }


    @RequestMapping("/edit")
    @ResponseBody
    public JsonUtil edit( OperatorBean  operatorBean,HttpServletRequest request) throws AuthenticationException {
        String oldname = request.getParameter("oldname");
        System.out.println(oldname);
        JsonUtil jsonUtil = new JsonUtil();
        OperatorBean o = operatorService.getLoginid(oldname);
        String loginid = o.getLoginid();

        System.out.println("loginid: "+loginid);
        try{
            operatorBean.setLoginid(loginid);
            operatorService.update(operatorBean);


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




    //获得所有的操作员的信息
    @RequestMapping("/list")
    @ResponseBody
    public JsonUtil list(OperatorBean operatorBean){




        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.setCode(0);
        jsonUtil.setMsg("查询成功");


        List<OperatorBean> beanList =  operatorService.selectByExample(operatorBean);// operatorBeanMapper.selectByExample(null);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jsonUtil.setCount(beanList.size());
        jsonUtil.setData(beanList);
        return jsonUtil;

    }
    @RequestMapping("/del")
    @ResponseBody
    public JsonUtil delObj(HttpServletRequest res){
        String id = res.getParameter("loginid");
        JsonUtil jsonUtil = new JsonUtil();


        System.out.println("删除的id为： "+id);
        try { operatorService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonUtil.setMsg(e.getMessage());
            jsonUtil.setCode(500);
        }
        jsonUtil.setCode(0);
        jsonUtil.setMsg("删除成功");
        return jsonUtil;

    }

    @RequestMapping("/upload")
    @ResponseBody
public JsonUtil upload(MultipartFile file,HttpServletRequest request)
    {
        JsonUtil js=new JsonUtil();
        try
        {
            String originalFilename = file.getOriginalFilename();
            String savepath="D:\\code\\StudentInfoManage\\src\\main\\resources\\static\\start";
            File file1 = new File(savepath, originalFilename);
            InputStream inputStream = file.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            IOUtils.copy(inputStream,fileOutputStream);
            js.setMsg("上传成功");
            js.setCode(0);
            Photo p=new Photo();
            p.setSrc(originalFilename);
            p.setLOGINNAME(request.getParameter("loginname"));
            int i = operatorService.storePsrc(p);
            js.setData(p);
        }catch(Exception e){
            js.setCode(500);
            js.setMsg("文件已存在");
            
        }
        return js;
    }
    @ResponseBody
    @RequestMapping("/save")
    public JsonUtil saveUser(ManagerBean mb, HttpServletRequest request)
    {JsonUtil js=new JsonUtil();
        try{
            String loginname = request.getParameter("loginname");
            JwtToken jwtToken=new JwtToken();
            String loginUserId = jwtToken.getLoginUserId(request);
            String ip = request.getRemoteAddr();
            mb.setLastLoginIp(ip);
            mb.setLoginid(loginUserId);
            managerBeanMapper.insert(mb);
            js.setData(mb);
            js.setMsg("保存用户成功");
        }catch (Exception e){
            js.setCode(500);
            js.setMsg(e.getMessage());
        }
        return js;
    }
}


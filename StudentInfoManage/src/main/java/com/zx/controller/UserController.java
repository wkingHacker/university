package com.zx.controller;

import com.util.JsonUtil;
import com.util.JwtToken;
import com.util.UUIdutil;
import com.zx.bean.ManagerBean;
import com.zx.bean.OperatorBean;
import com.zx.bean.RoleGroupBean;
import com.zx.dao.ManagerBeanMapper;
import com.zx.dao.OperatorBeanMapper;
import com.zx.dao.RoleGroupBeanMapper;
import com.zx.service.OperatorService;
import com.zx.service.imp.OperatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;


/**
 * @author Wking
 * @create 2020-08-17 17:17
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    ManagerBeanMapper managerBeanMapper;
    @Autowired
    OperatorBeanMapper operatorBeanMapper;
    @Autowired
    RoleGroupBeanMapper roleGroupBeanMapper;
    @Autowired
    OperatorServiceImpl operatorService;
    @ResponseBody
    @RequestMapping("/list")
    public JsonUtil showAllUser(HttpServletRequest request)
    {
        List<OperatorBean> operatorBeans = operatorBeanMapper.selectByExample(null);

        for(int i=0;i<operatorBeans.size();i++)
        {
            String groupid = operatorBeans.get(i).getGroupid();
            String ip = request.getRemoteAddr();

            RoleGroupBean roleGroupBean = roleGroupBeanMapper.selectByPrimaryKey(groupid);

            operatorBeans.get(i).setRoleGroupBeans(roleGroupBean);
            operatorBeans.get(i).setLastLoginIp(ip);
        }

        JsonUtil js=new JsonUtil();
        js.setCode(0);
        js.setCount(operatorBeans.size());
        js.setData(operatorBeans);
        js.setMsg("查询用户成功");
        return js;
    }
    @ResponseBody
    @RequestMapping("/add")
    public JsonUtil addUser(ManagerBean mb)
    {JsonUtil js=new JsonUtil();
        try{
            String uuid = UUIdutil.uuid();
            mb.setLoginid(uuid);
            managerBeanMapper.insert(mb);
            js.setCode(0);
            js.setMsg("添加用户成功");
        }
        catch (Exception e)
        {js.setCode(500);
            js.setMsg(e.getMessage());
        }
        return js;
    }
    @ResponseBody
    @RequestMapping("/delete")
    public JsonUtil deleteUser(ManagerBean mb)
    {

        JsonUtil js=new JsonUtil();
        try{
            managerBeanMapper.deleteByPrimaryKey(mb.getManagerid());
            js.setCode(0);
            js.setMsg("删除用户成功");
        }
        catch (Exception e)
        {
            js.setCode(500);
            js.setMsg(e.getMessage());
        }



        return js;
    }
    @ResponseBody
    @RequestMapping("/change")
    public JsonUtil changeUser(ManagerBean mb)
    {JsonUtil js=new JsonUtil();
        try{
            managerBeanMapper.updateByPrimaryKey(mb);
            js.setCode(0);
            js.setMsg("修改用户成功");
        }catch (Exception e){
            js.setCode(500);
            js.setMsg(e.getMessage());
        }
        return js;
    }

}

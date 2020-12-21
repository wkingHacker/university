package com.zx.controller;


import com.util.JsonUtil;
import com.util.JwtToken;
import com.zx.bean.MenuBean;
import com.zx.bean.OperatorBean;
import com.zx.bean.RoleGroupBean;
import com.zx.bean.ZtreeNode;
import com.zx.service.imp.MenuServiceImpl;
import com.zx.service.imp.RoleGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/roleGroup")
@Controller
public class RoleGroupController {



    @Autowired
    RoleGroupServiceImpl roleGroupService;

    @RequestMapping("/setRoleGroup")
    @ResponseBody
    public JsonUtil setRoleGroup( String groupid,String menuids){

        JsonUtil jsonUtil = new JsonUtil();
        try {
            //检查登录名否已存在，存在不允许写入

            //MVC  View  Controller Model(Service,Dao[mapper])

            System.out.println("权限组编号为"+groupid);

            System.out.println("被选中的菜单编号为"+menuids);

            roleGroupService.updateRoleGroup(groupid,menuids);
            jsonUtil.setCode(0);
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;
    }

    @RequestMapping("/add")
    @ResponseBody
    public JsonUtil add( OperatorBean operatorBean){

        JsonUtil jsonUtil = new JsonUtil();
        try {
            //检查登录名否已存在，存在不允许写入

            //MVC  View  Controller Model(Service,Dao[mapper])


            jsonUtil.setCode(0);
        }catch (Exception e){

            jsonUtil.setCode(500);
            jsonUtil.setMsg(e.getMessage());
        }

        return jsonUtil;
    }


    @RequestMapping("/edit")
    @ResponseBody
    public JsonUtil edit( OperatorBean  operatorBean){

        JsonUtil jsonUtil = new JsonUtil();


        return jsonUtil;
    }


    //目前缺少service层，之后再补充
    //先完成 查询操作,调用 Mapper接口的查询方法

    @Autowired
    MenuServiceImpl menuService;

    @RequestMapping("/queryRoleGroupMenus")
    @ResponseBody
    public JsonUtil queryRoleGroupMenus(String groupid, HttpServletRequest request){
        JsonUtil jsonUtil = new JsonUtil();
        //获得系统 中的所有菜单，形成与ztree节点属性相同的对象

        //获得当前编辑的权限组能够访问的菜单 权限，与系统中的所有菜单做比较，当前权限组能够访问的菜单在显示时应该是默认被选中


        List<MenuBean> menuBeanList =  menuService.selectByExample(null);
List<MenuBean> groupMenuList=menuService.selectByGroupId(groupid);

        List<ZtreeNode> treeNodes = new ArrayList<>();
        for(MenuBean mb :menuBeanList){
            ZtreeNode zn = new ZtreeNode();

            zn.setId(mb.getMenuid());
            zn.setpId(mb.getParentid());//父节点编号
            zn.setName(mb.getTitle());
            for(MenuBean groupMenu:groupMenuList){
                if(mb.getMenuid().equals(groupMenu.getMenuid())){
                    zn.setChecked(true);
                }
            }

            treeNodes.add(zn);//把每一个树节点放到treeNodes集合中
        }

        jsonUtil.setData(treeNodes);


        return jsonUtil;

    }







    //获得所有的操作员的信息
    @RequestMapping("/list")
    @ResponseBody
    public JsonUtil list(RoleGroupBean roleGroupBean, HttpServletRequest request){


        JwtToken jwtToken = new JwtToken();
        JsonUtil jsonUtil = new JsonUtil();
        System.out.println(request);
        try {
            String operatorId =   jwtToken.getLoginUserId(request);
            System.out.println("登录用户的ID值 为:"+operatorId);


            jsonUtil.setCode(0);
            jsonUtil.setMsg("查询成功");

            List<RoleGroupBean> beanList =  roleGroupService.selectByExample(roleGroupBean);// operatorBeanMapper.selectByExample(null);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jsonUtil.setCount(beanList.size());
            jsonUtil.setData(beanList);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            jsonUtil.setCode(1001);
            jsonUtil.setMsg("会话超时，请重新登录");
        }



        return jsonUtil;

    }
}

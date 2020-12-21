package com.zx.service.imp;

import com.zx.bean.ManagerBean;
import com.zx.bean.MenuBean;
import com.zx.dao.MenuBeanMapper;
import com.zx.dao.OperatorBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuServiceImpl {


    @Autowired
    OperatorBeanMapper operatorBeanMapper;



    @Autowired
    MenuBeanMapper menuBeanMapper;
    public List<MenuBean> selectByExample(MenuBean menuBean) {



        return menuBeanMapper.selectByExample(null);
    }


    public List<MenuBean> selectByGroupId(String groupid) {
        List<MenuBean> menuBeans= menuBeanMapper.selectMenuByGroupId(groupid);
        return menuBeans;
    }
}

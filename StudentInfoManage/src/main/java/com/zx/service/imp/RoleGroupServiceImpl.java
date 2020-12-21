package com.zx.service.imp;

import com.zx.bean.OperatorBean;
import com.zx.bean.RoleGroupBean;
import com.zx.bean.RoleMenuBeanExample;
import com.zx.bean.RoleMenuBeanKey;
import com.zx.dao.RoleGroupBeanMapper;
import com.zx.dao.RoleMenuBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleGroupServiceImpl {


    @Autowired
    RoleGroupBeanMapper roleGroupBeanMapper;
    @Autowired
    RoleMenuBeanMapper roleMenuBeanMapper;


    public int deleteByPrimaryKey(String operatorid) {
        return roleGroupBeanMapper.deleteByPrimaryKey(operatorid);
    }


    public int insert(RoleGroupBean roleGroupBean ){
        // //检查登录名否已存在，存在不允许写入

        return roleGroupBeanMapper.insert(roleGroupBean);
    }


    public List<RoleGroupBean> selectByExample(RoleGroupBean roleGroupBean) {

        return roleGroupBeanMapper.selectByExample(null);
    }


    public OperatorBean selectByPrimaryKey(String operatorid) {
        return null;
    }


    public int update(RoleGroupBean roleGroupBean) {
        return roleGroupBeanMapper.updateByPrimaryKey(roleGroupBean);
    }

    public void updateRoleGroup(String groupid, String menuids) {
        RoleMenuBeanExample example=new RoleMenuBeanExample();
        RoleMenuBeanExample.Criteria criteria=example.createCriteria();
        criteria.andGroupidEqualTo(groupid);
       roleMenuBeanMapper.deleteByExample(example);

       String[] mIds=menuids.split(",");
       for (String menuid:mIds){
           RoleMenuBeanKey roleMenuBeanKey=new RoleMenuBeanKey();
           roleMenuBeanKey.setGroupid(groupid);
           roleMenuBeanKey.getMenuid(menuid);
           roleMenuBeanMapper.insert(roleMenuBeanKey);
       }


    }
}

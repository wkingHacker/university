package com.zx.service.imp;


import com.util.UUIdutil;
import com.zx.bean.MenuBean;
import com.zx.bean.OperatorBean;
import com.zx.bean.OperatorBeanExample;
import com.zx.bean.Photo;
import com.zx.dao.MenuBeanMapper;
import com.zx.dao.OperatorBeanMapper;
import com.zx.dao.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class OperatorServiceImpl {


    @Autowired
    OperatorBeanMapper operatorBeanMapper;


    public int deleteByPrimaryKey(String operatorid) {
        return operatorBeanMapper.deleteByPrimaryKey(operatorid);
    }


    public int insert(OperatorBean record) {
        // //检查登录名否已存在，存在不允许写入
        String loginName =  record.getLoginname();
        System.out.println("新添加的用户名是： "+loginName);
        OperatorBeanExample oe=new OperatorBeanExample();
        OperatorBeanExample.Criteria criteria = oe.createCriteria();
        criteria.andLoginnameEqualTo(loginName);
        List<OperatorBean> operatorBeans = operatorBeanMapper.selectByExample(oe);


        if (operatorBeans.size()>0){
            throw new RuntimeException("登录名已存在");
        }
            record.setLoginid(UUIdutil.uuid());

        return operatorBeanMapper.insert(record);
    }


    public List<OperatorBean> selectByExample(OperatorBean operatorBean) {


        //判断OperatorBean中的某些属性是否不为空， 不为空则按相应的属性查询
        OperatorBeanExample example = new OperatorBeanExample();

        OperatorBeanExample.Criteria criteria = example.createCriteria();
        if(operatorBean!=null){
            if(operatorBean.getLoginname()!=null && !operatorBean.getLoginname().equals("")){
                criteria.andLoginnameLike(operatorBean.getLoginname()+"%");

            }

           /* if(operatorBean.getPhone()!=null && !operatorBean.getPhone().equals("")){
                criteria.andPhoneLike(operatorBean.getPhone()+"%");

            }*/
        }
        return operatorBeanMapper.selectByExample(example);
    }


    public OperatorBean selectByPrimaryKey(String operatorid) {
        return operatorBeanMapper.selectByPrimaryKey(operatorid);
    }


    public int update(OperatorBean record) {
        return operatorBeanMapper.updateByPrimaryKey(record);
    }

    public OperatorBean userLogin(OperatorBean operatorBean) {

        OperatorBeanExample example = new OperatorBeanExample();
        OperatorBeanExample.Criteria criteria = example.createCriteria();

        criteria.andLoginnameEqualTo(operatorBean.getLoginname());
        criteria.andLoginpwdEqualTo(operatorBean.getLoginpwd());

        List<OperatorBean>  list = operatorBeanMapper.selectByExample(example);
        if (list.size()>0){
            return list.get(0);//取出集合中的第个元素，就是当前登录用户的基本信息
        }
        return null;
    }

    @Autowired
    MenuBeanMapper menuBeanMapper;

    public List<MenuBean> getLoginUserMenu(String operatorId) {
        return  menuBeanMapper.getLoginUserMenu(operatorId);
    }
@Autowired
    PhotoMapper photoMapper;
    public List<Photo> getPsrc(String loginname){

        return photoMapper.getUserPhotoInfo(loginname);
    }
    public int  storePsrc(Photo photo){

        List<Photo> count = photoMapper.getUserPhotoInfo(photo.getLOGINNAME());
        if(count.size()!=0){
              delPsrc(count.get(0));

            photoMapper.insert(photo);
                return 1;


        }
        else{
            photoMapper.insert(photo);
        }
        return 1;

    }
    public List<Photo> delPsrc(Photo photo){

        return photoMapper.delUserPhotoInfo(photo);
    }
    public OperatorBean getLoginid(String loginname)
    {
        return operatorBeanMapper.getloginid(loginname);
    }
}

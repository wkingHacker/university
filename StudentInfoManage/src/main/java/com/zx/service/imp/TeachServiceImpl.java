package com.zx.service.imp;


import com.zx.bean.TeachBeanExample;
import com.zx.bean.TeachBeanKey;
import com.zx.dao.TeachBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName TeachServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/8/17 21:43
 * @Version 1.0
 **/
@Service
public class TeachServiceImpl {
    @Autowired
    TeachBeanMapper teachBeanMapper;


    public int deleteByPrimaryKey(String tid) {
        return teachBeanMapper.deleteByPrimaryKey(tid);
    }

    public int insert(TeachBeanKey record) {
        // //检查教师名否已存在，存在不允许写入
        String tid =  record.getTid();

        TeachBeanExample example = new TeachBeanExample();
        TeachBeanExample.Criteria criteria =   example.createCriteria();
        criteria.andTidLike(tid);
        List<TeachBeanKey> teachBeanKeys =  teachBeanMapper.selectByExample(example);
        if (teachBeanKeys.size()>0){
            throw new RuntimeException("教师id已存在");
        }


        return teachBeanMapper.insert(record);
    }



    public List<TeachBeanKey> selectByExample(TeachBeanKey teachBeanKey) {


        //判断TeacherInfoBean中的某些属性是否不为空， 不为空则按相应的属性查询
        TeachBeanExample example = new TeachBeanExample();

        TeachBeanExample.Criteria criteria = example.createCriteria();
        if(teachBeanKey!=null){
            if(teachBeanKey.getTid()!=null && !teachBeanKey.getTid().equals("")){
                criteria.andTidLike(teachBeanKey.getTid()+"%");

            }

            if(teachBeanKey.getCid()!=null && !teachBeanKey.getCid().equals("")){
                criteria.andCidLike(teachBeanKey.getCid()+"%");

            }

           /* if(TeacherInfoBean.getPhone()!=null && !TeacherInfoBean.getPhone().equals("")){
                criteria.andPhoneLike(TeacherInfoBean.getPhone()+"%");

            }*/
        }
        return teachBeanMapper.selectByExample(example);
    }


    public TeachBeanKey selectByPrimaryKey(String tid) {
        return teachBeanMapper.selectByPrimaryKey(tid);
    }


    public int update(TeachBeanKey record) {
        return teachBeanMapper.updateByPrimaryKey(record);
    }

    public void deleteTeachByCid(String cid) {

        /*级联删除老师信息*/


        teachBeanMapper.deleteByPrimaryKeyTeach_info(cid);
    }




}


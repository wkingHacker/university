package com.zx.service.imp;


import com.zx.bean.TeacherInfoBean;
import com.zx.bean.TeacherInfoBeanExample;
import com.zx.dao.TeacherInfoBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName TeacherInfoServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/8/17 18:10
 * @Version 1.0
 **/
@Service
public class TeacherInfoServiceImpl {
    @Autowired
    TeacherInfoBeanMapper teacherInfoBeanMapper;


    public int deleteByPrimaryKey(String tid) {
        Object[] array;
        List tidlist = Arrays.asList(tid.split(","));
        tidlist.forEach(id->{
            Integer teacherId=Integer.parseInt((String) tid);
            teacherInfoBeanMapper.deleteByPrimaryKey(tid);
        });



        return teacherInfoBeanMapper.deleteByPrimaryKey(tid);
    }


    public int insert(TeacherInfoBean teacherInfoBean){
        // //检查插入的老师是否已有了，存在不允许写入
        String Tid=teacherInfoBean.getTid();
        TeacherInfoBeanExample example=new TeacherInfoBeanExample();
        TeacherInfoBeanExample.Criteria criteria= example.createCriteria();
        criteria.andTidEqualTo(Tid);
        List<TeacherInfoBean> teacherInfoBeans= teacherInfoBeanMapper.selectByExample(example);
        if(teacherInfoBeans.size()>0){
            throw new RuntimeException("老师ID已存在");
        };
     /*   teacherInfoBean.setLoginid("1");
        teacherInfoBean.setTid("1001");
        teacherInfoBean.setSex("女");
        teacherInfoBean.setTname("swift");*/

        return teacherInfoBeanMapper.insert(teacherInfoBean);
    }

    public List<TeacherInfoBean> selectByExample(TeacherInfoBean teacherInfoBean) {


        //判断TeacherInfoBean中的某些属性是否不为空， 不为空则按相应的属性查询
        TeacherInfoBeanExample example = new TeacherInfoBeanExample();

        TeacherInfoBeanExample.Criteria criteria = example.createCriteria();
        if(teacherInfoBean!=null){
            if(teacherInfoBean.getTname()!=null && !teacherInfoBean.getTname().equals("")){
                criteria.andTnameLike(teacherInfoBean.getTname()+"%");

            }

           /* if(TeacherInfoBean.getPhone()!=null && !TeacherInfoBean.getPhone().equals("")){
                criteria.andPhoneLike(TeacherInfoBean.getPhone()+"%");

            }*/
        }
        return teacherInfoBeanMapper.selectByExample(example);
    }


    public TeacherInfoBean selectByPrimaryKey(String tid) {
        return teacherInfoBeanMapper.selectByPrimaryKey(tid);
    }


    public int update(TeacherInfoBean record) {
        return teacherInfoBeanMapper.updateByPrimaryKey(record);
    }



    public void deleteTeachByTid(String tid) {

        /*级联删除老师信息*/

        teacherInfoBeanMapper.deleteByPrimaryKeyCascadeTeach(tid);
        teacherInfoBeanMapper.deleteByPrimaryKeyTeach_info(tid);
    }




    public int deleteUserAllById(List<String> tid) {
        //创建一个map对象，map里面有个list集合
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("tid",tid );
        return teacherInfoBeanMapper.deleteUserAllById(map);
    }


}

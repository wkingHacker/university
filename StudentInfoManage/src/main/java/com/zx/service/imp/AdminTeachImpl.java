package com.zx.service.imp;

import com.zx.bean.OperatorBean;
import com.zx.bean.OperatorBeanExample;
import com.zx.bean.TeacherInfoBean;
import com.zx.bean.TeacherInfoBeanExample;
import com.zx.dao.ManagerBeanMapper;
import com.zx.dao.TeacherInfoBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author gwl
 * @create 2020-08-17 下午 7:25
 */

@Service
public class AdminTeachImpl {



    @Autowired
    TeacherInfoBeanMapper teacherInfoBeanMapper;

    public int insertTeach(TeacherInfoBean teacherInfoBean){
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

    public void deleteTeachByTid(String tid) {

        /*级联删除老师信息*/

        teacherInfoBeanMapper.deleteByPrimaryKeyCascadeTeach(tid);
        teacherInfoBeanMapper.deleteByPrimaryKeyTeach_info(tid);
    }


        /*更新表信息通过主键值*/
    public int updateTeacherName(TeacherInfoBean teacherInfoBean) {

      return  teacherInfoBeanMapper.updateByPrimaryKey(teacherInfoBean);
    }


    /*按一定条件查询*/
    public List<TeacherInfoBean> selectTeacherByExample(TeacherInfoBean teacherInfoBean) {

        //判断TeacherInfoBean中的某些属性是否不为空， 不为空则按相应的属性查询
        TeacherInfoBeanExample example = new TeacherInfoBeanExample();


        TeacherInfoBeanExample.Criteria criteria=example.createCriteria();
        if(teacherInfoBean!=null){
            if(teacherInfoBean.getTid()!=null && !teacherInfoBean.getTid().equals("")){
                criteria.andTidLike(teacherInfoBean.getTid()+"%");
            }
            if(teacherInfoBean.getTname()!=null && !teacherInfoBean.getTname().equals("")){
                criteria.andTnameLike(teacherInfoBean.getTname()+"%");
            }


        }
        return  teacherInfoBeanMapper.selectByExample(example);
    }
}

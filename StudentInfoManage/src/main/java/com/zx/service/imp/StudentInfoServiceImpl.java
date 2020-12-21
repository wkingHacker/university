package com.zx.service.imp;

import com.zx.bean.StudentInfoBean;
import com.zx.bean.StudentInfoBeanExample;
import com.zx.dao.StudentInfoBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WCY
 * @create 2020-08-18 11:03
 */

@Service
public class StudentInfoServiceImpl {

    @Autowired
    StudentInfoBeanMapper studentInfoBeanMapper;

    //添加学生
    //检查登录名否已存在，存在不允许写入!

    public int insert(StudentInfoBean record){

        return studentInfoBeanMapper.insert(record);
    }
    //根据学号删除学生
    public int deleteByPrimaryKey(String operatorid){
        return studentInfoBeanMapper.deleteByPrimaryKey(operatorid);
    }

    //根据学号查询学生信息
    public StudentInfoBean selectByPrimaryKey(String operatorid){
        return studentInfoBeanMapper.selectByPrimaryKey(operatorid);
    }

    //根据学号修改学生信息
    public int update(StudentInfoBean record){
        return studentInfoBeanMapper.updateByPrimaryKey(record);
    }

    //学生信息浏览
    public List<StudentInfoBean> selectByExample(StudentInfoBean studentInfoBean){
        //判断StudentInfoBean中的某些属性是否不为空， 不为空则按相应的属性查询
        StudentInfoBeanExample example = new StudentInfoBeanExample();
        StudentInfoBeanExample.Criteria criteria = example.createCriteria();
        if(studentInfoBean!=null) {
            if (studentInfoBean.getLoginid() != null && !studentInfoBean.getLoginid().equals("")) {
                criteria.andLoginidLike(studentInfoBean.getLoginid() + "%");

            }
        }
        return studentInfoBeanMapper.selectByExample(studentInfoBean);
    }
    public int countByExample(StudentInfoBeanExample example){ return studentInfoBeanMapper.countByExample(example); }
}

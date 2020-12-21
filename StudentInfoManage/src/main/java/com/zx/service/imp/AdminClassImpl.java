package com.zx.service.imp;

import com.zx.bean.TeachBeanExample;
import com.zx.bean.TeachBeanKey;
import com.zx.bean.TeacherInfoBean;
import com.zx.bean.TeacherInfoBeanExample;
import com.zx.dao.TeachBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gwl
 * @create 2020-08-20 上午 11:57
 */
@Service
public class AdminClassImpl {

   @Autowired
    TeachBeanMapper teachBeanMapper;


    public int insertClassInfo(TeachBeanKey teachBeanKey) {
        // //检查插入的老师所教课程是否已有了，存在不允许写入
        /**/

        String Tid=teachBeanKey.getTid();
        String Cid=teachBeanKey.getCid();
        TeachBeanExample example=new TeachBeanExample();
        TeachBeanExample.Criteria criteria=example.createCriteria();
        criteria.andTidEqualTo(Tid);
        criteria.andCidEqualTo(Cid);
        List<TeachBeanKey> teachBeanKeys= teachBeanMapper.selectByExample(example);
        if(teachBeanKeys.size()>0){
            throw new RuntimeException("老师教的课程已存在");
        };


        return teachBeanMapper.insert(teachBeanKey);

    }

    public int deleteTeachByTeachBean(TeachBeanKey teachBeanKey) {
        String Tid=teachBeanKey.getTid();
        String Cid=teachBeanKey.getCid();
        TeachBeanExample example=new TeachBeanExample();
        TeachBeanExample.Criteria criteria=example.createCriteria();
        criteria.andTidEqualTo(Tid);
        criteria.andCidEqualTo(Cid);
        return  teachBeanMapper.deleteByExample(example);
    }

    public List<TeachBeanKey> selectTeachByExample(TeachBeanKey teachBeanKey) {

        //判断TeachBeanKey中的某些属性是否不为空， 不为空则按相应的属性查询
        TeachBeanExample example = new TeachBeanExample();

        TeachBeanExample.Criteria criteria=example.createCriteria();
        if(teachBeanKey!=null){

            if(teachBeanKey.getCid()!=null && !teachBeanKey.getCid().equals("")){
                criteria.andCidLike(teachBeanKey.getCid()+"%");
            }
            if(teachBeanKey.getTid()!=null && !teachBeanKey.getTid().equals("")){
                criteria.andTidLike(teachBeanKey.getTid()+"%");
            }


        }
        return  teachBeanMapper.selectByExample(example);
    }

 /* sql语句不好实现
   public int  editTeachClassByTeachBean(TeachBeanKey teachBeanKey) {

       return teachBeanMapper.updateByTeachBeanKey(teachBeanKey);
    }*/
}

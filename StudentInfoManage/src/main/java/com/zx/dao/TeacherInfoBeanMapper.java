package com.zx.dao;

import com.zx.bean.TeacherInfoBean;
import com.zx.bean.TeacherInfoBeanExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherInfoBeanMapper {
    int countByExample(TeacherInfoBeanExample example);

    int deleteByExample(TeacherInfoBeanExample example);

    int deleteByPrimaryKey(String tid);

    int insert(TeacherInfoBean record);

    int insertSelective(TeacherInfoBean record);

    List<TeacherInfoBean> selectByExample(TeacherInfoBeanExample example);

    TeacherInfoBean selectByPrimaryKey(String tid);

    int updateByExampleSelective(@Param("record") TeacherInfoBean record, @Param("example") TeacherInfoBeanExample example);

    int updateByExample(@Param("record") TeacherInfoBean record, @Param("example") TeacherInfoBeanExample example);

    int updateByPrimaryKeySelective(TeacherInfoBean record);

    int updateByPrimaryKey(TeacherInfoBean record);


   int deleteByPrimaryKeyCascadeTeach(String tid);
   int deleteByPrimaryKeyTeach_info(String tid);

    /**
     * 根据id实现批量删除
     * @param maps
     * @return
     */
    int deleteUserAllById(Map<String,List<String>> maps);


}
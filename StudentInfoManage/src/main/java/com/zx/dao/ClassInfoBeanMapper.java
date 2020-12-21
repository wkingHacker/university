package com.zx.dao;

import com.zx.bean.ClassInfoBean;
import com.zx.bean.ClassInfoBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassInfoBeanMapper {
    int countByExample(ClassInfoBeanExample example);

    int deleteByExample(ClassInfoBeanExample example);

    int deleteByPrimaryKey(String cid);

    int insert(ClassInfoBean record);

    int insertSelective(ClassInfoBean record);

    List<ClassInfoBean> selectByExample(ClassInfoBeanExample example);

    ClassInfoBean selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") ClassInfoBean record, @Param("example") ClassInfoBeanExample example);

    int updateByExample(@Param("record") ClassInfoBean record, @Param("example") ClassInfoBeanExample example);

    int updateByPrimaryKeySelective(ClassInfoBean record);

    int updateByPrimaryKey(ClassInfoBean record);
}
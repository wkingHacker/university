package com.zx.dao;

import com.zx.bean.TeachBeanExample;
import com.zx.bean.TeachBeanKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachBeanMapper {
    int countByExample(TeachBeanExample example);

    int deleteByExample(TeachBeanExample example);

    int deleteByPrimaryKey(String tid);

    int insert(TeachBeanKey record);

    int insertSelective(TeachBeanKey record);

    List<TeachBeanKey> selectByExample(TeachBeanExample example);

    TeachBeanKey selectByPrimaryKey(String tid);

    int updateByExampleSelective(@Param("record") TeachBeanKey record, @Param("example") TeachBeanExample example);

    int updateByExample(@Param("record") TeachBeanKey record, @Param("example") TeachBeanExample example);

    int updateByTeachBeanKey(TeachBeanKey teachBeanKey);
    int updateByPrimaryKey(TeachBeanKey record);

    int deleteByPrimaryKeyTeach_info(String tid);
}
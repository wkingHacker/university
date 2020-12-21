package com.zx.dao;

import com.zx.bean.RoleGroupBean;
import com.zx.bean.RoleGroupBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleGroupBeanMapper {
    int countByExample(RoleGroupBeanExample example);

    int deleteByExample(RoleGroupBeanExample example);

    int deleteByPrimaryKey(String groupid);

    int insert(RoleGroupBean record);

    int insertSelective(RoleGroupBean record);

    List<RoleGroupBean> selectByExample(RoleGroupBeanExample example);

    RoleGroupBean selectByPrimaryKey(String groupid);

    int updateByExampleSelective(@Param("record") RoleGroupBean record, @Param("example") RoleGroupBeanExample example);

    int updateByExample(@Param("record") RoleGroupBean record, @Param("example") RoleGroupBeanExample example);

    int updateByPrimaryKeySelective(RoleGroupBean record);

    int updateByPrimaryKey(RoleGroupBean record);
}
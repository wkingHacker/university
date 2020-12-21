package com.zx.dao;

import com.zx.bean.RoleMenuBeanExample;
import com.zx.bean.RoleMenuBeanKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuBeanMapper {
    int countByExample(RoleMenuBeanExample example);

    int deleteByExample(RoleMenuBeanExample example);

    int deleteByPrimaryKey(RoleMenuBeanKey key);

    int insert(RoleMenuBeanKey record);

    int insertSelective(RoleMenuBeanKey record);

    List<RoleMenuBeanKey> selectByExample(RoleMenuBeanExample example);

    int updateByExampleSelective(@Param("record") RoleMenuBeanKey record, @Param("example") RoleMenuBeanExample example);

    int updateByExample(@Param("record") RoleMenuBeanKey record, @Param("example") RoleMenuBeanExample example);
}
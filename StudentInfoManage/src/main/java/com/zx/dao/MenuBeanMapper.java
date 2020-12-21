package com.zx.dao;

import com.zx.bean.MenuBean;
import com.zx.bean.MenuBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuBeanMapper {
    int countByExample(MenuBeanExample example);

    int deleteByExample(MenuBeanExample example);

    int deleteByPrimaryKey(String menuid);

    int insert(MenuBean record);

    int insertSelective(MenuBean record);

    List<MenuBean> selectByExample(MenuBeanExample example);

    MenuBean selectByPrimaryKey(String menuid);

    int updateByExampleSelective(@Param("record") MenuBean record, @Param("example") MenuBeanExample example);

    int updateByExample(@Param("record") MenuBean record, @Param("example") MenuBeanExample example);

    int updateByPrimaryKeySelective(MenuBean record);

    int updateByPrimaryKey(MenuBean record);

    List<MenuBean> getLoginUserMenu(String operatorId);

    List<MenuBean> selectMenuByGroupId(String groupid);
}
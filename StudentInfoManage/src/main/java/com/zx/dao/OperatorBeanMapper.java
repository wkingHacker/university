package com.zx.dao;

import com.zx.bean.OperatorBean;
import com.zx.bean.OperatorBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OperatorBeanMapper {
    int countByExample(OperatorBeanExample example);

    int deleteByExample(OperatorBeanExample example);

    int deleteByPrimaryKey(String loginid);

    int insert(OperatorBean record);

    int insertSelective(OperatorBean record);

    List<OperatorBean> selectByExample(OperatorBeanExample example);

    OperatorBean selectByPrimaryKey(String loginid);

    int updateByExampleSelective(@Param("record") OperatorBean record, @Param("example") OperatorBeanExample example);

    int updateByExample(@Param("record") OperatorBean record, @Param("example") OperatorBeanExample example);

    int updateByPrimaryKeySelective(OperatorBean record);

    int updateByPrimaryKey(OperatorBean record);


    OperatorBean getloginid(String loginname);
}
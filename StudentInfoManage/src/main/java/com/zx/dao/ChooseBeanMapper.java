package com.zx.dao;

import com.zx.bean.ChooseBeanExample;
import com.zx.bean.ChooseBeanKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChooseBeanMapper {
    int countByExample(ChooseBeanExample example);

    int deleteByExample(ChooseBeanExample example);

    int deleteByPrimaryKey(ChooseBeanKey key);

    int insert(ChooseBeanKey record);

    int insertSelective(ChooseBeanKey record);

    List<ChooseBeanKey> selectByExample(ChooseBeanExample example);

    int updateByExampleSelective(@Param("record") ChooseBeanKey record, @Param("example") ChooseBeanExample example);

    int updateByExample(@Param("record") ChooseBeanKey record, @Param("example") ChooseBeanExample example);
}
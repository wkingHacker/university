package com.zx.service;




import com.zx.bean.MenuBean;
import com.zx.bean.OperatorBean;


import java.util.List;


public interface OperatorService {

    int deleteByPrimaryKey(String operatorid);
    int insert(OperatorBean record);
    List<OperatorBean> selectByExample(OperatorBean operatorBean);
    OperatorBean selectByPrimaryKey(String operatorid);
    int update( OperatorBean record);


}

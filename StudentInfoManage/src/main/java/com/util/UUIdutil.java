package com.util;

import java.util.UUID;

/** 

* @author ���� Wking: 

* @version ����ʱ�䣺2019��9��22�� ����8:24:48 



*/
public  class UUIdutil {
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		return id.replace("-","");
		
	}
}

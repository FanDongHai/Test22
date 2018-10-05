package com.xx.utils;

import com.xx.vo.JsonBean;

public class JsonBeanUtil {
	
	// 接收传入数据工具与注解一起用
	public static JsonBean shiFtJsonBean(int code, Object msgs) {
		JsonBean jsonBean = new JsonBean();
		
		jsonBean.setCode(code);
		jsonBean.setMsgs(msgs);
		
		return jsonBean;
	}
}

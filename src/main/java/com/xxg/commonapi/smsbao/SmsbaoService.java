package com.xxg.commonapi.smsbao;

import java.io.IOException;
import java.net.URLEncoder;

import com.xxg.commonapi.util.HttpUtil;

public class SmsbaoService {
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码MD5，不区分大小写
	 */
	private String passwordMd5;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordMd5(String passwordMd5) {
		this.passwordMd5 = passwordMd5;
	}

	/**
	 * 发送短信
	 * @param phone 手机号码
	 * @param message 短信消息内容，格式要求：【公司签名】短信内容，如：【叉叉网】您的短信验证码是123456。
	 * @return 是否发送成功
	 * @throws IOException 
	 */
	public boolean send(String phone, String message) throws IOException {
		String url = "http://api.smsbao.com/sms?u=";
		url += username;
		url += "&p=";
		url += passwordMd5;
		url += "&m=";
		url += URLEncoder.encode(phone, "UTF-8");
		url += "&c=";
		url += URLEncoder.encode(message, "UTF-8");
		String response = HttpUtil.get(url);
		if("0".equals(response)) {
			return true;
		} else {
			return false;
		}
	}

}

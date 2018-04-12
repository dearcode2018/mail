/**
* MyAuthenticator.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.entity;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 描述: 
 * @author qye.zheng
 * MyAuthenticator
 */
public class AuthenticatorEntity extends Authenticator {
	
	/* 登录邮件发送服务器的用户名 */
	private String username;
	
	/* 登录邮件发送服务器的密码 */
	private String password;
	
	/** 无参构造方法 */
	public AuthenticatorEntity() {}

	/** 有参构造方法 */
	public AuthenticatorEntity(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @return
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		
		return new PasswordAuthentication(username, password);
	}
	
}

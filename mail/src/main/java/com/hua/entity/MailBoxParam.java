/**
 * MailBoxParam.java
 * @author  qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.entity;

import java.util.Properties;

import com.hua.constant.MailConstant;

/**
 * MailBoxParam
 * 描述: 邮箱参数
 * @author  qye.zheng
 * 
 */
public final class MailBoxParam
{
	/* 邮件使用的协议 (example : smtp) */
	private String protocol;

	/* 邮件发送 服务器 地址 */
	private String host;

	/* 邮件发送 服务器 端口 */
	private int port;

	/* 是否需要身份验证 */
	private boolean auth = true;
	
	/* 邮件发送者的地址 */
	private String fromAddress;

	/* 登录邮件发送服务器的用户名 */
	private String username;

	/* 登录邮件发送服务器的密码 */
	private String password;

	/* 邮件会话属性 - 设置邮箱各个参数 */
	private Properties props;

	/**
	 * @return the protocol
	 */
	public String getProtocol()
	{
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	/**
	 * @return the host
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host)
	{
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * @return the auth
	 */
	public boolean isAuth()
	{
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(boolean auth)
	{
		this.auth = auth;
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress()
	{
		return fromAddress;
	}

	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress)
	{
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the props
	 */
	public Properties getProps()
	{
		return props;
	}
	
	/**
	 * @param props the props to set
	 */
	public void setProps(Properties props)
	{
		this.props = props;
		// 初始化属性
		this.protocol = props.getProperty(MailConstant.MAIL_TRANSPORT_PROTOCOL);
		this.host = props.getProperty(MailConstant.MAIL_SMTP_HOST);
		this.port = Integer.parseInt(props.getProperty(MailConstant.MAIL_SMTP_PORT));
		this.auth = Boolean.valueOf(props.getProperty(MailConstant.MAIL_SMTP_AUTH));
		this.fromAddress = props.getProperty(MailConstant.MAIL_SEND_FROM_ADDRESS);
		this.username = props.getProperty(MailConstant.MAIL_SEND_USERNAME);
		this.password = props.getProperty(MailConstant.MAIL_SEND_PASSWORD);
	}
	
}

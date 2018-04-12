/**
 * 描述: 
 * MailSender.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.hua.constant.MailConstant;

/**
 * 描述: 邮件实体
 * 
 * 
 * @author qye.zheng MailSender
 */
public final class MailEntity
{

	/* 文本格式的邮件 */
	public static final int MAIL_TYPE_TEXT = 0;

	/* html格式的邮件 */
	public static final int MAIL_TYPE_HTML = 1;

	public static final String split = ",";

	/* 邮件发送 服务器 地址 */
	private String host;

	/* 邮件发送 服务器 端口 */
	private String port;

	/* 邮件使用的协议 (example : smtp) */
	private String protocol;

	/* 邮件发送者的地址 */
	private String fromAddress;

	/* 登录邮件发送服务器的用户名 */
	private String username;

	/* 登录邮件发送服务器的密码 */
	private String password;

	/* 是否需要身份验证 */
	private boolean auth = true;

	/* 邮件主题 */
	private String subject;

	/* 邮件内容 - 文本/html */
	private String content;

	/* 邮件会话属性 */
	private Properties props;
	
	/* 邮件[接收者]的地址 */
	private Set<String> toAddress = new HashSet<String>(0);
	
	/* 邮件[抄送者]的地址 */
	private Set<String> carbonCopyAddress = new HashSet<String>(0);
	
	/* 邮件[密送者]的地址 */
	private Set<String> blindCarbonCopyAddress = new HashSet<String>(0);
	
	/* 邮件附件的文件名 */
	private Set<String> attachFileName = new HashSet<String>(0);
	
	/** 无参构造方法 */
	public MailEntity()
	{
	}

	/**
	 * @return the host
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host)
	{
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public String getPort()
	{
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(String port)
	{
		this.port = port;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol()
	{
		return protocol;
	}

	/**
	 * @param protocol
	 *            the protocol to set
	 */
	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress()
	{
		return fromAddress;
	}

	/**
	 * @param fromAddress
	 *            the fromAddress to set
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
	 * @param username
	 *            the username to set
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the auth
	 */
	public boolean isAuth()
	{
		return auth;
	}

	/**
	 * @param auth
	 *            the auth to set
	 */
	public void setAuth(boolean auth)
	{
		this.auth = auth;
	}

	/**
	 * @return the subject
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	
	/**
	 * @return the props
	 */
	public Properties getProps()
	{
		return props;
	}

	/**
	 * @param props
	 *            the props to set
	 */
	public void setProps(final Properties props)
	{
		this.props = props;
		// 初始化属性
		this.protocol = props.getProperty(MailConstant.MAIL_TRANSPORT_PROTOCOL);
		this.host = props.getProperty(MailConstant.MAIL_SMTP_HOST);
		this.port = props.getProperty(MailConstant.MAIL_SMTP_PORT);
		this.auth = Boolean.valueOf(props.getProperty(MailConstant.MAIL_SMTP_AUTH));
		this.username = props.getProperty(MailConstant.MAIL_SEND_USERNAME);
		this.password = props.getProperty(MailConstant.MAIL_SEND_PASSWORD);
		this.fromAddress = props.getProperty(MailConstant.MAIL_SEND_FROM_ADDRESS);
	}

	/**
	 * @return the toAddress
	 */
	public Set<String> getToAddress()
	{
		return toAddress;
	}

	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(Set<String> toAddress)
	{
		this.toAddress = toAddress;
	}

	/**
	 * @return the carbonCopyAddress
	 */
	public Set<String> getCarbonCopyAddress()
	{
		return carbonCopyAddress;
	}

	/**
	 * @param carbonCopyAddress the carbonCopyAddress to set
	 */
	public void setCarbonCopyAddress(Set<String> carbonCopyAddress)
	{
		this.carbonCopyAddress = carbonCopyAddress;
	}

	/**
	 * @return the blindCarbonCopyAddress
	 */
	public Set<String> getBlindCarbonCopyAddress()
	{
		return blindCarbonCopyAddress;
	}

	/**
	 * @param blindCarbonCopyAddress the blindCarbonCopyAddress to set
	 */
	public void setBlindCarbonCopyAddress(Set<String> blindCarbonCopyAddress)
	{
		this.blindCarbonCopyAddress = blindCarbonCopyAddress;
	}

	/**
	 * @return the attachFileName
	 */
	public Set<String> getAttachFileName()
	{
		return attachFileName;
	}

	/**
	 * @param attachFileName the attachFileName to set
	 */
	public void setAttachFileName(Set<String> attachFileName)
	{
		this.attachFileName = attachFileName;
	}

	/**
	 * 
	 * 描述: 添加 单个发送者地址
	 * @author qye.zheng
	 * 
	 * @param toAddress
	 */
	public void addToAddress(final String toAddress) 
	{
		this.toAddress.add(toAddress);
	}
	
	/**
	 * 
	 * 描述: 添加 单个抄送者地址
	 * @author qye.zheng
	 * 
	 * @param toAddress
	 */
	public void addCarbonCopyAddress(final String carbonCopyAddress) 
	{
		this.carbonCopyAddress.add(carbonCopyAddress);
	}
	
	/**
	 * 
	 * 描述: 添加 单个密送者地址
	 * @author qye.zheng
	 * 
	 * @param toAddress
	 */
	public void addBlindCarbonCopyAddress(final String blindCarbonCopyAddress) 
	{
		this.blindCarbonCopyAddress.add(blindCarbonCopyAddress);
	}
	
	/**
	 * 
	 * 描述: 添加 单个附件文件名
	 * @author qye.zheng
	 * 
	 * @param toAddress
	 */
	public void addAttachFileName(final String attachFileName) 
	{
		this.attachFileName.add(attachFileName);
	}

}

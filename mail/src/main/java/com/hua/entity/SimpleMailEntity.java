/**
 * SimpleMailEntity.java
 * 
 * @author qye.zheng
 * 	version 1.0
 */
package com.hua.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述: 简单邮件实体
 * 
 * @author qye.zheng SimpleMailEntity
 * 
 */
public final class SimpleMailEntity
{
	/* 邮件主题 */
	private String subject;

	/* 邮件内容 */
	private String content;
	
	/* 邮件格式 : 默认是 html格式 */
	private int mailType = MailEntity.MAIL_TYPE_HTML;

	/* 邮件[接收者]的地址 */
	private Set<String> toAddress = new HashSet<String>(0);
	
	/* 邮件[抄送者]的地址 */
	private Set<String> carbonCopyAddress = new HashSet<String>(0);
	
	/* 邮件[密送者]的地址 */
	private Set<String> blindCarbonCopyAddress = new HashSet<String>(0);
	
	/* 邮件附件的文件名 */
	private Set<String> attachFileName = new HashSet<String>(0);
	
	/** 无参构造方法 */
	public SimpleMailEntity()
	{
	}

	/**
	 * @return the subject
	 */
	public String getSubject()
	{
		
		return subject;
	}


	/**
	 * @param subject the subject to set
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
	 * @param content the content to set
	 */
	public void setContent(String content)
	{
		this.content = content;
	}


	/**
	 * @return the mailType
	 */
	public int getMailType()
	{
		return mailType;
	}


	/**
	 * @param mailType the mailType to set
	 */
	public void setMailType(int mailType)
	{
		this.mailType = mailType;
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

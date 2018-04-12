/**
 * 描述: 
 * MailSendEntity.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import java.util.HashSet;
import java.util.Set;

import com.hua.constant.MailConstant;

/**
 * 描述: 邮件发送实体
 * 
 * 
 * @author qye.zheng 
 * MailSendEntity
 */
public final class MailSendEntity
{
	/* 邮件格式 : 默认是 html格式 */
	private int mailType = MailConstant.MAIL_TYPE_HTML;
	
	/* 邮件主题 */
	private String subject;

	/* 邮件内容 - 文本/html */
	private String content;
	
	/* 邮件附件完整路径 */
	private Set<String> attachments = new HashSet<String>(0);
	
	/* 邮件[接收者]的地址 */
	private Set<String> toAddresses = new HashSet<String>(0);
	
	/* 邮件[抄送者]的地址 */
	private Set<String> carbonCopyAddresses = new HashSet<String>(0);
	
	/* 邮件[密送者]的地址 */
	private Set<String> blindCarbonCopyAddresses = new HashSet<String>(0);
	
	/** 无参构造方法 */
	public MailSendEntity()
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
	 * 
	 * 描述: 添加附件 (附件完整路径)
	 * @author  qye.zheng
	 * 
	 * @param attachment
	 * @return
	 */
	public boolean addAttachment(final String attachment)
	{
		return attachments.add(attachment);
	}

	/**
	 * 
	 * 描述: 添加接收者
	 * @author  qye.zheng
	 * 
	 * @param toAddress
	 * @return
	 */
	public boolean addToAddress(final String toAddress)
	{
		return toAddresses.add(toAddress);
	}
	
	/**
	 * 
	 * 描述: 添加抄送者
	 * @author  qye.zheng
	 * 
	 * @param toAddress
	 * @return
	 */
	public boolean addCarbonCopyAddress(final String carbonCopyAddress)
	{
		return carbonCopyAddresses.add(carbonCopyAddress);
	}
	
	/**
	 * 
	 * 描述: 添加密送者
	 * @author  qye.zheng
	 * 
	 * @param toAddress
	 * @return
	 */
	public boolean addBlindCarbonCopyAddress(final String blindCarbonCopyAddress)
	{
		return blindCarbonCopyAddresses.add(blindCarbonCopyAddress);
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
	 * @return the attachments
	 */
	public Set<String> getAttachments()
	{
		return attachments;
	}

	/**
	 * @return the toAddresses
	 */
	public Set<String> getToAddresses()
	{
		return toAddresses;
	}

	/**
	 * @return the carbonCopyAddresses
	 */
	public Set<String> getCarbonCopyAddresses()
	{
		return carbonCopyAddresses;
	}

	/**
	 * @return the blindCarbonCopyAddresses
	 */
	public Set<String> getBlindCarbonCopyAddresses()
	{
		return blindCarbonCopyAddresses;
	}
	
	
}

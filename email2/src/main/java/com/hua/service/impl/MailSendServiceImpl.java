/**
 * MailSendServiceImpl.java
 * @author  qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.service.impl;

import java.util.Properties;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.hua.entity.MailBoxParam;
import com.hua.entity.MailSendEntity;
import com.hua.service.MailSendService;
import com.hua.util.ReadProperties;

/**
 * MailSendServiceImpl
 * 描述: 
 * @author  qye.zheng
 * 
 */
public class MailSendServiceImpl implements MailSendService
{

	private static final String CONFIG_PATH = "/conf/properties/mail.properties";

	private static final ReadProperties readProps = new ReadProperties(CONFIG_PATH);

	/* 共用 : 单例，没有特殊要求 直接使用该实例 */
	private static MailBoxParam mailBoxParam;

	// 加载邮件会话共用属性
	static
	{
		mailBoxParam = new MailBoxParam();
		final Properties props = readProps.getProps();
		mailBoxParam.setProps(props);
	}
	
	/**
	 * 描述: 发送邮件 
	 * @author  qye.zheng
	 * 
	 * @param mailSendEntity
	 * @return
	 */
	@Override
	public boolean sendMail(final MailSendEntity mailSendEntity)
	{
		boolean flag = false;
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 发送文本邮件
	 * @author  qye.zheng
	 * 
	 * @param mailSendEntity
	 * @return
	 */
	private boolean sendTextMail(final MailSendEntity mailSendEntity)
	{
		boolean flag = false;
		final Email email = new SimpleEmail();
		email.setHostName(mailBoxParam.getHost());
		email.setSmtpPort(mailBoxParam.getPort());
		email.setSSLOnConnect(true);
		try
		{
			email.setFrom(mailBoxParam.getFromAddress(), "test");
			email.setSubject(mailSendEntity.getSubject());
			email.setMsg(mailSendEntity.getContent());
			
			// 发送者
			final String[] toAddressArr = new String[mailSendEntity.getToAddresses().size()];
			mailSendEntity.getToAddresses().toArray(toAddressArr);
			email.addTo(toAddressArr);
			
			// 抄送这
			final String[] carbonCopyAddressArr = new String[mailSendEntity.getCarbonCopyAddresses().size()];
			mailSendEntity.getCarbonCopyAddresses().toArray(carbonCopyAddressArr);
			email.addCc(carbonCopyAddressArr);
			
			// 密送者
			final String[] blindCarbonCopyAddressArr = new String[mailSendEntity.getBlindCarbonCopyAddresses().size()];
			mailSendEntity.getBlindCarbonCopyAddresses().toArray(blindCarbonCopyAddressArr);
			email.addBcc(blindCarbonCopyAddressArr);
			
			// 发送邮件
			email.send();
			
			
			
			
		} catch (EmailException e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * 
	 * 描述: 发送html邮件
	 * @author  qye.zheng
	 * 
	 * @param mailSendEntity
	 * @return
	 */
	private boolean sendHtmlMail(final MailSendEntity mailSendEntity)
	{
		boolean flag = false;
		
		
		return flag;
	}
}

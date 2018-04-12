/**
 * MailSendServiceImpl.java
 * @author  qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.service.impl;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.hua.constant.MailConstant;
import com.hua.entity.AuthenticatorEntity;
import com.hua.entity.MailBoxParam;
import com.hua.entity.MailSendEntity;
import com.hua.service.MailSendService;
import com.hua.util.DateTimeUtil;
import com.hua.util.JMailUtil;
import com.hua.util.ReadProperties;

/**
 * MailSendServiceImpl
 * 描述: 邮件发送服务
 * @author  qye.zheng
 * 
 */
public final class MailSendServiceImpl extends CoreServiceImpl implements
		MailSendService
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
	 * 
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
		AuthenticatorEntity authenticator = null;
		final Properties props = mailBoxParam.getProps();
		final String username = mailBoxParam.getUsername();
		final String password = mailBoxParam.getPassword();
		final String fromAddress = mailBoxParam.getFromAddress();

		if (mailBoxParam.isAuth())
		{
			// 需要验证
			authenticator = new AuthenticatorEntity(username, password);
		} else 
		{
			log.warn("sendMail =====>无需验证, authenticator 为空 ");
			// 无需验证 authenticator 为空
		}
		// 根据邮件会话属性和密码验证器构建一个发送邮件的session
		final Session session = Session.getDefaultInstance(props, authenticator);
		
        // TODO 可以在控制台（console)上看到发送邮件的过程 (非测试 关闭此功能)
        //session.setDebug(true);
		try
		{
			// 根据 session 创建一个邮件消息
			final Message message = new MimeMessage(session);
			// 创建邮件发送者地址
			final Address from = new InternetAddress(fromAddress);
			// 设置邮件发送者地址
			message.setFrom(from);

			// 邮件接收者地址
			final Address[] toAddressArr = JMailUtil.getAddressArr(mailSendEntity.getToAddresses());
			// 邮件抄送者地址
			final Address[] carbonCopyAddressArr = JMailUtil.getAddressArr(mailSendEntity.getCarbonCopyAddresses());
			// 邮件密送者地址
			final Address[] blindCarbonCopyAddressArr = JMailUtil.getAddressArr(mailSendEntity.getBlindCarbonCopyAddresses());

			// 设置邮件接收者地址
			message.setRecipients(Message.RecipientType.TO, toAddressArr);
			// 设置邮件抄送者地址
			message.setRecipients(Message.RecipientType.CC, carbonCopyAddressArr);
			// 设置邮件密送者地址
			message.setRecipients(Message.RecipientType.BCC, blindCarbonCopyAddressArr);

			// 设置邮件消息的主题
			message.setSubject(mailSendEntity.getSubject());
			// 设置邮件消息的发送时间
			message.setSentDate(DateTimeUtil.getDateTime());
			
			if (MailConstant.MAIL_TYPE_HTML == mailSendEntity.getMailType())
			{
				log.info("sendMail =====> html");
				// html
				// Multipart (包括正文、附件)
				final Multipart multipart = new MimeMultipart();
				// BodyPart
				final BodyPart bodyPart = new MimeBodyPart();
				// 设置html内容
				bodyPart.setContent(mailSendEntity.getContent(), MailConstant.CONTENT_TYPE);
				// 将 bodyPart 加入 multipart
				multipart.addBodyPart(bodyPart);
				// 设置邮件消息的主要内容
				message.setContent(multipart);
				
				// 附件处理器
				JMailUtil.attachmentHandler(multipart, mailSendEntity.getAttachments());
				
			} else if (MailConstant.MAIL_TYPE_TEXT == mailSendEntity.getMailType())
			{
				log.info("sendMail =====> text");
				// text
				// 设置邮件消息的主要内容
				message.setText(mailSendEntity.getContent());
			}
			
			log.info("sendMail =====> begin 发送邮件");
			// 发送邮件
			Transport.send(message);
			log.info("sendMail =====> end 发送邮件");
			flag = true;
			
			return flag;
		} catch (Exception e)
		{
			log.error("sendMail =====> 发送邮件失败", e);
		}

		return flag;
	}
	
	

}

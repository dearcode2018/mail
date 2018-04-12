/**
 * MailServiceImpl.java
 * 
 * @author qye.zheng
 * 	version 1.0
 */
package com.hua.service.impl;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hua.entity.MailEntity;
import com.hua.entity.SimpleMailEntity;
import com.hua.service.MailService;
import com.hua.util.ReadProperties;

/**
 * 描述: 邮件服务
 * 
 * @author qye.zheng MailServiceImpl
 * 
 */
public final class MailServiceImpl implements MailService
{

	protected Log log = LogFactory.getLog(this.getClass().getName());

	private static final String CONFIG_PATH = "/conf/properties/project.properties";

	private static final ReadProperties readProps = new ReadProperties(CONFIG_PATH);

	/* 共用 : 单例，没有特殊要求 直接使用该实例 */
	private static MailEntity mailEntity = new MailEntity();

	// 加载邮件会话共用属性
	static
	{
		mailEntity = new MailEntity();
		final Properties props = readProps.getProps();
		mailEntity.setProps(props);
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 * @param simpleMailEntity
	 * @return
	 */
	@Override
	public boolean sendMail(SimpleMailEntity simpleMailEntity)
	{
		return false;
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 * @param mailEntity
	 * @return
	 */
	@Override
	public boolean sendTextMail(MailEntity mailEntity)
	{
		return false;
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 * @param mailEntity
	 * @return
	 */
	@Override
	public boolean sendHtmlMail(MailEntity mailEntity)
	{
		return false;
	}



}

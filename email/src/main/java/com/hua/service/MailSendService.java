/**
 * MailSendService.java
 * @author  qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.service;

import com.hua.entity.MailSendEntity;

/**
 * MailSendService
 * 描述: 邮件发送服务
 * @author  qye.zheng
 * 
 */
public interface MailSendService extends CoreService
{

	/**
	 * 
	 * 描述: 发送邮件 
	 * @author  qye.zheng
	 * 
	 * @param mailSendEntity
	 * @return
	 */
	public boolean sendMail(final MailSendEntity mailSendEntity);
}

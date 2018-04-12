/**
 * MailSender.java
 * 
 * @author qye.zheng
 * 	version 1.0
 */
package com.hua.service;

import com.hua.entity.MailEntity;
import com.hua.entity.SimpleMailEntity;

/**
 * 描述: 
 * @author qye.zheng
 * MailSender
 * 
 */
public interface MailService extends CoreService {
	
	/**
	 * 
	 * 描述:  发送邮件
	 * 没有特殊要求，直接调用此方法即可
	 * 
	 * @author qye.zheng
	 * @param simpleMailEntity
	 * @return
	 */
	public boolean sendMail(final SimpleMailEntity simpleMailEntity);
	
	/**
	 * 
	 * 描述: 以文本格式发送邮件 
	 * 
	 * @author qye.zheng
	 * @param mailEntity
	 * @return
	 */
	public boolean sendTextMail(final MailEntity mailEntity);
	
	/**
	 * 
	 * 描述: 以Html格式发送邮件 
	 * 
	 * @author qye.zheng
	 * @param mailEntity
	 * @return
	 */
	public boolean sendHtmlMail(final MailEntity mailEntity);
}

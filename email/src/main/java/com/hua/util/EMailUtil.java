/**
 * EMailUtil.java
 * @author  qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import com.hua.constant.Constant;

/**
 * EMailUtil
 * 描述: 
 * @author  qye.zheng
 * 
 */
public final class EMailUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 */
	private EMailUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 附件处理器
	 * @author  qye.zheng
	 * 
	 * @param attachments
	 * @param multiPartEmail
	 */
	public static void attachmentHandler(final Set<String> attachments, final MultiPartEmail multiPartEmail)
	{
		if (!attachments.isEmpty())
		{
			final Iterator<String> it = attachments.iterator();
			EmailAttachment attachment = null;
			String filename = null;
			while (it.hasNext())
			{
				attachment = new EmailAttachment();
				filename = it.next();
				// 设置附件完整路径
				attachment.setPath(filename);
				// 设置附件描述信息
				attachment.setDescription("description ...");
				// 设置文件名 - 路径最后一个分隔符之后的字符
				attachment.setName(filename.substring(filename.lastIndexOf(Constant.SLASH) + 1));
				try
				{
					// 加入复合
					multiPartEmail.attach(attachment);
				} catch (EmailException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

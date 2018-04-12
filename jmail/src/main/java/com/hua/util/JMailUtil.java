/**
 * JMailUtil.java
 * @author  qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

import java.util.Iterator;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;

import com.hua.constant.Constant;

/**
 * JMailUtil
 * 描述: 
 * @author  qye.zheng
 * 
 */
public final class JMailUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 */
	private JMailUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 获取地址数组
	 * @author  qye.zheng
	 * 
	 * @return
	 */
	public static Address[] getAddressArr(final Set<String> addresses)
	{
		final Address[] addArr = new Address[addresses.size()];
		try
		{
			if (!addresses.isEmpty())
			{
				int i = 0;
				final Iterator<String> it = addresses.iterator();
				while (it.hasNext())
				{
					addArr[i] = new InternetAddress(it.next());
					i++;
				}
			}
		} catch (AddressException e)
		{
			e.printStackTrace();
		}
		
		return addArr;
	}
	
	/**
	 * 
	 * 描述: 附件处理器
	 * @author  qye.zheng
	 * 
	 * @param multipart
	 * @param attachments
	 */
	public static void attachmentHandler(final Multipart multipart, final Set<String> attachments) 
	{
		try
		{
			BodyPart bodyPart = null;
			FileDataSource fileDataSource = null;
			if (!attachments.isEmpty())
			{
				final Iterator<String> it = attachments.iterator();
				while (it.hasNext())
				{
					bodyPart = new MimeBodyPart();
					
					// 通过完整的文件路径，构造文件数据源
					fileDataSource = new FileDataSource(it.next());
					// 设置数据处理器
					bodyPart.setDataHandler(new DataHandler(fileDataSource));
					// 设置文件名 编码 (从文件数据源中获取文件名)
					bodyPart.setFileName(MimeUtility.encodeText(fileDataSource.getName(), Constant.CHART_SET_GB2312, "B"));
					
					// 加入 Multipart
					multipart.addBodyPart(bodyPart);
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

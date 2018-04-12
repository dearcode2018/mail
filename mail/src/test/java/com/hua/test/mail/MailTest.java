/**
 * 描述: 
 * MailTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.mail;

import java.util.Properties;

import org.junit.Test;

import com.hua.entity.MailEntity;
import com.hua.service.MailService;
import com.hua.service.impl.MailServiceImpl;
import com.hua.test.BaseTest;
import com.hua.util.ReadProperties;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * MailTest
 */
public final class MailTest extends BaseTest {
	
	private static final String CONFIG_PATH = "/conf/properties/project.properties";
	
	private static final ReadProperties readProps = new ReadProperties(CONFIG_PATH);
	
	private MailService mailService = new MailServiceImpl();
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSend() {
		try
		{
			final MailEntity mailEntity = new MailEntity();
			final Properties props = readProps.getProps();
			mailEntity.setProps(props);
			mailEntity.setSubject("邮件主题 : 这是我的测试邮件主题");
			mailEntity.setContent("邮件内容 : 现在是 : 2014年4月10日15:19:50");
			mailEntity.addToAddress("pinzhong@126.com");
			// 发送邮件
			mailService.sendHtmlMail(mailEntity);
		} catch (Exception e)
		{
			log.error("testSend =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		
	}
}

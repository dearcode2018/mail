/**
 * 描述: 
 * JMailTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.mail;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.entity.AuthenticatorEntity;
import com.hua.entity.MailBoxParam;
import com.hua.test.BaseTest;
import com.hua.util.ReadProperties;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JMailTest
 */
public final class JMailTest extends BaseTest {

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
	 * 描述: 读取邮件
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadMail() {
		try {
			final String username = "dearzqy";
			final String password = "xzy123";
			final String fromAddress = "dearzqy@163.com";
			AuthenticatorEntity authenticator = null;			
			// 需要验证
			authenticator = new AuthenticatorEntity(username, password);
			Properties props = new Properties();
			//props.put("mail.transport.protocol", "smtp");
			//props.put("mail.smtp.host", "smtp.163.com");
			//props.put("mail.smtp.port", "25");
			// 协议
			props.setProperty("mail.store.protocol", "pop3");
			// 端口
			props.setProperty("mail.pop3.port", "110");
			// pop3服务器
			props.setProperty("mail.pop3.host", "pop3.163.com");
			
			// 根据邮件会话属性和密码验证器构建一个发送邮件的session
			//final Session session = Session.getDefaultInstance(props, authenticator);
			final Session session = Session.getDefaultInstance(props);
			Store store = session.getStore("pop3");
			 store.connect(username, password);  
			//Store store = session.getStore();
			
			// 获得收件箱
			Folder folder = store.getFolder("INBOX");
			/* Folder.READ_ONLY：只读权限 
	         * Folder.READ_WRITE：可读可写（可以修改邮件的状态） 
	         */  
			// 打开收件箱
			folder.open(Folder.READ_WRITE);
			// 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数  
			log.info("testReadMail =====> " + folder.getUnreadMessageCount());
			Message message = null;
			// 从1开始，最早的邮件是1
			message = folder.getMessage(79);
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			log.info("testReadMail =====> " + message.getSubject());
			// 获取邮件内容，0是标题对象，1-是内容对象
			BodyPart bodyPart = mimeMultipart.getBodyPart(1);
			String html = bodyPart.getContent().toString();
			log.info("testReadMail =====> " + html);
			Integer length = 10;
			String str = "密码: ";
			int startIndex = html.indexOf(str) + str.length();
			String target = html.substring(startIndex, startIndex + length);
			log.info("testReadMail =====> target = " + target);
		} catch (Exception e) {
			log.error("testReadMail =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSendTextMail() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSendTextMail =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSendHtmlMail() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSendHtmlMail =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			Set<String> data = new HashSet<String>();
			data.add("a");
			data.add("b");
			// 错误
			//String[] arr = (String[]) data.toArray();
			//
			String[] arr = new String[data.size()];
			data.toArray(arr);
			
			log.info("testTemp =====> " + arr[1]);
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
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
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 普通测试方法
	 ,@Test注解 不带参数
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNormal() {
		System.out.println("testNormal()");
	}
	
	/**
	 * 
	 * 描述: 期望发生异常-测试方法
	 ,@Test注解 异常
	 * @author qye.zheng
	 * 
	 */
	@Test(expected=NullPointerException.class)
	@SuppressWarnings("all")
	public void testException() {
		String str = null;
		System.out.println(str.length());
	}
	
	/**
	 * 
	 * 描述: 超时测试方法
	 ,@Test注解 指定运行时间 (单位 : 毫秒)
	 * @author qye.zheng
	 * 
	 */
	@Test(timeout=3000)
	public void testTimeOut() {
		System.out.println("testTimeOut()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Ignore("暂时忽略的方法")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@BeforeClass
	public static void beforeClass() {
		System.out.println("beforeClass()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println("afterClass()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@Before
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@After
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}

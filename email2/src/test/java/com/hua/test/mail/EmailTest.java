/**
 * 描述: 
 * EmailTest.java
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

import java.io.File;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceFileResolver;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.entity.MailBoxParam;
import com.hua.entity.MailSendEntity;
import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;
import com.hua.util.EMailUtil;
import com.hua.util.ReadProperties;
import com.hua.util.StringUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * EmailTest
 */
public final class EmailTest extends BaseTest {
	
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
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSendMail() {
		try {
			
			
			
		} catch (Exception e) {
			log.error("testSendMail =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 发送纯文本邮件
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSendTextMail() {
		try {
			String address = "dearzqy2010@qq.com";
			//
			final MailSendEntity mailSendEntity = new MailSendEntity();
			mailSendEntity.setSubject("邮件主题 : 这是我的测试邮件主题");
			// 纯文本格式无法实现html效果
			//mailSendEntity.setContent("html格式邮件内容 : 现在的时间是<h2> : " + new Date() + "</h2>");
			mailSendEntity.setContent("邮件内容 : 现在的时间是 : " + new Date());
			
			mailSendEntity.addToAddress(address);
			mailSendEntity.addCarbonCopyAddress(address);
			mailSendEntity.addBlindCarbonCopyAddress(address);
			
			// 简单文本邮件
			final Email email = new SimpleEmail();
			email.setHostName(mailBoxParam.getHost());
			email.setSmtpPort(mailBoxParam.getPort());
			// 设置验证
			email.setAuthenticator(new DefaultAuthenticator(mailBoxParam.getUsername(), mailBoxParam.getPassword()));
			email.setSSLOnConnect(true);
			
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
			
		} catch (Exception e) {
			log.error("testSendTextMail =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 发送 复合 带附件的邮件
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWithAttachment() {
		try {
			String address = "dearzqy2010@qq.com";
			//
			final MailSendEntity mailSendEntity = new MailSendEntity();
			mailSendEntity.setSubject("邮件主题 : 这是我的测试邮件主题");
			mailSendEntity.setContent("邮件内容 : 现在的时间是 : " + new Date());
			mailSendEntity.addToAddress(address);
			mailSendEntity.addCarbonCopyAddress(address);
			mailSendEntity.addBlindCarbonCopyAddress(address);
			
			// 添加附件 TODO 测试之前应该检查一下附件是否存在
			mailSendEntity.addAttachment(ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true));
			mailSendEntity.addAttachment(ClassPathUtil.getClassSubpath("/file/img/抱树小狗_01.jpg", true));
			
			// 复合邮件
			final MultiPartEmail email = new MultiPartEmail();
			email.setHostName(mailBoxParam.getHost());
			email.setSmtpPort(mailBoxParam.getPort());
			// 设置验证
			email.setAuthenticator(new DefaultAuthenticator(mailBoxParam.getUsername(), mailBoxParam.getPassword()));
			email.setSSLOnConnect(true);
			
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
			
			// 附件处理部分
			EMailUtil.attachmentHandler(mailSendEntity.getAttachments(), email);
			
			// 发送邮件
			email.send();
			
		} catch (Exception e) {
			log.error("testWithAttachment =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 发送html内容邮件
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSendHtmlMail() {
		try {
			String address1 = "dearzqy2010@qq.com";
			String address2 = "dearzqy@163.com";
			//
			final MailSendEntity mailSendEntity = new MailSendEntity();
			mailSendEntity.setSubject("邮件主题 : 这是我的测试邮件主题");
			mailSendEntity.setContent("html格式邮件内容 : 现在的时间是<h2> : " + new Date() + "</h2>");
			
			
			
			mailSendEntity.addToAddress(address1);
			mailSendEntity.addToAddress(address2);
			mailSendEntity.addCarbonCopyAddress(address1);
			mailSendEntity.addBlindCarbonCopyAddress(address1);
			
			// 添加附件 TODO 测试之前应该检查一下附件是否存在
			mailSendEntity.addAttachment(ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true));
			mailSendEntity.addAttachment(ClassPathUtil.getClassSubpath("/file/img/抱树小狗_01.jpg", true));
			
			// html 复合邮件
			final HtmlEmail email = new HtmlEmail();
			email.setHostName(mailBoxParam.getHost());
			email.setSmtpPort(mailBoxParam.getPort());
			// 设置验证
			email.setAuthenticator(new DefaultAuthenticator(mailBoxParam.getUsername(), mailBoxParam.getPassword()));
			email.setSSLOnConnect(true);
			
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
			
			// 附件处理部分
			EMailUtil.attachmentHandler(mailSendEntity.getAttachments(), email);
			
			  // set the alternative message (若不支持html消息，则提示如下信息)
			  email.setTextMsg("Your email client does not support HTML messages");
			
			// 发送邮件
			email.send();
			
		} catch (Exception e) {
			log.error("testSendHtmlMail =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 发送html 内嵌图片邮件
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSendImageHtmlMail() {
		try {
			// 相对路径 (完整路径 = 基本路径 + 相对路径)
			String imagePath = "img/金丝猴_01.jpg";
			final StringBuilder htmlBuilder = StringUtil.getBuilder();
			htmlBuilder.append("html格式邮件内容 : 现在的时间是<h2> : " + new Date() + "</h2>");
			
			htmlBuilder.append("图片区域<img src=\"" + imagePath + "\" />");
			
			String address = "dearzqy2010@qq.com";
			//
			final MailSendEntity mailSendEntity = new MailSendEntity();
			mailSendEntity.setSubject("邮件主题 : 这是我的测试邮件主题");
			//mailSendEntity.setContent("邮件内容 : 现在的时间是 : " + new Date());
			mailSendEntity.setContent(htmlBuilder.toString());
			mailSendEntity.addToAddress(address);
			mailSendEntity.addCarbonCopyAddress(address);
			mailSendEntity.addBlindCarbonCopyAddress(address);
			
			// 添加附件 TODO 测试之前应该检查一下附件是否存在
			mailSendEntity.addAttachment(ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true));
			mailSendEntity.addAttachment(ClassPathUtil.getClassSubpath("/file/img/抱树小狗_01.jpg", true));
			
			// html 图片复合邮件
			final ImageHtmlEmail email = new ImageHtmlEmail();
			email.setHostName(mailBoxParam.getHost());
			email.setSmtpPort(mailBoxParam.getPort());
			// 设置验证
			email.setAuthenticator(new DefaultAuthenticator(mailBoxParam.getUsername(), mailBoxParam.getPassword()));
			email.setSSLOnConnect(true);
			
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
			
			// 附件处理部分
			EMailUtil.attachmentHandler(mailSendEntity.getAttachments(), email);
			
			/**
			 org.apache.commons.mail.DataSourceResolver 接口实现类有
			 	--> org.apache.commons.mail.resolver.DataSourceBaseResolver (抽象类)
			 			--> org.apache.commons.mail.resolver.DataSourceClassPathResolver
			 			--> org.apache.commons.mail.resolver.DataSourceCompositeResolver
			 			--> org.apache.commons.mail.resolver.DataSourceFileResolver
			 			--> org.apache.commons.mail.resolver.DataSourceUrlResolver
			 
			 
			 */
			
			// html 嵌套图片
			//final URL url = new URL("http://www.abc.com/test.png");
			/*
			 相对路径 (完整路径 = 基本路径 + 相对路径)
			 通过 DataSourceFileResolver 将 html 模板中的相对路径 + baseDir中的基本路径，拼接之后进行处理
			 然后展示在邮件的html内容中.
			 */
			final File baseDir = new File(ClassPathUtil.getClassSubpath("/file/"));
			final DataSourceResolver dataSourceResolver = new DataSourceFileResolver(baseDir);
			email.setDataSourceResolver(dataSourceResolver);
			
			  // set the alternative message (若不支持html消息，则提示如下信息)
			  email.setTextMsg("Your email client does not support HTML messages");
			  
			// 发送邮件
			email.send();
			
		} catch (Exception e) {
			log.error("testSendImageHtmlMail =====> ", e);
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
			
			/* ClassPathUtil.getClassSubpath 使用的java.net.URL 来处理路径，含有中文字符的将进行编码
			有些File 方法无法直接解析url编码之后的字符，需要使用 java.net.URLDecoder 来进行解码
			*/
			// 编码之后的url (含有中文或其他非通用字符，需要进行编码然后再解析)
			String encodeUrl = ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg");
			// url 解码
			String decodeUrl = URLDecoder.decode(encodeUrl, Constant.CHART_SET_UTF_8);
			
			log.info("testTemp =====> " + encodeUrl);
			log.info("testTemp =====> " + decodeUrl);
			log.info("testTemp =====> " + ClassPathUtil.getClassSubpath("/file/img/"));
			
			log.info("testTemp =====> " + ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true));
			
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

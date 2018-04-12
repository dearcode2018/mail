/**
 * 描述: 
 * SpringJmailTest.java
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
import java.util.Date;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SpringJmailTest
 */
/*
 * 
 * @SpringJUnit4ClassRunner 运行器负责拉起 spring 环境
 * @ContextConfiguration 指定 spring配置文件，若不指定，则使用默认配置.
 */
// for Junit 4.x
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:conf/xml/applicationContext.xml"})
@ContextConfiguration(locations = {
		"classpath:conf/xml/spring-bean.xml", 
		"classpath:conf/xml/spring-config.xml", 
		"classpath:conf/xml/spring-jmail.xml"})
public final class SpringJmailTest extends BaseTest {

	/**
	 * 引当前项目用其他项目之后，然后可以使用
	 * SpringJunitTest模板测试的其他项目
	 * 
	 * 可以使用所引用目标项目的所有资源
	 * 若引用的项目的配置与本地的冲突或无法生效，需要
	 * 将目标项目的配置复制到当前项目同一路径下
	 * 
	 */
	
	@Resource
	private JavaMailSender javaMailSender;
	
	private String from = "dearzqy@163.com";
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSpringJunit() {
		try {
			String toAddress = "dearzqy2010@qq.com";
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setFrom(from, "别名 哈哈哈");
			mimeMessageHelper.setTo(toAddress);
			mimeMessageHelper.setSubject("邮件主题(spring-mail) : 这是我的测试邮件主题");
			// 邮件格式: 文本
			mimeMessageHelper.setText("邮件内容 : 现在的时间是 : " + new Date());
			
			// 发送邮件
			javaMailSender.send(mimeMessage);
			
		} catch (Exception e) {
			log.error("testSpringJunit =====> ", e);
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
			String toAddress = "dearzqy2010@qq.com";
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setFrom(from, "别名 哈哈哈");
			mimeMessageHelper.setTo(toAddress);
			mimeMessageHelper.setSubject("邮件主题(spring text-mail) : 这是我的测试邮件主题");
			// 邮件格式: 文本
			mimeMessageHelper.setText("邮件内容 : 现在的时间是 : " + new Date());
			
			// 发送邮件
			javaMailSender.send(mimeMessage);
			
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
			String toAddress = "dearzqy2010@qq.com";
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setTo(toAddress);
			mimeMessageHelper.setSubject("邮件主题(spring html-mail) : 这是我的测试邮件主题");
			// 邮件格式: html. true 表示启动HTML格式的邮件  
			mimeMessageHelper.setText("<html><head></head><body><h1>hello!!spring html Mail</h1></body></html>", true);
			
			// 发送邮件
			javaMailSender.send(mimeMessage);
			
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
	public void testSendEmbedImgMail() {
		try {
			String toAddress = "dearzqy2010@qq.com";
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			String contentId = "myImg";
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setTo(toAddress);
			mimeMessageHelper.setSubject("邮件主题(spring embed-img-mail) : 这是我的测试邮件主题");
			
			// 邮件格式: 嵌套图片. true 表示启动HTML格式的邮件  
			mimeMessageHelper.setText("<html><head></head><body><h1>hello!!spring image html mail</h1>"  
                        + "<img src=\"cid:"+ contentId +  "+\"/></body></html>", true);
			
			// 图片文件资源
			FileSystemResource imgResource = new FileSystemResource(new File(ClassPathUtil.getClassSubpath("/file/img/抱树小狗_01.jpg", true)));
			// 设置 图片到html中
			mimeMessageHelper.addInline(contentId, imgResource);
			
			// 发送邮件
			javaMailSender.send(mimeMessage);
			
		} catch (Exception e) {
			log.error("testSendEmbedImgMail =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSendWithAttachmentMail() {
		try {
			String toAddress = "dearzqy2010@qq.com";
			String from = "dearzqy@163.com";
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setTo(toAddress);
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setSubject("邮件主题(spring with-attachment-mail) : 这是我的测试邮件主题");
			// 邮件格式: html. true 表示启动HTML格式的邮件  
			mimeMessageHelper.setText("<html><head></head><body><h1>你好: 邮件中有附件，看看吧!</h1></body></html>", true);
			
			// 文件资源
			FileSystemResource file1 = new FileSystemResource(new File(ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true)));
			FileSystemResource file2 = new FileSystemResource(new File(ClassPathUtil.getClassSubpath("/file/img/长颈鹿_01.jpg", true)));
			
			// 添加附件
			mimeMessageHelper.addAttachment("白熊.jpg", file1);
			mimeMessageHelper.addAttachment("长颈鹿.jpg", file2);
			
			// 发送邮件
			javaMailSender.send(mimeMessage);
			
		} catch (Exception e) {
			log.error("testSendWithAttachmentMail =====> ", e);
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
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
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

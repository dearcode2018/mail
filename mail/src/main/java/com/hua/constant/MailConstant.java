package com.hua.constant;

public interface MailConstant {
	
	String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	
	String MAIL_SMTP_HOST = "mail.smtp.host";
	
	String MAIL_SMTP_PORT = "mail.smtp.port";
	
	String MAIL_SMTP_AUTH = "mail.smtp.auth";
	
	String MAIL_SEND_USERNAME = "mail.send.username";
	
	String MAIL_SEND_PASSWORD = "mail.send.password";
	
	String MAIL_SEND_FROM_ADDRESS= "mail.send.fromAddress";
	
	String MAIL_SEND_TO_ADDRESS= "mail.send.toAddress";
	
	/* 内容类型 */
	String CONTENT_TYPE = "text/html; charset=utf-8";
	
	/* 文本格式的邮件 */
	public static final int MAIL_TYPE_TEXT = 0;

	/* html格式的邮件 */
	public static final int MAIL_TYPE_HTML = 1;
}

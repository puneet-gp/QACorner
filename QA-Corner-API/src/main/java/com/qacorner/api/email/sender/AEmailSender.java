package com.qacorner.api.email.sender;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.qacorner.api.email.content.AMessageContent;
import com.qacorner.api.email.content.AttachmentMessageContent;
import com.qacorner.api.email.content.HtmlMessageContent;
import com.qacorner.api.email.content.PlainTextMessageContent;

public abstract class AEmailSender {

	private final Properties props = new Properties();
	private final Session session;
	private Message message;
	private String userName;
	private String mailSubject;
	private String[] recipients;
	private static final Logger _logger = Logger.getLogger("AEmailSender");
	
	public AEmailSender(final String userName, final String passWord,
			final String smtpHost, String mailSubject, String[] recipients) {
		_logger.info("Preparinf properites");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", /* "smtp.gmail.com" */smtpHost);
		props.put("mail.smtp.port", "587");
		_logger.info("Properties Prepared");
		session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				_logger.info("Authenticating user name & password");
				PasswordAuthentication authentication = new PasswordAuthentication(
						userName, passWord);
				_logger.info("Password Authenticated...");
				return authentication;
			}
		});
		this.userName = userName;
		this.mailSubject = mailSubject;
		this.recipients = recipients;
		message = new MimeMessage(session);
	}

	public abstract AMessageContent getMesssageContent();

	public synchronized void  sendEmail() {
		_logger.info("Preparing email body");
		try {
			message.setSubject(this.mailSubject);
			message.setFrom(new InternetAddress(this.userName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(StringUtils.join(this.recipients, ",")));
			AMessageContent content = this.getMesssageContent();
			if (content instanceof PlainTextMessageContent) {
				message.setText(String.valueOf(content.getpreparedContent()));
				_logger.info("Message is a plain text content message");
			} else if (content instanceof HtmlMessageContent) {
				message.setContent(
						String.valueOf(content.getpreparedContent()),
						"text/html");
				_logger.info("Message is a html content message");
			} else if (content instanceof AttachmentMessageContent) {
				message.setContent((Multipart) content.getpreparedContent());
				_logger.info("Message is a multipart message with attachment");
			}
			_logger.info("Sending email");
			Transport.send(message);
			_logger.info("Email sent..");
		} catch (MessagingException e) {
			_logger.error("Failed in sending email");
			_logger.error(e);
		}
	}
}
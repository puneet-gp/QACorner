package com.qacorner.api.email.sender;

import com.qacorner.api.email.content.AMessageContent;
import com.qacorner.api.email.content.HtmlMessageContent;

public class HtmlEmailSender extends AEmailSender {

	private String htmlContent;
	public HtmlEmailSender(String userName, String passWord, String smtpHost,
			String mailSubject, String[] recipients, String htmlContent) {
		super(userName, passWord, smtpHost, mailSubject, recipients);
		this.htmlContent =  htmlContent;
	}
	
	public HtmlEmailSender(String userName, String password, String smtpHost, String mailSubject, String recipient, String htmlContent) {
		this(userName, password, smtpHost, mailSubject, new String[] {recipient}, htmlContent);
	}

	@Override
	public AMessageContent getMesssageContent() {
		return new HtmlMessageContent(htmlContent);
	}
}
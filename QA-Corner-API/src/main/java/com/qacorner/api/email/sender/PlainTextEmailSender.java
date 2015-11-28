package com.qacorner.api.email.sender;

import com.qacorner.api.email.content.AMessageContent;
import com.qacorner.api.email.content.PlainTextMessageContent;

public class PlainTextEmailSender extends AEmailSender {

	private String plainTextContent;

	public PlainTextEmailSender(String userName, String passWord,
			String smtpHost, String mailSubject, String[] recipients,
			String plainTextContent) {
		super(userName, passWord, smtpHost, mailSubject, recipients);
		this.plainTextContent = plainTextContent;
	}

	@Override
	public AMessageContent getMesssageContent() {
		return new PlainTextMessageContent(plainTextContent);
	}
}
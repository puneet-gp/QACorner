package com.qacorner.api.email.sender;

import com.qacorner.api.email.content.AMessageContent;
import com.qacorner.api.email.content.AttachmentMessageContent;

public class AttachmentEmailSender extends AEmailSender {

	private String text;
	private String fileAbsolutePath;

	public AttachmentEmailSender(String userName, String passWord,
			String smtpHost, String mailSubject, String[] recipients,
			String text, String fileAbsolutePath) {
		super(userName, passWord, smtpHost, mailSubject, recipients);
		this.text = text;
		this.fileAbsolutePath = fileAbsolutePath;

	}

	@Override
	public AMessageContent getMesssageContent() {
		return new AttachmentMessageContent(text,
				fileAbsolutePath);
	}
}
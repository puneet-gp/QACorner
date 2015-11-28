package com.qacorner.api.email.content;

public class PlainTextMessageContent extends AMessageContent {

	private String plainTextContent;
	
	public PlainTextMessageContent(String plainTextContent) {
		this.plainTextContent = plainTextContent;
	}

	@Override
	public Object getpreparedContent() {
		return plainTextContent;
	}
}
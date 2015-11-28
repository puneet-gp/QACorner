package com.qacorner.api.email.content;


public class HtmlMessageContent extends AMessageContent {

	private String htmlContent;
	
	public HtmlMessageContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	
	@Override
	public Object getpreparedContent() {
		
		return htmlContent;
	}
}
package com.qacorner.api.email.content;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class AttachmentMessageContent extends AMessageContent {

	private String content;
	private File filePath;
	private static final Logger _logger = Logger.getLogger("AttachmentMessageContent");
	public AttachmentMessageContent(String content, File file) {
		this.content = content;
		this.filePath = file;
	}

	public AttachmentMessageContent(File file) {
		this("", file);
	}

	public AttachmentMessageContent(String content, String filePath) {
		this(content, new File(filePath));
	}

	@Override
	public Object getpreparedContent() {
		// Create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		try {
			messageBodyPart.setText(this.content);
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(
					this.filePath.getAbsolutePath());
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(this.filePath.getName());
			multipart.addBodyPart(messageBodyPart);
			return multipart;
		} catch (MessagingException e) {
			_logger.error("Failed to upload attachment cause of");
			_logger.error(e);
			return null;
		}
	}
}
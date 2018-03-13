package org.primejava.cms.pojo;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.common.base.Preconditions;

public class EmailForward implements Forwardable<EmailForwardPojo> {
	public void send(EmailForwardPojo pojo) {
		Preconditions.checkNotNull(pojo);
		try {
			Session session = getSession(pojo);
			session.setDebug(true);
			MimeMessage mailMessage = new MimeMessage(session);
			mailMessage.setFrom(new InternetAddress(pojo.getEmailFrom()));
			mailMessage.setRecipients(Message.RecipientType.TO,
					getToAddress(pojo.getDestinations()));
			if ((null != pojo.getCcUsers()) && (!pojo.getCcUsers().isEmpty())) {
				mailMessage.setRecipients(Message.RecipientType.CC,
						getToAddress(pojo.getCcUsers()));
			}
			if ((null != pojo.getBccUsers()) && (!pojo.getBccUsers().isEmpty())) {
				mailMessage.setRecipients(Message.RecipientType.BCC,
						getToAddress(pojo.getBccUsers()));
			}
			mailMessage.setSubject(pojo.getSubject());
			mailMessage.setSentDate(new Date());
			Multipart multiPart = getMultiPart(pojo.getAttachmentFiles());
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(pojo.getMessage());
			multiPart.addBodyPart(messageBodyPart);
			mailMessage.setContent(multiPart);
			Transport.send(mailMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ForwardException(e);
		}
	}

	private Multipart getMultiPart(List<String> attachmentFiles)
			throws MessagingException {
		if ((null == attachmentFiles) || (attachmentFiles.isEmpty())) {
			return new MimeMultipart();
		}
		Multipart multiPart = new MimeMultipart();
		for (String attatchFile : attachmentFiles) {
			BodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attatchFile);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(getFileName(attatchFile));
			multiPart.addBodyPart(messageBodyPart);
		}
		return multiPart;
	}

	private String getFileName(String filePath) {
		if (filePath.contains("/")) {
			return filePath.substring(filePath.lastIndexOf("/") + 1);
		}
		if (filePath.contains("\\")) {
			return filePath.substring(filePath.lastIndexOf("\\") + 1);
		}
		return filePath;
	}

	private Address[] getToAddress(List<String> destinations)
			throws AddressException {
		Address[] toAddress = new Address[destinations.size()];
		int i = 0;
		for (String destination : destinations) {
			toAddress[(i++)] = new InternetAddress(destination);
		}
		return toAddress;
	}

	private Session getSession(EmailForwardPojo pojo) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", pojo.getMailHost());
		properties.put("mail.smtp.port",
				Integer.valueOf(pojo.getMailHostPort()));
		properties.put("mail.smtp.auth", pojo.isLogonValidate() ? "true"
				: "false");
		if (pojo.isLogonValidate()) {
			Authenticator authenticator = new MailAuthenticator(pojo.getUser(),
					pojo.getPassword());

			return Session.getInstance(properties, authenticator);
		}
		return Session.getInstance(properties);
	}

}

package com.oscar.healtry_front.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	public void enviarEmail(final String to, final String subject, final String body) {

		var props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "2525"); // TODO puerto FakeSMTP, ver si hago correo por smtp

		var session = Session.getInstance(props);

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply@healtry.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

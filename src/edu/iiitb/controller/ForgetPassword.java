package edu.iiitb.controller;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import edu.iiitb.database.DB;

public class ForgetPassword {

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String execute() throws AddressException, MessagingException {
		setPassword(DB.seekPassword(getEmail()));
		System.out.println("Email:" + getEmail() + "  password:"
				+ getPassword());

		if (getPassword() != null) {
			System.out.println("in mail");
			String host = "smtp.gmail.com";
			String port = "587";
			final String adminUser = "we.are.randomised@gmail.com";
			final String adminPassword = "ooadproject";

			// sets SMTP server properties
			Properties properties = new Properties();
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.user", adminUser);
			properties.put("mail.password", adminPassword);

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(adminUser, adminPassword);
				}
			};
			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(adminUser));
			InternetAddress[] toAddresses = { new InternetAddress(getEmail()) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject("Flipkart Account - Password Recovery Link");
			msg.setSentDate(new Date());

			// creates message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart
					.setContent(
							"Dear Customer,"
									+ " Greetings from Flipkart! You received this email because you filled out a"
									+ " form on Flipkart.com  indicating that you had forgotten your password. "
									+ "This is your password: "
									+ password
									+ " .We recommend you to kindly update your password "
									+ "once you relogin with your present password. \n Thank you for shopping with us!"
									+ " Flipkart.com..."
									+ " The Online Megastore", "text/html");

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// sets the multi-part as e-mail's content
			msg.setContent(multipart);
			// sends the e-mail
			Transport.send(msg);
			return "success";
		}
		return "error";
	}

}

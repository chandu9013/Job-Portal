package com.practo.sai.jobportal.utility;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.practo.sai.jobportal.model.JobApplicationModel;

@Component
public class SmtpMailSender {

	private static final Logger LOG = Logger.getInstance(SmtpMailSender.class);

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendConfirmationMails(JobApplicationModel application)
			throws MessagingException, AuthenticationFailedException {
		sendMailToAdmin(application);
		sendMailToApplier(application);
	}

	public void sendMailToAdmin(JobApplicationModel application) throws MessagingException {

		String adminMail = application.getJob().getPostedBy().getEmailId();

		int jobId = application.getJob().getjId();
		String team = application.getJob().getTeam().getName();
		String category = application.getJob().getCategory().getCategoryName();

		String subject = "Application received for Job Id - " + jobId;

		String body = "Hi,\n\t\t An application has been received for \n " + jobId + "\t" + team + "\t" + category;
		LOG.debug(body);
		send(adminMail, subject, body);
	}

	public void sendMailToApplier(JobApplicationModel application)
			throws AuthenticationFailedException, MessagingException {
		String email = application.getEmployee().getEmailId();

		int jobId = application.getJob().getjId();
		String team = application.getJob().getTeam().getName();
		String category = application.getJob().getCategory().getCategoryName();

		String subject = "Application received for Job Id - " + jobId;

		String body = "Hi,\n\t\t You application for \n " + jobId + "\t" + team + "\t" + category
				+ " has been received";
		LOG.debug(body);
		send(email, subject, body);
	}

	private void send(String to, String subject, String body) throws MessagingException, AuthenticationFailedException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true); // true indicates
														// multipart message
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true); // true indicates html
		// continue using helper object for more functionalities like adding
		// attachments, etc.

		javaMailSender.send(message);
	}
}

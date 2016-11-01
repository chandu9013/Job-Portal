package com.practo.sai.jobportal.utility;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.model.JobApplicationModel;

/**
 * Class that provides methods to send notifier emails to admins and employees.
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Component
public class SmtpMailSender {

  /**
   * Logger for this class.
   */
  private static final Logger LOG = Logger.getInstance(SmtpMailSender.class);

  /**
   * {@link JavaMailSender}
   */
  @Autowired
  private JavaMailSender javaMailSender;

  /**
   * Method to send confirmation mails to admin and employee who applied for the job
   * 
   * @param application {@link JobApplicationModel}
   * @throws MessagingException Thrown when there is a problem sending mail to the parties.
   * @throws AuthenticationFailedException Thrown when there is a problem authenticating with the
   *         mailing service.
   */
  public void sendConfirmationMails(JobApplicationModel application)
      throws MessagingException, AuthenticationFailedException {
    sendMailToAdmin(application);
    sendMailToApplier(application);
  }

  /**
   * Method to send notifier mail to admin saying a new application has been received
   * 
   * @param application {@link JobApplicationModel}
   * @throws MessagingException Thrown when there is a problem sending mail to the parties.
   */
  public void sendMailToAdmin(JobApplicationModel application) throws MessagingException {

    String adminMail = application.getJob().getPostedBy().getEmailId();

    int jobId = application.getJob().getjId();
    String team = application.getJob().getTeam().getName();
    String category = application.getJob().getCategory().getCategoryName();

    String subject = "Application received for Job Id - " + jobId;

    String body = "Hi,\n\t\t An application has been received for \n " + jobId + "\t" + team + "\t"
        + category;
    LOG.debug(body);
    send(adminMail, subject, body);
  }

  /**
   * Method to send mail to employee confirming receipt of application for the job.
   * 
   * @param application {@link JobApplicationModel}
   * @throws AuthenticationFailedException Thrown when there is a problem authenticating with the
   *         mailing service.
   * @throws MessagingException Thrown when there is a problem sending mail to the parties.
   */
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

  /**
   * Method to send mail to employee confirming selection of application for the job.
   * 
   * @param job {@link Job}
   * @throws AuthenticationFailedException Thrown when there is a problem authenticating with the
   *         mailing service.
   * @throws MessagingException Thrown when there is a problem sending mail to the parties.
   */
  public void sendApproval(Job job) throws AuthenticationFailedException, MessagingException {
    String email = job.getEmployeeByRecruitId().getEmailId();

    int jobId = job.getJId();
    String team = job.getTeam().getName();
    String category = job.getCategory().getCategoryName();

    String subject = "Application for Job Id selected - " + jobId;

    String body = "Hi,\n\t\t You application for \n " + jobId + "\t" + team + "\t" + category
        + " has been selected";
    LOG.debug(body);
    send(email, subject, body);
  }

  /**
   * 
   * @param to EmailId of the concerned party
   * @param subject Subject of the email
   * @param body Body of the email
   * @throws MessagingException Thrown when there is a problem sending mail to the parties.
   * @throws AuthenticationFailedException Thrown when there is a problem authenticating with the
   *         mailing service.
   */
  private void send(final String to, final String subject, final String body)
      throws MessagingException, AuthenticationFailedException {

    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper;

    helper = new MimeMessageHelper(message, true); // true indicates
    // multipart message
    helper.setSubject(subject);
    helper.setTo(to);
    helper.setText(body, false); // true indicates html
    // continue using helper object for more functionalities like adding
    // attachments, etc.

    javaMailSender.send(message);
  }
}

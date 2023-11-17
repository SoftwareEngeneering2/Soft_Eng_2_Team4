package com.example.Financers.helpers;

import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class EmailService {
    private final static String RESET_PASSWORD_MESSAGE = """
    We have received a request to reset your Financer's Guide To Life account's password. Please click on the following link to reset your password.\n
    """;
    private final static String RESET_PASSWORD_SUBJECT = "Financer's Guide to Life Password Reset";
    private final static String RESET_PASSWORD_LINK_TEXT = "Reset Password";
    private final static String COMPANY_EMAIL = "fgl.noreply.fgl@gmail.com";
    private final static String COMPANY_EMAIL_PASSWORD = "vxdl pmuh ubay qhzi"; //shhhhhhh no looky

    private Session getEmailSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(COMPANY_EMAIL, COMPANY_EMAIL_PASSWORD);
            }
        });
        return session;
    }
    public boolean sendResetPasswordLink(String email, String link) {
        String htmlMessage = String.format("%s\n<a href=%s>%s</a>",
                RESET_PASSWORD_MESSAGE, link, RESET_PASSWORD_LINK_TEXT
        );
        Session session = getEmailSession();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(COMPANY_EMAIL));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(RESET_PASSWORD_SUBJECT);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart html = new MimeBodyPart();
            html.setContent(htmlMessage, "text/html");
            multipart.addBodyPart(html);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

package ExpenseTracker;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Authentication {
    public class SendEmail {
        Properties properties;

        public SendEmail() {
            properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String accountEmail = "badgalriribean12@gmail.com";
            String password = "Jovilyns101"; // Use the actual Gmail password or app password here

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(accountEmail, password);
                }
            });

            try {
                // Prepare the email message
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(accountEmail));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress("recipient@example.com"));
                message.setSubject("Test Email");
                message.setText("This is a test email sent from Jakarta Mail.");

                // Send the email
                Transport.send(message);
                System.out.println("Email sent successfully.");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}

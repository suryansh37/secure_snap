import javax.mail.*;
import javax.mail.internet.*;
import javax.sql.DataSource;

import java.util.Properties;

public class EmailSender {

    public static void main(String[] args) {
        String senderEmail = "your-email@gmail.com";
        String senderPassword = "your-email-password";
        String recipientEmail = "recipient-email@example.com";
        String subject = "Encrypted Image";

        try {
            // Create a session with the mail server
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            // Create a MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Add text part (you can skip this if you only want to send the image)
            BodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Encrypted image attached.");
            multipart.addBodyPart(textBodyPart);

            // Add image attachment
            BodyPart imageBodyPart = new MimeBodyPart();
            String encryptedImagePath = "C:\\Users\\EVERVITAL\\Desktop\\minipro\\encrypted-image.jpg"; // Replace with your path
            // DataSource source = new FileDataSource(encryptedImagePath);
            // imageBodyPart.setDataHandler(new DataHandler(source));
            imageBodyPart.setFileName("encrypted-image.jpg");
            multipart.addBodyPart(imageBodyPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

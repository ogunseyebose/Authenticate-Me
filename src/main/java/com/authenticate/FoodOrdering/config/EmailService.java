package com.authenticate.FoodOrdering.config;


import com.authenticate.FoodOrdering.exception.MailingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;
    public void sendmail(String sender,String recipient,String subject,String content) throws AddressException, MessagingException, IOException, MailingException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(content);
        message.setSentDate(new Date());
        try{
        emailSender.send(message);
        }catch (MailException error){
            throw new MailingException("99","Error Sending Mail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
/*public void sendmail(String sender,String recipient,String subject,String content) throws AddressException, MessagingException, IOException, MailingException {
try {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            StandardCharsets.UTF_8.name());

    helper.addAttachment("logoNew.png", new ClassPathResource("memorynotfound-logo.png"));
    String inlineImage = "<img src=\"/Users/ogunseyebose/Desktop/logoNew.png\"></img><br/>";

    helper.setText(inlineImage + content, true);
    helper.setSubject(subject);
    helper.setTo(recipient);
    helper.setFrom(sender);

    emailSender.send(message);
}
    catch (MailException error){
        throw new MailingException("99","Error Sending Mail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}*/
}

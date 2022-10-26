package com.authenticate.FoodOrdering.config;


import com.authenticate.FoodOrdering.exception.MailingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;

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
public void sendmail(String sender,String recipient,String subject,String content,String attachFile) throws AddressException, MessagingException, IOException, MailingException {

    MimeMessagePreparator preparator = new MimeMessagePreparator()
    {
        public void prepare(MimeMessage mimeMessage) throws Exception
        {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            mimeMessage.setFrom(new InternetAddress(sender));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(content);
            FileInputStream inputStream = new FileInputStream(new File(attachFile));

            //FileSystemResource file = new FileSystemResource(new File(attachFile));
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.addAttachment("Picture", new ByteArrayResource(IOUtils.toByteArray(inputStream)));
        }
    };

    try {
       emailSender.send(preparator);
    }
    catch (MailException error){
        throw new MailingException("99","Error Sending Mail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}

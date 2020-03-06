package com.grow.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailClient {
    @Autowired private JavaMailSender javaMailSender;
   // @Autowired
   // private HotelServiceConfig hotelServiceConfig;

    private static final Logger logger = LoggerFactory.getLogger(EmailClient.class);

    public Boolean sendMail(String to, String cc, String subject, String bodyText , String replyTo) {
        Boolean sentStatus = Boolean.TRUE;
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String recipients[] = to.split(",");
            helper.setTo(recipients);
            if(cc != null){
                String[] ccList = cc.split(",");
                if(ccList.length > 0){
                    helper.setCc(ccList);
                }
            }
            helper.setFrom("manoj.singh", "Yatra for Business");
            helper.setSubject(subject);
            helper.setText(bodyText, true);
            if(replyTo!=null)
                helper.setReplyTo(replyTo);

            javaMailSender.send(message);
        }catch (Exception e){
            logger.error("Error occurred while sending email : {}", e);
            sentStatus = Boolean.FALSE;
        }
        return sentStatus;
    }


    /*public Boolean sendMail(String to, String cc, String subject, String bodyText) {
        return sendMail(to, cc, subject, bodyText, null);
    }


    public Boolean sendMail(String to, String cc, String subject, String bodyText, String fileName, String filePath) {
        Boolean sentStatus = Boolean.TRUE;
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            if(!cc.isEmpty())
                helper.setCc(cc);
            helper.setFrom(hotelServiceConfig.getConfirmationFromEmailAddress(), "Yatra For Business");
            helper.setSubject(subject);
            helper.setText(bodyText, true);

            FileSystemResource file = new FileSystemResource(filePath);
            if(file.exists()){
                helper.addAttachment(fileName, file);
            }
        }catch (MessagingException e){
            logger.error("Error caught while sending email to : {}, Exception : {}", to, e);
            sentStatus = Boolean.FALSE;
        }catch (Exception e){
            logger.error("Error caught while sending email to : {}, Exception : {}", to, e);
            sentStatus = Boolean.FALSE;
        }
        return sentStatus;
    }*/

}

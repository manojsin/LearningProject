/*
package com.grow.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Utility {
    @Bean(name = "JavaMailSender")
    //@Conditional(EmailConfigurationCondition.class)
    public JavaMailSender javaMailService() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp1.yatra.in");
        mailSender.setPort(25);
        Properties props = new Properties();
        props.put("mail.transport.protJavaMailSenderImplocol", "smtp");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }

}
*/

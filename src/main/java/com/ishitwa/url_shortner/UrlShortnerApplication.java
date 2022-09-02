package com.ishitwa.url_shortner;

import com.ishitwa.url_shortner.config.SecurityConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;


@EnableAsync
@SpringBootApplication
@EnableMongoRepositories
public class UrlShortnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlShortnerApplication.class, args);
    }
    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(SecurityConstants.smtp);
        mailSender.setPort(SecurityConstants.port);
        mailSender.setUsername(SecurityConstants.email);
        mailSender.setPassword(SecurityConstants.password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(
                        "http://localhost:8080/",
                        "https://ishitwaagrawal.github.io/",
			"http://server2.ishitw.me/",
			"http://urlshort.ishitw.me/",
			"http://172.105.51.155/"
                ).allowedMethods("GET","POST","PUT","DELETE");
            }
        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

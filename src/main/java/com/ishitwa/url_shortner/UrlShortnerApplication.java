package com.ishitwa.url_shortner;

import com.ishitwa.url_shortner.config.SecurityConstants;
import com.ishitwa.url_shortner.repository.UrlRepo;
import com.ishitwa.url_shortner.repository.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;


@EnableAsync
@SpringBootApplication
//@EnableSwagger2
@EnableJpaRepositories(basePackageClasses = {UserRepo.class, UrlRepo.class})
public class UrlShortnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlShortnerApplication.class, args);
    }
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.ishitwa.url_shortner")).build();
//    }
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
                        "http://localhost:8080",
                        "https://ishitwaagrawal.github.io/Url-Shortner/"
                );
            }
        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

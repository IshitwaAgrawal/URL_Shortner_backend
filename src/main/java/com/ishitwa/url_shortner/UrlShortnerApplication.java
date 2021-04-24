package com.ishitwa.url_shortner;

import com.ishitwa.url_shortner.repository.UrlRepo;
import com.ishitwa.url_shortner.repository.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepo.class, UrlRepo.class})
public class UrlShortnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlShortnerApplication.class, args);
    }
}

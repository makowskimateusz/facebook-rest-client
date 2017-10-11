package pl.makowski.mateusz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class FacebookRestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacebookRestClientApplication.class, args);
    }
}

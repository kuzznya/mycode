package ru.teamnull.mycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class MycodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycodeApplication.class, args);
    }

}

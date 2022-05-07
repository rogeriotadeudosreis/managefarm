package br.com.rogerio.manageFarm;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ManageFarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageFarmApplication.class, args);
    }

}

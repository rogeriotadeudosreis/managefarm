package br.com.rogerio.manageFarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ManageFarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageFarmApplication.class, args);
    }


}

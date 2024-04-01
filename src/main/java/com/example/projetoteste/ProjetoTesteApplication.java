package com.example.projetoteste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class
        }
)
public class ProjetoTesteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoTesteApplication.class, args);
    }

}

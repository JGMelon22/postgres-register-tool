package com.joaog.br.postgres_register_tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PostgresRegisterToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresRegisterToolApplication.class, args);
    }

}

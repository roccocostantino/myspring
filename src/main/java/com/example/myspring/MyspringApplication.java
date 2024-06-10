package com.example.myspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.myspring.app.persistence.repository")
@ComponentScan(basePackages = { "com.example.myspring.app.*" })
@EntityScan("com.example.myspring.app.persistence.model")
@SpringBootApplication
public class MyspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyspringApplication.class, args);
	}

}

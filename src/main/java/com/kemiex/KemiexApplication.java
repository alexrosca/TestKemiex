package com.kemiex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class KemiexApplication {

	public static void main(String[] args) {
		SpringApplication.run(KemiexApplication.class, args);
	}

}

package com.carlos.infnet.vatrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VatrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(VatrateApplication.class, args);
	}

}

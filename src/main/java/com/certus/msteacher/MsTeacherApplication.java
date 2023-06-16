package com.certus.msteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients("com.edu.certus.profesor.client")
@SpringBootApplication
public class MsTeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTeacherApplication.class, args);
	}

}

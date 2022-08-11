package com.recordshop.diskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class DiskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiskServiceApplication.class, args);
	}

}

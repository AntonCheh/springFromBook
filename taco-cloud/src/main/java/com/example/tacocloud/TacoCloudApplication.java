package com.example.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		System.setProperty("org.apache.tomcat.jni.disable", "true");
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}

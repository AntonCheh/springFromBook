package com.example.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.from(TacoCloudApplication::main).with(TestTacoCloudApplication.class).run(args);
	}

}

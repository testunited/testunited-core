package org.testunited.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"org.testunited.webapi"})
@SpringBootApplication
public class TestUnitedApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestUnitedApplication.class, args);
	}

}

package io.github.LucasZSGP.medium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"org.openapitools.model"})
public class MediumApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediumApplication.class, args);
	}

}

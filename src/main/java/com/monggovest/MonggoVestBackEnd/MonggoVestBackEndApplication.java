package com.monggovest.MonggoVestBackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class MonggoVestBackEndApplication {


	public static void main(String[] args) {

		SpringApplication.run(MonggoVestBackEndApplication.class, args);
	}

}


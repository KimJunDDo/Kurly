package com.board.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class BoardMakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardMakingApplication.class, args);
	}

}

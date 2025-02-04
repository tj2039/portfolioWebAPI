package com._2je7.pofol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PortfolioWebApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PortfolioWebApiApplication.class, args);
	}
	
}

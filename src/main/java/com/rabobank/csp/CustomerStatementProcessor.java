package com.rabobank.csp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author sindhu Starting class of the application. We can just run this class
 *         as a standalone application
 */
@SpringBootApplication
@ComponentScan({ "com.rabobank.csp.constant", "com.rabobank.csp.controller",
		"com.rabobank.csp.model", "com.rabobank.csp.service",
		"com.rabobank.csp.serviceImpl" })
public class CustomerStatementProcessor {
	public static void main(String[] args) {
		SpringApplication.run(CustomerStatementProcessor.class, args);
	}
}

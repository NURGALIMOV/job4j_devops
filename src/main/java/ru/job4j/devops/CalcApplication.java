package ru.job4j.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Base class.
 */
@SpringBootApplication
public class CalcApplication {

	/**
	 * Constructor.
	 */
	public CalcApplication() {
	}

	/**
	 * Main method.
	 *
	 * @param args - argument array
	 */
	public static void main(String[] args) {
		SpringApplication.run(CalcApplication.class, args);
	}
}

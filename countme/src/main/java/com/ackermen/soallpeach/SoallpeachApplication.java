package com.ackermen.soallpeach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@SpringBootApplication
public class SoallpeachApplication {

	private volatile int sum = 0;

	@GetMapping(value = "/count")
	public int available() {
		return sum;
	}

	@PostMapping(value = "/")
	synchronized public void checkedOut(@RequestBody String input) {
		try {
			sum += Integer.valueOf(input.subSequence(0, input.length()-1).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SoallpeachApplication.class, args);
	}

}
 
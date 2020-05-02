package com.ackermen.soallpeach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@SpringBootApplication
public class SoallpeachApplication {

	private List<String> sum = Collections.synchronizedList(new ArrayList<>());

	@GetMapping(value = "/count")
	public int available() {
		int num = 0;
		// System.out.println("GET COUNT: " + sum);
		for (String s: sum) {
			try {
				Integer temp = Integer.valueOf(s);
				num += temp;
			} catch (Exception e) {
				
			}
		}
		return num;
	}

	@PostMapping(value = "/")
	synchronized public void checkedOut(@RequestBody String input) {
		// System.out.println("POST: " + input);
		try {
			sum.add(input);
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SoallpeachApplication.class, args);
	}

}
 
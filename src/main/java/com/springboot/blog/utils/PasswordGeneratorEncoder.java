package com.springboot.blog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode(("Ju.55.25.17")));
		System.out.println(passwordEncoder.encode("carlos84america"));
		System.out.println(passwordEncoder.encode("jaq.77"));

	}
}

package com.atguigu.securitydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import java.security.SecureRandom;

@SpringBootTest
class SecurityDemoApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void testEncode() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(31, new SecureRandom());
		String encode = encoder.encode("123456");
		System.out.println(encode);
		Assert.isTrue(encoder.matches("123456", encode), "密码不一致");
	}
}

package com.online.springioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringIocApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private final static String url = "http://localhost:";
	
	@Test
	public void contextLoads() {
		String res = restTemplate
				.getForObject(new StringBuilder(url)
				.append(port).append("hello").toString(), String.class)
				.toString();
		System.out.println("res");
	}

}

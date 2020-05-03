package com.example.demo;

import java.util.Random;

import brave.Tracer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController
		implements ApplicationListener<ServletWebServerInitializedEvent> {

	private static final Log log = LogFactory.getLog(DemoController.class);
	private final Random random = new Random();
	private int port;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/")
	public String greeting() throws InterruptedException {
		log.info("hello");
		Thread.sleep(this.random.nextInt(1000));
		String hi = this.restTemplate
				.getForObject("http://localhost:" + this.port + "/hi", String.class);
		return "hello/" + hi;
	}

	@RequestMapping("/hi")
	public String hi() throws InterruptedException {
		log.info("hi");
		int millis = this.random.nextInt(1000);
		Thread.sleep(millis);
		String hey = this.restTemplate
				.getForObject("http://localhost:" + this.port + "/hey", String.class);
		return "hi/" + hey;
	}

	@RequestMapping("/hey")
	public String hey() throws InterruptedException {
		log.info("hey");
		int millis = this.random.nextInt(1000);
		Thread.sleep(millis);
		return "hey";
	}

	@Override
	public void onApplicationEvent(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
		this.port = servletWebServerInitializedEvent.getSource().getPort();
	}
}

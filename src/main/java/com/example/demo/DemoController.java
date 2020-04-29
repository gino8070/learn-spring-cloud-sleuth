package com.example.demo;

import java.util.Random;
import java.util.concurrent.Callable;

import brave.Span;
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
	@Autowired
	private RestTemplate restTemplate;
	private int port;

	@RequestMapping("/")
	public String greeting() throws InterruptedException {
		log.info("hello");
		String hi = this.restTemplate
				.getForObject("http://localhost:" + this.port + "/hi", String.class);
		String hey = this.restTemplate
				.getForObject("http://localhost:" + this.port + "/hey", String.class);
		return "hello/" + hi + "/" + hey;
	}

	@RequestMapping("/hi")
	public String hi() throws InterruptedException {
		log.info("hi");
		return "hi";
	}

	@RequestMapping("/hey")
	public String hey() throws InterruptedException {
		log.info("hey");
		return "hey";
	}

	@Override
	public void onApplicationEvent(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
		this.port = servletWebServerInitializedEvent.getSource().getPort();
	}
}

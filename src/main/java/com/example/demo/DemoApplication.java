package com.example.demo;

import brave.Tracing;
import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import zipkin2.Span;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.xray_udp.XRayUDPReporter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Tracing tracing(@Value("${spring.application.name:spring-tracing}") String serviceName,
	                       Reporter<Span> spanReporter) {
		return Tracing.newBuilder()
				.localServiceName(serviceName)
				.spanReporter(XRayUDPReporter.create())
				.traceId128Bit(true)
				.sampler(Sampler.ALWAYS_SAMPLE)
				.build();
	}
}

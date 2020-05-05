package com.example.demo;

import org.springframework.cloud.sleuth.zipkin2.ZipkinAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.xray_udp.XRayUDPReporter;

@Configuration
public class XrayConfig {
	@Bean(ZipkinAutoConfiguration.REPORTER_BEAN_NAME)
	Reporter<Span> myReporter() {
		return XRayUDPReporter.create();
	}
}

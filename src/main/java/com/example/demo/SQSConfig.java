package com.example.demo;

import org.springframework.cloud.sleuth.zipkin2.ZipkinAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.sqs.SQSSender;

@Configuration
public class SQSConfig {
	@Bean(ZipkinAutoConfiguration.REPORTER_BEAN_NAME)
	Reporter<Span> myReporter() {
		return AsyncReporter.create(sqsSender());
	}
	@Bean(ZipkinAutoConfiguration.SENDER_BEAN_NAME)
	Sender sqsSender() {
		return SQSSender.create("xxxxxxx");
	}
}

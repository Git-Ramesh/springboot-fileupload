package com.rs.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebMvcConfig {
	// @Bean(name = "multipartResolver")
	// public CommonsMultipartResolver multipartResolver() {
		// System.out.println("WebMvcConfig::multipartResolver()");
		// CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		// multipartResolver.setMaxUploadSize(269587795656L);
		// return multipartResolver;
	// }

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

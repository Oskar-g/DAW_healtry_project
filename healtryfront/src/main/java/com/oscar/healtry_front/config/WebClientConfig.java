package com.oscar.healtry_front.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

	@Value("${app.backend.base-url}")
	private String apiUrl;

	@Bean
	@Qualifier("api-web-client")
	public WebClient apiWebClient() {
		return WebClient.builder().baseUrl(apiUrl).build();
	}

}

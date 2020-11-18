package com.archeroes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.archeroes.service.WebCrawler;

@Configuration
public class BootstrapData {
	
	@Autowired
	private WebCrawler webCrawler;
	
	@Bean
	public void test() {
		webCrawler.getHeroesDCLinkWikiFandom();
	}
	
}

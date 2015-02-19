package com.safien.code2015.disasterapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	private String cloudDBService;

	public String getCloudDBService() {
		return cloudDBService;
	}

	public void setCloudDBService(String cloudDBService) {
		this.cloudDBService = cloudDBService;
	}
}

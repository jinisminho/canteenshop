package com.spring2020.customerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaAuditing
public class CustomerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				String frontendPort = "http://localhost:3000";
				registry.addMapping("/**").allowedOrigins(frontendPort);
			}
		};
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer()
//	{
//		return new WebMvcConfigurer()
//		{
//			@Override
//			public void addCorsMappings(CorsRegistry registry)
//			{
//				String frontendPort = "http://localhost:3000";
//				registry.addMapping("/**").allowedOrigins(frontendPort);
//
//			}
//		};
//	}

}

package com.resource.server;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@RestController
@EnableResourceServer
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class ResourceServerApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@CrossOrigin
	public String securedCall(HttpServletRequest request) {
		return "DKOM IS REQUESTING THE DATA FROM :"+request.getContextPath()+" and Auth Key is :";
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:8080")
		.allowedMethods("PUT", "DELETE", "GET", "POST")
		.allowedHeaders("*")
		.allowCredentials(false).maxAge(3600);
	}
}

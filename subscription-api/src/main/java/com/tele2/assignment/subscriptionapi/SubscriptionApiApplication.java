package com.tele2.assignment.subscriptionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tele2.assignment.subscriptionapi.filters.CustomURLFilter;

@SpringBootApplication

public class SubscriptionApiApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(SubscriptionApiApplication.class, args);
	
		
	}
	/*
	 * @Bean public FilterRegistrationBean < CustomURLFilter >
	 * filterRegistrationBean() { FilterRegistrationBean < CustomURLFilter >
	 * registrationBean = new FilterRegistrationBean(); CustomURLFilter
	 * customURLFilter = new CustomURLFilter();
	 * 
	 * registrationBean.setFilter(customURLFilter);
	 * registrationBean.addUrlPatterns("/greeting/*"); registrationBean.setOrder(2);
	 * //set precedence return registrationBean; }
	 */
}

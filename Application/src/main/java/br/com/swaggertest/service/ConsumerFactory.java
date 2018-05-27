package br.com.swaggertest.service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.automation.service.IAutomationTool;
import br.com.automation.service.swagger.SwaggerTest;
import br.com.automation.service.tools.SeleniumTool;

@Configuration
public class ConsumerFactory {

	@Bean
	public static SwaggerTest createSwaggerTest(){
		return new SwaggerTest();
	}
	
	@Bean
	public static IAutomationTool createAutomationToolTest(){
		return new SeleniumTool();
	}
}
package hu.ericsson.spring.importselector.module.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = {"hu.ericsson"})
@DependsOn(value = {"appConfigB"})
public class AppConfig {

	
	@Bean
	@Conditional(PropertySourceCondition.class)
	public static PropertySourcesPlaceholderConfigurer getPropertyPlaceholderConfigurer(){
		
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocation(new ClassPathResource("spring-config/application.properties"));
		return configurer;
	}

}

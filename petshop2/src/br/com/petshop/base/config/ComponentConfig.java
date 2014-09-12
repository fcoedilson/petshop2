package br.com.petshop.base.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages="br.com.petshop", excludeFilters={ @Filter(Configuration.class)} )
@PropertySource(value={"classpath:application.main.properties","classpath:processador.main.properties", "classpath:mail.main.properties"})
public class ComponentConfig {
	

}
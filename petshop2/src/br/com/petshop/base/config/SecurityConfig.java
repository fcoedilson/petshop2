package br.com.petshop.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={"classpath:br/com/petshop/base/config/spring-security.xml"})
public class SecurityConfig {}

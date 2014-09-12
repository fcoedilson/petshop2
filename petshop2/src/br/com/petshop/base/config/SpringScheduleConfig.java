package br.com.petshop.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:br/com/petshop/base/config/spring-schedule.xml")
public class SpringScheduleConfig {}
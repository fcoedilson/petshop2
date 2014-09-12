package br.com.petshop.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import br.com.petshop.base.util.Conexao;

@Configuration
public class ConexaoConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public Conexao conexao(){
		return new Conexao(environment.getProperty("db.url"), environment.getProperty("db.driver"), environment.getProperty("db.username"), environment.getProperty("db.password"));
	}
	
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
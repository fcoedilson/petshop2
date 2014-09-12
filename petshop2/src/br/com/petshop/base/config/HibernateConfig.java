package br.com.petshop.base.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig extends DataSourceConfig {

	@Value("hibernate.properties")
	private Properties hibernateProperties;

	Properties hibernateProperties(){
		return hibernateProperties;
	}

	@Bean
	LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean asb = new LocalSessionFactoryBean();
		asb.setDataSource(remoteDataSource());
		asb.setPackagesToScan(new String[]{"br.com.petshop"});
		asb.setHibernateProperties(hibernateProperties());
		return asb;
	}
	
	@Bean
	SessionFactory sessionFactory() {
		return sessionFactoryBean().getObject();
	}
	
}

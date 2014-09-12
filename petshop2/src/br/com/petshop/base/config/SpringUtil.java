package br.com.petshop.base.config;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringUtil {

	private static final ApplicationContext applicationContext;

	private static Logger logger = Logger.getLogger(SpringUtil.class);

	static {

		applicationContext = new AnnotationConfigApplicationContext("br.com.petshop");

		logger.debug("Contexto da aplicacao iniciado com sucesso.");
	}

	public static Object getBean(String bean){
		return  applicationContext.getBean(bean);
	}

}

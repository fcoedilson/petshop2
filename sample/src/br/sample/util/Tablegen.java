package br.sample.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Tablegen {
	
	public static void main(String[] args){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory ("sample") ;
		factory.close();

	}

}

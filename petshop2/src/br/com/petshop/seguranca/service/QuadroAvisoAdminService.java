package br.com.petshop.seguranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuadroAvisoAdminService {


	@Autowired
	private EmailService emailService;
	

	public Long contarTodosLogEmailNaoEnviados(){
		return emailService.countAllFalse();
	}
	
}
package br.com.petshop.seguranca.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.petshop.architecture.actions.ActionSearch;
import br.com.petshop.architecture.actions.DefaultActionSearch;
import br.com.petshop.architecture.modules.AbstractFacadeBean;
import br.com.petshop.base.config.Mail;
import br.com.petshop.base.entity.Email;
import br.com.petshop.seguranca.service.EmailService;

@ViewScoped
@ManagedBean
public class EmailBean extends AbstractFacadeBean<Email> {

	private static final long serialVersionUID = -110607125939884428L;
	
	@ManagedProperty(value="#{emailService}")
	private EmailService emailService;
	
	@ManagedProperty(value="#{mail}")
	private Mail mail;

	protected String getPageTitle() {
		return "titulo_cadastro_email";
	}
	
	public void initBean(){
		getFilter().put(STATUS, "0");
		getFilter().put(EmailService.REENVIO, "0");
		if(fromQuadroAviso()){
			getFilter().put(STATUS, false);
			getPagedList();
			search(true);
		}
	}
	
	public void reenviarEmail(Email email){
		int reenviado = email.getReenviado();
		reenviado++;
		email.setReenviado(reenviado);
		mail.enviarEmail(email);
	}
	
	protected Class<Email> getDefaultEntityClass() {
		return Email.class;
	}
	
	protected ActionSearch<Email> searchAction() {
		return new DefaultActionSearch<Email>(emailService);
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}
}
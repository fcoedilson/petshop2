package br.com.clinipet.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinipet.entity.Usuario;
import br.com.clinipet.service.UsuarioService;


@ConversationScoped
@Named("userBean")
public class UsuarioBean implements Serializable{


	@Inject
	UsuarioService service;

	@Inject
	Conversation conversation;

	private Usuario user;
	
	private List<Usuario> list = service.list();

	private Long id;

	public String add() {
		user = new Usuario();
		if (conversation.isTransient())
			conversation.begin();	
		return "userAdd";
	}

	public String addUser() {

		user.setDataCadastro(new Date());
		service.add(user);
		return "userEdit";
	}

	public String search() {
		this.user = service.find(id);
		if (this.user == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"User not found", "Usr with id "+ id + " not found"));
			return null;
		}
		if (conversation.isTransient())
			conversation.begin();
		return "userEdit";
	}

	public String delete() {
		service.delete(id);
		if (!conversation.isTransient())
			conversation.end();
		return "userHome";
	}

	public String update() {
		service.update(user);
		return "driverEdit";
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public List<Usuario> getList() {
		return list;
	}

	public void setList(List<Usuario> list) {
		this.list = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

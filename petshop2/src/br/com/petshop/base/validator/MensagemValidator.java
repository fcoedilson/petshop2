package br.com.petshop.base.validator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

	public class MensagemValidator {

		static FacesMessage message = new FacesMessage();

		public static class MensagensValidator {

			public static FacesMessage mensagemStatus(String msg, String componentID) {

				message.setDetail(msg);
				message.setSummary(msg);
				message.setSeverity(FacesMessage.SEVERITY_INFO);
				FacesContext currentInstance = FacesContext.getCurrentInstance();
				currentInstance.addMessage(componentID, message);
				return message;
			}

			public static FacesMessage mensagemErro(String msg, String componentID) {
				
				message.setDetail(msg);
				message.setSummary(msg);
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext currentInstance = FacesContext.getCurrentInstance();
				currentInstance.addMessage(componentID, message);
				return message;
			}
		}
	}

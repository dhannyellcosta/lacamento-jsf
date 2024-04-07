package br.com.lancamento.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class JsfUtil {
	
	private static void addMessage(String mensagem, Severity severity) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severity, null, mensagem));
	}
	
	public static void addSucessMessage(String mensagem) {
		addMessage(mensagem, FacesMessage.SEVERITY_INFO);
	}
	
	public static void addErroMessage(String mensagem) {
		addMessage(mensagem, FacesMessage.SEVERITY_ERROR);
	}
	
	public static void addAlertMessage(String mensagem) {
		addMessage(mensagem, FacesMessage.SEVERITY_WARN);
	}

}

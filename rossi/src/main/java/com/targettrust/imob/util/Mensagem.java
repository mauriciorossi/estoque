package com.targettrust.imob.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {

    public static final void add(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
    }

    public static final void error(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, ""));
    }

}

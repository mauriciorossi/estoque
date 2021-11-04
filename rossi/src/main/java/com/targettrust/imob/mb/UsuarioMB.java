package com.targettrust.imob.mb;

import com.targettrust.imob.bean.UsuarioBean;
import com.targettrust.imob.rn.UsuarioBD;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsuarioMB extends UsuarioBD{
    private UsuarioBean usuario;

    public UsuarioMB(){
    usuario = new UsuarioBean();
    }
    
    public String consultaUsuario() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        boolean login = consultaUsuarioBD(usuario);  
        if(login){
            //System.setProperty ("java.awt.headless", "true"); 
            //Toolkit tk = Toolkit.getDefaultToolkit();
            //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            return "index1";
        }
        else{
             /* Cria uma mensagem. */
      FacesMessage msg = new FacesMessage("Usuário ou senha inválido!");
      /* Obtém a instancia atual do FacesContext e adiciona a mensagem de erro nele. */
      FacesContext.getCurrentInstance().addMessage("erro", msg);
      return null;
        }
    }
    /**
     * @return the usuario
     */
    public UsuarioBean getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }
    
    /**
     *
     * @return
     */
    public String mataSessao(){
        System.out.println("eh aqui");
   // FacesContext fc = FacesContext.getCurrentInstance();    
   // HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);    
    //session.invalidate();
    return "index";    
    }
    
}

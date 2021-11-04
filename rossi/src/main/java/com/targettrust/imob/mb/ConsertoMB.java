package com.targettrust.imob.mb;

import com.targettrust.imob.bean.ConsertoBean;
import com.targettrust.imob.rn.ConsertoBD;
import com.targettrust.imob.util.Mensagem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConsertoMB {

    private ConsertoBean conserto;

    public ConsertoMB() {
        conserto = new ConsertoBean();
    }

    public List<ConsertoBean> getLista() {
        try {
            ArrayList<ConsertoBean> lista = new ArrayList<ConsertoBean>();
            lista = new ConsertoBD().listaConsertos(conserto);
            //conserto.setConsFiltro("");
            return lista;
        } catch (ClassNotFoundException e) {
            Mensagem.error(e.getMessage());
        } catch (IllegalAccessException e) {
            Mensagem.error(e.getMessage());
        } catch (InstantiationException e) {
            Mensagem.error(e.getMessage());
        } catch (SQLException e) {
            Mensagem.error(e.getMessage());
        }
        return null;
    }

    public boolean salvaConserto() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (conserto.getConsId() == 0) {
                new ConsertoBD().salvaConserto(conserto);
            } else {
                new ConsertoBD().editaConserto(conserto);
            }
            this.conserto = new ConsertoBean();
            if (conserto.getConsId() == 0) {
                Mensagem.add("Conserto inserido com sucesso!");
            } else {
                Mensagem.add("Conserto atualizado com sucesso!");
            }
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;
        } catch (IllegalAccessException e) {
            System.out.println(e);
            return false;
        } catch (InstantiationException e) {
            System.out.println(e);
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public String novoConserto() {
        this.conserto = new ConsertoBean();
        return "novoConserto";
    }

    public String editaConserto() {
        System.out.println("editando!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "novoConserto";
    }

  public boolean excluiConserto() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (conserto.getConsId()!= 0) {
                new ConsertoBD().excluiConserto(conserto);
                Mensagem.add("Conserto Deletado com sucesso!");
                this.conserto = new ConsertoBean();
            } else {
                Mensagem.add("Não é possivel deletar!");
            }
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;
        } catch (IllegalAccessException e) {
            System.out.println(e);
            return false;
        } catch (InstantiationException e) {
            System.out.println(e);
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * @return the conserto
     */
    public ConsertoBean getConserto() {
        return conserto;
    }

    /**
     * @param conserto the conserto to set
     */
    public void setConserto(ConsertoBean conserto) {
        this.conserto = conserto;
    }

}

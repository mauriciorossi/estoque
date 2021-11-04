package com.targettrust.imob.mb;

import com.targettrust.imob.bean.ClienteBean;
import com.targettrust.imob.bean.ValeBean;
import com.targettrust.imob.rn.ClienteBD;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.targettrust.imob.rn.ValeBD;
import com.targettrust.imob.util.Mensagem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ValeMB {

    private ValeBean vale;
    private ClienteBean clienteSelecionado;
    private ClienteBD clienteBD = new ClienteBD();
    private List<ClienteBean> clientes = new ArrayList<ClienteBean>();

    public ValeMB() {
        vale = new ValeBean();
    }

    public List<ValeBean> getLista() {
        try {
            ArrayList<ValeBean> lista = new ArrayList<ValeBean>();
            lista = new ValeBD().listaVales(getVale());
            //getVale().setValeFiltro("");
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

    public boolean salvaVale() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (vale.getValeId() == 0) {
                new ValeBD().salvaVale(getVale(), getClienteSelecionado());
            } else {
                new ValeBD().editaVale(getVale());
            }
            this.vale = new ValeBean();
            if (vale.getValeId() == 0) {
                Mensagem.add("Vale inserido com sucesso!");
            } else {
                Mensagem.add("Vale atualizado com sucesso!");
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

    public String novoVale() {
        this.vale = new ValeBean();
        return "novoVale";
    }

    public String editarVale() {
        System.out.println("editando!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "novoVale";
    }

    public boolean excluiVale() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (vale.getValeId() != 0) {
                new ValeBD().excluiVale(vale);
                Mensagem.add("Vale Deletado com sucesso!");
                this.vale = new ValeBean();
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
     * @return the vale
     */
    public ValeBean getVale() {
        return vale;
    }

    /**
     * @param vale the vale to set
     */
    public void setVale(ValeBean vale) {
        this.vale = vale;
    }

    public ClienteBean getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(ClienteBean clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public List<ClienteBean> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteBean> clientes) {
        this.clientes = clientes;
    }

    public List<ClienteBean> completaNome(String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.clientes = clienteBD.listaTodos();
        List<ClienteBean> sugestoes = new ArrayList<ClienteBean>();
        for (ClienteBean j : this.clientes) {
            if (j.getClienteNome().startsWith(query)) {
                sugestoes.add(j);
            }
        }
        return sugestoes;
    }

}

package com.targettrust.imob.mb;

import com.targettrust.imob.bean.ClienteBean;
import com.targettrust.imob.rn.ClienteBD;
import com.targettrust.imob.util.Mensagem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClienteMB {

    private ClienteBean cliente;
    private ClienteBean clienteSelecionado;
    private ClienteBD clienteBD = new ClienteBD();
    private List<ClienteBean> clientes = new ArrayList<ClienteBean>();

    public ClienteMB() {
        cliente = new ClienteBean();
    }

    public List<ClienteBean> getLista() {
        try {
            ArrayList<ClienteBean> lista = new ArrayList<ClienteBean>();
            lista = new ClienteBD().listaClientes(getCliente());
            //getCliente().setClienteFiltro("");
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

    public boolean salvaCliente() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (cliente.getClienteId() == 0) {
                new ClienteBD().salvaCliente(getCliente());
            } else {
                new ClienteBD().editaCliente(getCliente());
            }
            this.cliente = new ClienteBean();
            if (cliente.getClienteId() == 0) {
                Mensagem.add("Cliente inserido com sucesso!");
            } else {
                Mensagem.add("Cliente atualizado com sucesso!");
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

    public String novoCliente() {
        this.cliente = new ClienteBean();
        return "novoCliente";
    }

    public String editarCliente() {
        System.out.println("editando!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "novoCliente";
    }

    public boolean excluiCliente() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            new ClienteBD().excluiCliente(cliente);
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;
        } catch (InstantiationException e) {
            System.out.println(e);
            return false;
        } catch (IllegalAccessException e) {
            System.out.println(e);
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * @return the cliente
     */
    public ClienteBean getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
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
}

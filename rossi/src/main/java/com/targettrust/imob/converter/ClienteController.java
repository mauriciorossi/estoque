package com.targettrust.imob.converter;

import com.targettrust.imob.bean.ClienteBean;
import com.targettrust.imob.rn.ClienteBD;
import java.util.ArrayList;
import java.util.List; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ClienteController {

    private ClienteBean clienteSelecionado;
    private ClienteBD clienteBD = new ClienteBD();
    private List<ClienteBean> clientes = new ArrayList<ClienteBean>();

    public ClienteBean getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setCleinteSelecionado(ClienteBean clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public List<ClienteBean> getClientes() {
        return clientes;
    }

    public void setJogadores(List<ClienteBean> clientes) {
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

package com.targettrust.imob.mb;

import com.targettrust.imob.bean.PedidoBean;
import com.targettrust.imob.rn.PedidoBD;
import com.targettrust.imob.util.Mensagem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PedidoMB {

    private PedidoBean pedido;

    public PedidoMB() {
        pedido = new PedidoBean();
    }

    public List<PedidoBean> getLista() {
        try {
            ArrayList<PedidoBean> lista = new ArrayList<PedidoBean>();
            lista = new PedidoBD().listaPedidos(getPedido());
            //getPedido().setPedFiltro("");
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

    public boolean salvaPedido() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (pedido.getPedID() == 0) {
                new PedidoBD().salvaPedido(getPedido());
            } else {
                new PedidoBD().editaPedido(getPedido());
            }
            this.pedido = new PedidoBean();
            if (pedido.getPedID() == 0) {
                Mensagem.add("Pedido inserido com sucesso!");
            } else {
                Mensagem.add("Pedido atualizado com sucesso!");
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

    public String novoPedido() {
        this.pedido = new PedidoBean();
        return "novoPedido";
    }

    public String editarPedido() {
        System.out.println("editando!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "novoPedido";
    }

    public boolean excluiPedido() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (pedido.getPedID()!= 0) {
                new PedidoBD().excluiPedido(pedido);
                Mensagem.add("Pedido Deletado com sucesso!");
                this.pedido = new PedidoBean();
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
     * @return the pedido
     */
    public PedidoBean getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(PedidoBean pedido) {
        this.pedido = pedido;
    }

}

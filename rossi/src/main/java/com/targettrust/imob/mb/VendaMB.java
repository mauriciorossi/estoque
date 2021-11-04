package com.targettrust.imob.mb;

import com.targettrust.imob.bean.VendaBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.targettrust.imob.rn.VendaBD;
import com.targettrust.imob.util.Mensagem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import net.sourceforge.barbecue.Barcode;

@ManagedBean
@SessionScoped
public class VendaMB {

    private VendaBean venda;

    public VendaMB() {
        venda = new VendaBean();
    }

    public List<VendaBean> getLista() {
        try {
            ArrayList<VendaBean> lista = new ArrayList<VendaBean>();
            lista = new VendaBD().listaVendas(getVenda());
            //getVenda().setVendaFiltro("");
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

    public boolean salvaVenda() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (venda.getVendaId() == 0) {
                new VendaBD().salvaVenda(getVenda());
            } else {
                new VendaBD().editaVenda(getVenda());
            }
            this.venda = new VendaBean();
            if (venda.getVendaId() == 0) {
                Mensagem.add("Venda inserido com sucesso!");
            } else {
                Mensagem.add("Venda atualizado com sucesso!");
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

    public String novaVenda() {
        this.venda = new VendaBean();
        return "novoVenda";
    }

    public String editarVenda() {
        System.out.println("editando!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "novaVenda";
    }

    public boolean excluiVenda() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (venda.getVendaId() != 0) {
                new VendaBD().excluiVenda(venda);
                Mensagem.add("Venda Deletado com sucesso!");
                this.venda = new VendaBean();
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
     * @return the venda
     */
    public VendaBean getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(VendaBean venda) {
        this.venda = venda;
    }

}

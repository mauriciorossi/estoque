package com.targettrust.imob.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.targettrust.imob.bean.VendedorBean;
import com.targettrust.imob.rn.VendedorBD;
import com.targettrust.imob.util.Mensagem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class VendedorMB {

    private VendedorBean vendedor;

    public VendedorMB() {
        vendedor = new VendedorBean();
    }

    public List<VendedorBean> getListaVendedor() {
        try {
            ArrayList<VendedorBean> lista = new ArrayList<VendedorBean>();
            lista = new VendedorBD().listaVendedores(vendedor);
            //vendedor.setVendFiltro("");
            return lista;
        } catch (ClassNotFoundException e) {
            Mensagem.error(e.getMessage());
        } catch (InstantiationException e) {
            Mensagem.error(e.getMessage());
        } catch (IllegalAccessException e) {
            Mensagem.error(e.getMessage());
        } catch (SQLException e) {
            Mensagem.error(e.getMessage());
        }
        return null;
    }

    public boolean salvaVendedor() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (vendedor.getVendCodigo() == 0) {
                new VendedorBD().salvaVendedor(vendedor);
            } else {
                new VendedorBD().edtiaVendedor(vendedor);
            }
            this.vendedor = new VendedorBean();
            if (vendedor.getVendCodigo() == 0) {
                Mensagem.add("Vendedor inserido com sucesso!");
            } else {
                Mensagem.add("Vendedor atualizado com sucesso!");
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

    public String novoVendedor() {
        this.vendedor = new VendedorBean();
        return "novoVendedor";
    }

    public String editarVendedor() {
        System.out.println("editando!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "novoVendedor";
    }

    public boolean excluiVendedor() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (vendedor.getVendCodigo() != 0) {
                new VendedorBD().excluiVendedor(vendedor);
                Mensagem.add("Vendedor Deletado com sucesso!");
                this.vendedor = new VendedorBean();
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
     * @return the vendedor
     */
    public VendedorBean getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(VendedorBean vendedor) {
        this.vendedor = vendedor;
    }
}

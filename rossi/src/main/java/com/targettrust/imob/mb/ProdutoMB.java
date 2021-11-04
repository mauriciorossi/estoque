package com.targettrust.imob.mb;

import com.targettrust.imob.bean.EtiquetaBean;
import com.targettrust.imob.bean.ProdutoBean;
import com.targettrust.imob.rn.EtiquetasBD;
import com.targettrust.imob.rn.ProdutoBD;
import com.targettrust.imob.util.Mensagem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.sf.jasperreports.engine.JRException;

@ManagedBean
@SessionScoped
public class ProdutoMB {

    private ProdutoBean produto;

    public ProdutoMB() {
        produto = new ProdutoBean();
    }

    public String novoProduto() {
        this.produto = new ProdutoBean();
        return "novoProduto";
    }

    public List<ProdutoBean> getLista() {
        try {
            ArrayList<ProdutoBean> lista = new ArrayList<ProdutoBean>();
            lista = new ProdutoBD().listaProdutos(produto);
            //produto.setProdFiltro("");
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

    public boolean salvarProduto() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (produto.getProdCodigo() == 0) {
                boolean salvou = new ProdutoBD().salvaProduto(produto);
                if (salvou) {
                    Mensagem.add("Produto inserido com sucesso!");
                    this.produto = new ProdutoBean();
                } else {
                    Mensagem.add("Erro ao salvar, verifique os campos!");
                }
            } else {
                new ProdutoBD().editaProduto(produto);
                Mensagem.add("Produto atualizado com sucesso!");
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

    public String editarProduto() {
        return "novoProduto";
    }

    public boolean excluiProduto() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            if (produto.getProdCodigo() != 0) {
                new ProdutoBD().excluiProduto(produto);
                Mensagem.add("Produto Deletado com sucesso!");
                this.produto = new ProdutoBean();
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

    public void salvaEtiquetas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, JRException, Exception {
        try {
            boolean inseriu = new EtiquetasBD().salvaEtiquetas(produto);
            if (inseriu) {
                Mensagem.add("Etiquetas inseridas com sucesso!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
    }

    public String excluiEtiquetas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, JRException {
        try {
            new ProdutoBD().excluiEtiquetas();
            return "";
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return "";
        } catch (InstantiationException e) {
            System.out.println(e);
            return "";
        } catch (IllegalAccessException e) {
            System.out.println(e);
            return "";
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        }
    }

    /**
     * @return the produto
     */
    public ProdutoBean getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(ProdutoBean produto) {
        this.produto = produto;
    }

}

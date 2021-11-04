package com.targettrust.imob.rn;

import com.targettrust.imob.bean.ProdutoBean;
import com.targettrust.imob.conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoBD {

    public ArrayList<ProdutoBean> listaProdutos(ProdutoBean produto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("select prodcodigo, proddescricao, prodvalorunitario, prodreferencia, prodtamanho, prodobservacao, prodquantidadeatualsete, prodquantidadeatualtotal from produto")
                    .append(" WHERE 1 = 1 ");
            if (produto.getProdComboBoxEscolhido() == 1) {
                if (produto.getProdFiltro() != null && !produto.getProdFiltro().equals("")) {
                    sql.append("AND produto.prodreferencia like '%").append(produto.getProdFiltro()).append("%'");
                }
            } else if (produto.getProdComboBoxEscolhido() == 2) {
                if (produto.getProdFiltro() != null && !produto.getProdFiltro().equals("")) {
                    sql.append("AND produto.prodcodigo = ").append(produto.getProdFiltro()).append(" ");
                }
            } else if (produto.getProdComboBoxEscolhido() == 3) {
                if (produto.getProdFiltro() != null && !produto.getProdFiltro().equals("")) {
                    sql.append("AND produto.proddescricao like '%").append(produto.getProdFiltro()).append("%'");
                }
            }
             sql.append(" ORDER BY produto.proddescricao ");
            System.out.println("sql" + sql.toString());
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<ProdutoBean> lista = new ArrayList<ProdutoBean>();
            ProdutoBean prod;
            while (rs.next()) {
                prod = new ProdutoBean();
                prod.setProdCodigo(rs.getInt("prodcodigo"));
                prod.setProdDescricao(rs.getString("proddescricao"));
                prod.setProdValorUnitario(rs.getDouble("prodvalorunitario"));
                prod.setProdReferencia(rs.getString("prodreferencia"));
                prod.setProdTamanho(rs.getString("prodtamanho"));
                prod.setProdObservacao(rs.getString("prodobservacao"));
                prod.setProdQuantidadeAtualSete(rs.getInt("prodquantidadeatualsete"));
                prod.setProdQuantidadeAtualTotal(rs.getInt("prodquantidadeatualtotal"));
                lista.add(prod);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            conn.close();
        }
    }

    public boolean salvaProduto(ProdutoBean produto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("INSERT INTO produto (prodcodigo,proddescricao,prodquantidadeatualsete, prodquantidadeatualtotal, prodvalorunitario,prodreferencia,prodtamanho,prodobservacao) values(?,?,?,?,?,?,?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setInt(i++, 0);
            pstmt.setString(i++, produto.getProdDescricao());
            pstmt.setInt(i++, produto.getProdQuantidadeAtualSete());
            pstmt.setInt(i++, produto.getProdQuantidadeAtualTotal());
            pstmt.setDouble(i++, produto.getProdValorUnitario());
            pstmt.setString(i++, produto.getProdReferencia());
            pstmt.setString(i++, produto.getProdTamanho());
            pstmt.setString(i++, produto.getProdObservacao());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            con.close();
        }
    }
    
    
public boolean editaProduto(ProdutoBean produto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("UPDATE produto SET proddescricao= ?,prodquantidadeatualsete = ?, prodquantidadeatualtotal = ?,prodvalorunitario = ?,prodreferencia = ?,prodtamanho =?, prodobservacao = ? "
                    + "where prodcodigo = ?");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setString(i++, produto.getProdDescricao());
            pstmt.setInt(i++, produto.getProdQuantidadeAtualSete());
            pstmt.setInt(i++, produto.getProdQuantidadeAtualTotal());
            pstmt.setDouble(i++, produto.getProdValorUnitario());
            pstmt.setString(i++, produto.getProdReferencia());
            pstmt.setString(i++, produto.getProdTamanho());
            pstmt.setString(i++, produto.getProdObservacao());
            pstmt.setInt(i++, produto.getProdCodigo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            con.close();
        }
    }
    
    
    public boolean excluiProduto(ProdutoBean produto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("delete from produto where prodcodigo = ? ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, produto.getProdCodigo());            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            con.close();
        }
    }

    public boolean excluiEtiquetas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("delete from etiquetas  ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            con.close();
        }
    }
}

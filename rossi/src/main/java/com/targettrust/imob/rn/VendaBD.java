package com.targettrust.imob.rn;

import com.targettrust.imob.bean.VendaBean;
import com.targettrust.imob.conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendaBD {

    public ArrayList<VendaBean> listaVendas(VendaBean venda) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("select prodcodigo, proddescricao, prodvalorunitario, prodreferencia, prodtamanho, prodobservacao, prodquantidadeatual from produto")
                    .append(" WHERE 1 = 1 ");
            if (venda.getVendaComboBoxEscolhido() == 1) {
                if (venda.getVendaFiltro() != null && !venda.getVendaFiltro().equals("")) {
                    sql.append("AND produto.prodreferencia like '%").append(venda.getVendaFiltro()).append("%'");
                }
            } else if (venda.getVendaComboBoxEscolhido() == 2) {
                if (venda.getVendaFiltro() != null && !venda.getVendaFiltro().equals("")) {
                    sql.append("AND produto.prodcodigo = ").append(venda.getVendaFiltro()).append(" ");
                }
            } else if (venda.getVendaComboBoxEscolhido() == 3) {
                if (venda.getVendaFiltro()!= null && !venda.getVendaFiltro().equals("")) {
                    sql.append("AND produto.proddescricao like '%").append(venda.getVendaFiltro()).append("%'");
                }
            }
               System.out.println("sql"+sql.toString());
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<VendaBean> lista = new ArrayList<VendaBean>();
            VendaBean ven;
            while (rs.next()) {
                ven = new VendaBean();
                ven.setVendaId(rs.getInt("prodcodigo"));
                lista.add(ven);
            }
            return lista;
        } catch (Exception e) {
            return null;
        } finally {
            conn.close();
        }
    }

    public boolean salvaVenda(VendaBean venda) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            System.out.println("chegou aqui cade!");
            StringBuffer sql = new StringBuffer("INSERT INTO produto (prodcodigo,proddescricao,prodquantidadeatual,prodvalorunitario,prodreferencia,prodtamanho,prodobservacao) values(?,?,?,?,?,?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setInt(i++, 0);
            pstmt.setString(i++, venda.getVendaFiltro());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }finally{
            con.close();
        }
    }
    
    public boolean editaVenda(VendaBean venda) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("UPDATE produto SET ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setString(i++, venda.getVendaFiltro());         
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }finally{
            con.close();
        }
    }
    
    
    public boolean excluiVenda(VendaBean venda) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("delete from produto where prodcodigo = ? ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, venda.getVendaFiltro());            
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }finally{
            con.close();
        }
    }
}

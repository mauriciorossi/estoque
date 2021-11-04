package com.targettrust.imob.rn;

import com.targettrust.imob.bean.ConsertoBean;
import com.targettrust.imob.conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConsertoBD {

    public ArrayList<ConsertoBean> listaConsertos(ConsertoBean conserto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = ConexaoDB.getConnection();
        try {
                StringBuffer sql = new StringBuffer("select consID , consDataEntrega, consData, vendNome, consDescricao, produtoDescricao, clienteNome from conserto"
                        + " join produto on produto.produtocodigo = conserto.produtocodigo"
                        + " join cliente on cliente.clienteId = conserto.clienteId "
                        + " join vendedor on vendedor.vendId = conserto.vendId ")
                    .append(" WHERE 1 = 1 ");
            if (conserto.getConsComboBoxEscolhido() == 1) {
                if (conserto.getConsFiltro() != null && !conserto.getConsFiltro().equals("")) {
                    sql.append("AND consData like '%").append(conserto.getConsFiltro()).append("%'");
                }
            } else if (conserto.getConsComboBoxEscolhido() == 2) {
                if (conserto.getConsFiltro() != null && !conserto.getConsFiltro().equals("")) {
                    sql.append("AND consID = ").append(conserto.getConsFiltro()).append(" ");
                }
            } else if (conserto.getConsComboBoxEscolhido() == 3) {
                if (conserto.getConsFiltro() != null && !conserto.getConsFiltro().equals("")) {
                    sql.append("AND clienteNome like '%").append(conserto.getConsFiltro()).append("%'");
                }
            }
               System.out.println("sql"+sql.toString());
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<ConsertoBean> lista = new ArrayList<ConsertoBean>();
            ConsertoBean cons;
            while (rs.next()) {
                cons = new ConsertoBean();
                cons.setConsId(rs.getInt("prodcodigo"));
                cons.setConsData(rs.getDate("consDataEntrega"));
                cons.setConsData(rs.getDate("consData"));
                cons.setVendNome(rs.getString("vendNome"));
                cons.setConsDescricao(rs.getString("consDescricao"));
                cons.setProdDescricao(rs.getString("produtoDescricao"));
                cons.setClienteNome(rs.getString("clienteNome"));
                lista.add(cons);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            conn.close();
        }
    }

    public boolean salvaConserto(ConsertoBean conserto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            System.out.println("chegou aqui cade!");
            StringBuffer sql = new StringBuffer("INSERT INTO conserto (consID ,consData, consDataEntrega vendId, prodId, consDescricao) values(?,?,?,?,?,?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setInt(i++, 0);
            pstmt.setDate(i++, conserto.getConsData());
            pstmt.setDate(i++, conserto.getConsDataEntrega());
            pstmt.setInt(i++, conserto.getVendId());
            pstmt.setInt(i++, conserto.getProdutoId());
            pstmt.setString(i++, conserto.getConsDescricao());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            con.close();
        }
    }
    
    public boolean editaConserto(ConsertoBean conserto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("UPDATE conserto SET consData, consDataEntrega vendId, prodId, consDescricao");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setDate(i++, conserto.getConsData());
            pstmt.setDate(i++, conserto.getConsDataEntrega());
            pstmt.setInt(i++, conserto.getVendId());
            pstmt.setInt(i++, conserto.getProdutoId());
            pstmt.setString(i++, conserto.getConsDescricao());
            pstmt.setInt(i++, conserto.getConsId());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            con.close();
        }
    }
    
    
    public boolean excluiConserto(ConsertoBean conserto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("delete from conserto where consID = ? ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, conserto.getConsId());            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            con.close();
        }
    }
}

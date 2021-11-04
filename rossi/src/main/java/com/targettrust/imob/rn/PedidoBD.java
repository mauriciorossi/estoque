package com.targettrust.imob.rn;

import com.targettrust.imob.bean.PedidoBean;
import com.targettrust.imob.conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoBD {

    public ArrayList<PedidoBean> listaPedidos(PedidoBean pedido) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("select pediD, produto.prodCodigo, pedData, clienteNome, prodDescricao, vendNome, pedEntregue from pedido"
                    + " join produto on produto.prodcodigo = pedido.prodcodigo "
                    + " join cliente on cliente.clienteId = pedido.clienteId "
                    + " join vendedor on vendedor.vendId = pedido.vendId ")
                    .append(" WHERE 1 = 1 ");
            if (pedido.getPedComboBoxEscolhido() == 1) {
                if (pedido.getPedFiltro() != null && !pedido.getPedFiltro().equals("")) {
                    sql.append("AND clienteNome like '%").append(pedido.getPedFiltro()).append("%'");
                }
            } else if (pedido.getPedComboBoxEscolhido() == 2) {
                if (pedido.getPedFiltro() != null && !pedido.getPedFiltro().equals("")) {
                    sql.append("AND pediD = ").append(pedido.getPedFiltro()).append(" ");
                }
            }
            System.out.println("sql"+sql.toString());
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<PedidoBean> lista = new ArrayList<PedidoBean>();
            PedidoBean ped;
            while (rs.next()) {
                ped = new PedidoBean();
                ped.setPedID(rs.getInt("pediD"));
                ped.setProdutoId(rs.getInt("prodcodigo"));
                ped.setPedData(rs.getDate("pedData"));
                ped.setClienteNome(rs.getString("clienteNome"));
                ped.setProdDescricao(rs.getString("prodDescricao"));
                ped.setVendNome(rs.getString("vendNome"));
                ped.setPedInformado(rs.getBoolean("pedInformado"));
                lista.add(ped);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            conn.close();
        }
    }

    public boolean salvaPedido(PedidoBean pedido) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            System.out.println("chegou aqui cade!");
            StringBuffer sql = new StringBuffer("INSERT INTO pedido (pedId, clienteId, produtoId, pedData, pedInformado, vendId) values(?,?,?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setInt(i++, 0);
            pstmt.setInt(i++, pedido.getClienteId());
            pstmt.setInt(i++, pedido.getProdutoId());
            pstmt.setDate(i++, pedido.getPedData());
            pstmt.setBoolean(i++, pedido.isPedInformado());
            pstmt.setInt(i++, pedido.getVendID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            con.close();
        }
    }
    
    public boolean editaPedido(PedidoBean pedido) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("UPDATE pedido SET clienteId = ?, produtoId = ?, pedData = ?, pedInformado = ?, vendId = ?"
                    + "where pediId = ?");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setInt(i++, pedido.getClienteId());
            pstmt.setInt(i++, pedido.getProdutoId());
            pstmt.setDate(i++, pedido.getPedData());
            pstmt.setBoolean(i++, pedido.isPedInformado());
            pstmt.setInt(i++, pedido.getVendID());
            pstmt.setInt(i++, pedido.getPedID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            con.close();
        }
    }
    
    
    public boolean excluiPedido(PedidoBean pedido) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("delete from pedido where pedID = ? ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, pedido.getPedID());            
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

package com.targettrust.imob.rn;

import com.targettrust.imob.bean.ClienteBean;
import com.targettrust.imob.bean.ValeBean;
import com.targettrust.imob.conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ValeBD {

    public ArrayList<ValeBean> listaVales(ValeBean vale) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("select valeId, valeValor, valeData, valeDataUtilizacao, cliente.clienteNome from vale "
                    + " join cliente on cliente.clienteId = vale.clienteId ")
                    .append(" WHERE 1 = 1 ");
            if (vale.getValeComboBoxEscolhido() == 1) {
                if (vale.getValeFiltro() != null && !vale.getValeFiltro().equals("")) {
                    sql.append("AND clienteNome like '%").append(vale.getValeFiltro()).append("%'");
                }
            } else if (vale.getValeComboBoxEscolhido() == 2) {
                if (vale.getValeFiltro() != null && !vale.getValeFiltro().equals("")) {
                    sql.append("AND valeId = ").append(vale.getValeFiltro()).append(" ");
                }
            }
            System.out.println("sql" + sql.toString());
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<ValeBean> lista = new ArrayList<ValeBean>();
            ValeBean val;
            while (rs.next()) {
                val = new ValeBean();
                val.setValeId(rs.getInt("valeId"));
                val.setValeValor(rs.getDouble("valeValor"));
                val.setValeData(rs.getDate("valeData"));
                val.setValeDataUtilizacao(rs.getDate("valeDataUtilizacao"));
                val.setValeNomeCliente(rs.getString("clienteNome"));
                lista.add(val);
            }
            return lista;
        } catch (SQLException e) {
            return null;
        } finally {
            conn.close();
        }
    }

    public boolean salvaVale(ValeBean vale, ClienteBean cliente) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            System.out.println("chegou aqui cade!");
            StringBuffer sql = new StringBuffer("INSERT INTO vale (valeid, clienteId, valeValor, valeData, valeDataUtilizacao) values(?,?,?,?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setInt(i++, 0);
            pstmt.setInt(i++, cliente.getClienteId());
            pstmt.setDouble(i++, vale.getValeValor());
            pstmt.setDate(i++, new java.sql.Date(vale.getValeDataUtilizacao().getTime()));
            pstmt.setDate(i++, new java.sql.Date(vale.getValeDataUtilizacao().getTime()));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            con.close();
        }
    }

    public boolean editaVale(ValeBean vale) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("UPDATE vale SET valeid = ?, clienteId = ?, valeValor = ?, valeData = ?, valeDataUtilizacao = ? "
                    + "where valeID = ?");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setInt(i++, vale.getClienteId());
            pstmt.setDouble(i++, vale.getValeValor());
            pstmt.setDate(i++, (Date) vale.getValeData());
            pstmt.setDate(i++, (Date) vale.getValeDataUtilizacao());
            pstmt.setInt(i++, vale.getValeId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            con.close();
        }
    }

    public boolean excluiVale(ValeBean vale) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("delete from vale where valeId = ? ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, vale.getValeId());
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        } finally {
            con.close();
        }
    }
}

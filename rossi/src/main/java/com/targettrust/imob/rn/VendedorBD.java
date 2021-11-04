package com.targettrust.imob.rn;

import com.targettrust.imob.bean.VendedorBean;
import com.targettrust.imob.conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendedorBD {

    public ArrayList<VendedorBean> listaVendedores(VendedorBean vendedor) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("select vendId , vendNome , vendTelefone , vendCelular  from vendedores ")
                    .append(" WHERE 1 = 1 ");
            if (vendedor.getVendComboBoxEscolhido() == 1) {
                if (vendedor.getVendFiltro() != null && !vendedor.getVendFiltro().equals("")) {
                    sql.append("AND vendedores.vendNome like '%").append(vendedor.getVendFiltro()).append("%'");
                }
            } else if (vendedor.getVendComboBoxEscolhido() == 2) {
                if (vendedor.getVendFiltro() != null && !vendedor.getVendFiltro().equals("")) {
                    sql.append("AND vendedores.vendId = ").append(vendedor.getVendFiltro()).append(" ");
                }
            } 
             System.out.println("sql"+sql.toString());
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<VendedorBean> lista = new ArrayList<VendedorBean>();
            VendedorBean vendedores;
            while (rs.next()) {
                vendedores = new VendedorBean();
                vendedores.setVendCodigo(rs.getInt("vendId "));
                vendedores.setVendNome(rs.getString("vendNome"));
                vendedores.setVendTelefone(rs.getString("vendTelefone"));
                vendedores.setVendCelular(rs.getString("vendCelular"));
                lista.add(vendedores);
            }
            return lista;
        } catch (SQLException e) {
            return null;
        } finally {
            conn.close();
        }
    }

    public boolean salvaVendedor(VendedorBean vendedor) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            System.out.println("chegou aqui cade!");
            StringBuffer sql = new StringBuffer("INSERT INTO vendedor (vendId,vendNome,vendTelefone,vendCelular) values(?,?,?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setInt(i++, 0);
            pstmt.setString(i++, vendedor.getVendNome());
            pstmt.setString(i++, vendedor.getVendTelefone());
            pstmt.setString(i++, vendedor.getVendCelular());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }finally{
            con.close();
        }
    }
    
    public boolean edtiaVendedor(VendedorBean vendedor) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("UPDATE vendedor SET vendNome = ?, vendTelefone = ?, vendCelular = ?"
                    + "where vendId = ?");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setString(i++, vendedor.getVendNome());
            pstmt.setString(i++, vendedor.getVendTelefone());
            pstmt.setString(i++, vendedor.getVendCelular());
            pstmt.setInt(i++, vendedor.getVendCodigo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }finally{
            con.close();
        }
    }
    
    
    public boolean excluiVendedor(VendedorBean vendedor) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("delete from vendedor where vendId = ? ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, vendedor.getVendCodigo());            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }finally{
            con.close();
        }
    }
}

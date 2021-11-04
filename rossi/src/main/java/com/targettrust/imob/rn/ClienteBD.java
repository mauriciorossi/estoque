package com.targettrust.imob.rn;

import com.targettrust.imob.bean.ClienteBean;
import com.targettrust.imob.conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteBD {

    public ArrayList<ClienteBean> listaClientes(ClienteBean cliente) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("select clienteId , clienteNome , clienteCPF , clienteEndereco , clienteTelefone , clienteCelular , clienteEmail  from cliente ")
                    .append(" WHERE 1 = 1 ");
            if (cliente.getClienteComboBoxEscolhido() == 1) {
                if (cliente.getClienteFiltro() != null && !cliente.getClienteFiltro().equals("")) {
                    sql.append("AND clienteNome like '%").append(cliente.getClienteFiltro()).append("%'");
                }
            } else if (cliente.getClienteComboBoxEscolhido() == 2) {
                if (cliente.getClienteFiltro() != null && !cliente.getClienteFiltro().equals("")) {
                    sql.append("AND clienteCPF = ").append(cliente.getClienteFiltro()).append(" ");
                }
            } else if (cliente.getClienteComboBoxEscolhido() == 3) {
                if (cliente.getClienteFiltro() != null && !cliente.getClienteFiltro().equals("")) {
                    sql.append("AND clienteEmail like '%").append(cliente.getClienteFiltro()).append("%'");
                }
            }
            System.out.println("sql" + sql.toString());
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<ClienteBean> lista = new ArrayList<ClienteBean>();
            ClienteBean cli;
            while (rs.next()) {
                cli = new ClienteBean();
                cli.setClienteId(rs.getInt("clienteId"));
                cli.setClienteNome(rs.getString("clienteNome"));
                cli.setClienteCPF(rs.getString("clienteCPF"));
                cli.setClienteEndereco(rs.getString("clienteEndereco"));
                cli.setClienteTelefone(rs.getString("clienteTelefone"));
                cli.setClienteCelular(rs.getString("clienteCelular"));
                cli.setClienteEmail(rs.getString("clienteEmail"));
                lista.add(cli);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            conn.close();
        }
    }

    public boolean salvaCliente(ClienteBean cliente) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            System.out.println("chegou aqui cade!");
            StringBuffer sql = new StringBuffer("INSERT INTO cliente (clienteId ,clienteNome ,clienteCPF ,clienteEndereco ,clienteTelefone ,clienteCelular ,clienteEmail ) values(?,?,?,?,?,?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setInt(i++, 0);
            pstmt.setString(i++, cliente.getClienteNome());
            pstmt.setString(i++, cliente.getClienteCPF());
            pstmt.setString(i++, cliente.getClienteEndereco());
            pstmt.setString(i++, cliente.getClienteTelefone());
            pstmt.setString(i++, cliente.getClienteCelular());
            pstmt.setString(i++, cliente.getClienteEmail());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            con.close();
        }
    }

    public boolean editaCliente(ClienteBean cliente) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("UPDATE cliente SET clienteNome = ? ,clienteCPF = ?, clienteEndereco = ?, clienteTelefone = ?, clienteCelular = ?, clienteEmail = ? "
                    + " where clienteId = ?");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int i = 1;
            pstmt.setString(i++, cliente.getClienteNome());
            pstmt.setString(i++, cliente.getClienteCPF());
            pstmt.setString(i++, cliente.getClienteEndereco());
            pstmt.setString(i++, cliente.getClienteTelefone());
            pstmt.setString(i++, cliente.getClienteCelular());
            pstmt.setString(i++, cliente.getClienteEmail());
            pstmt.setInt(i++, cliente.getClienteId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            con.close();
        }
    }

    public boolean excluiCliente(ClienteBean cliente) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("delete from cliente where clienteId = ? ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, cliente.getClienteId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            con.close();
        }
    }

    public ClienteBean buscaPorId(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClienteBean cliente = new ClienteBean();
        Connection con = ConexaoDB.getConnection();
        String sql = "select clienteId, clienteNome from cliente where clienteId = ?";
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente.setClienteId(rs.getInt("clienteId"));
                cliente.setClienteNome(rs.getString("clienteNome"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return cliente;
    }

    public List<ClienteBean> listaTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<ClienteBean> clientes = new ArrayList<ClienteBean>();
        try {
            Connection con = ConexaoDB.getConnection();
            String sql = "select clienteId, clienteNome from cliente";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ClienteBean c = new ClienteBean();
                c.setClienteId(rs.getInt("clienteId"));
                c.setClienteNome(rs.getString("clienteNome"));
                clientes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return clientes;
    }
}

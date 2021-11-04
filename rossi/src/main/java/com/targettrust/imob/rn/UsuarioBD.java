package com.targettrust.imob.rn;

import com.targettrust.imob.bean.UsuarioBean;
import com.targettrust.imob.conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioBD {

    public boolean consultaUsuarioBD(UsuarioBean usuario) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection conn = ConexaoDB.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from usuario where lower(usuUsuario) = ? and lower(usuSenha) = ? ");
            int i = 1;
            pstmt.setString(i++, usuario.getUsuario().toLowerCase());
            pstmt.setString(i++, usuario.getSenha());

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                System.out.println("ok  ");
                pstmt.close();
                return true;
            } else {
                pstmt.close();
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            conn.close();
        }
    }
}

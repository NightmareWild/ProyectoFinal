/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.EArtista;
import pojo.EUsuario;

/**
 *
 * @author Nicolas Peralta
 */
public class DaoUsuario {
 
    private final String db = "jdbc:postgresql://localhost:5432/disquera"; 
    private final String usuarioP = "postgres";
    private final String contrasenaServer = "admin";
    
    public Boolean agregarUsuario(EUsuario usuario){    
        try {
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.insertusuario(?,?)}");
            procedimiento.setString(1, usuario.getUsuario());
            procedimiento.setString(2, usuario.getClave());
            ResultSet rs = procedimiento.executeQuery();
            while(rs.next()){
                return rs.getBoolean(1);
            }
            conectar.close();
        } catch (SQLException ex) {
            System.out.println("Conexion no  exitosa" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    public List<EUsuario> login(EUsuario usuario){    
        try {
            List<EUsuario> usuarios = new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.f_login(?,?)}");
            procedimiento.setString(1, usuario.getUsuario());
            procedimiento.setString(2, usuario.getClave());
            ResultSet rs = procedimiento.executeQuery();
            while(rs.next()){
                EUsuario artista = new EUsuario();
                artista.setId( rs.getInt("user_id"));
                artista.setUsuario(rs.getString("user_name"));
                artista.setClave(rs.getString("contrasena"));
                artista.setRol(rs.getString("rol"));
                usuarios.add(artista);
            }
            conectar.close();
            return usuarios;
        } catch (SQLException ex) {
            System.out.println("Conexion no  exitosa" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

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
import pojo.EAlbum;
import pojo.ECancion;

/**
 *
 * @author Nicolas Peralta
 */
public class DaoCompra {
    
    private final String db = "jdbc:postgresql://localhost:5432/disquera"; 
    private final String usuarioP = "postgres";
    private final String contrasenaServer = "admin";
    
    public List<EAlbum> listaAlbumnes(){    
        try {
            List<EAlbum> albumnes = new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.f_listaalbumnescompra()}");
            ResultSet rs = procedimiento.executeQuery();
            while(rs.next()){
                EAlbum album = new EAlbum();
                album.setId( rs.getInt("album_id"));
                album.setNombre(rs.getString("nombre"));                               
                album.setIdArtista(rs.getInt("idartista"));
                album.setCaratula(rs.getString("caratula")); 
                album.setValor(rs.getInt("valor"));
                albumnes.add(album);
            }
            conectar.close();
            return albumnes;
        } catch (SQLException ex) {
            System.out.println("Conexion no  exitosa" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Boolean comprarCancion(int id){    
        try {
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.f_insertcompracancion(?)}");
            procedimiento.setInt(1, id);
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
    
    public Boolean comprarAlbum(int id){    
        try {
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.f_insertcompraalbum(?)}");
            procedimiento.setInt(1, id);
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
}

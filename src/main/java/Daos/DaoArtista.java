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
import pojo.EArtista;
import pojo.ECancion;

/**
 *
 * @author Nicolas Peralta
 */
public class DaoArtista {
    
    private final String db = "jdbc:postgresql://localhost:5432/disquera"; 
    private final String usuarioP = "postgres";
    private final String contrasenaServer = "admin";
    
    public Boolean agregarArtista(EArtista artista){    
        try {
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.insertartista(?,?,?,?)}");
            procedimiento.setString(1, artista.getNombre());
            procedimiento.setString(2, artista.getGenero());
            procedimiento.setDate(3, artista.getFechaNacimiento());
            procedimiento.setString(4, artista.getFoto());
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
    
    public List<EArtista> listaArtistas(){    
        try {
            List<EArtista> artistas = new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.f_listaartista()}");
            ResultSet rs = procedimiento.executeQuery();
            while(rs.next()){
                EArtista artista = new EArtista();
                artista.setId( rs.getInt("artista_id"));
                artista.setNombre(rs.getString("nombre"));
                artista.setGenero(rs.getString("genero"));                
                artista.setFechaNacimiento(rs.getDate("fecha"));
                artista.setFoto(rs.getString("foto"));
                artistas.add(artista);
            }
            conectar.close();
            return artistas;
        } catch (SQLException ex) {
            System.out.println("Conexion no  exitosa" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Boolean agregarAlbum(EAlbum album){    
        try {
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.f_insertalbum(?,?,?,?)}");
            procedimiento.setString(1, album.getNombre());
            procedimiento.setInt(2, album.getIdArtista());
            procedimiento.setString(3, album.getCaratula());
            procedimiento.setInt(4, album.getValor());
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
    
    public List<EAlbum> listaAlbumnes(int id){    
        try {
            List<EAlbum> albumnes = new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.f_listaalbumnes(?)}");
            procedimiento.setInt(1, id);
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
    
    public Boolean agregarCancion(ECancion cancion){    
        try {
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.f_insertcancion(?,?,?)}");
            procedimiento.setString(1, cancion.getNombre());
            procedimiento.setInt(2, cancion.getIdAlbum());
            procedimiento.setInt(3, cancion.getValor());
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
    
    public List<ECancion> listaCanciones(int id){    
        try {
            List<ECancion> canciones = new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            Connection conectar = DriverManager.getConnection(db, usuarioP, contrasenaServer);
            CallableStatement procedimiento = conectar.prepareCall("{call public.f_listacanciones(?)}");
            procedimiento.setInt(1, id);
            ResultSet rs = procedimiento.executeQuery();
            while(rs.next()){
                ECancion cancion = new ECancion();
                cancion.setId( rs.getInt("cancion_id"));
                cancion.setNombre(rs.getString("nombre")); 
                cancion.setValor(rs.getInt("valor"));
                cancion.setIdAlbum(rs.getInt("album_id"));                 
                canciones.add(cancion);
            }
            conectar.close();
            return canciones;
        } catch (SQLException ex) {
            System.out.println("Conexion no  exitosa" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

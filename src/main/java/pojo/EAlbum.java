/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Nicolas Peralta
 */
public class EAlbum {
    
    private int id;
    private String nombre;
    private int idArtista;
    private String caratula;
    private int valor;

    public EAlbum(String nombre, int idArtista, String caratula, int valor) {
        this.nombre = nombre;
        this.idArtista = idArtista;
        this.caratula = caratula;
        this.valor = valor;
    }    
    
    public EAlbum() {
    } 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}

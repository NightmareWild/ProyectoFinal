/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.file.UploadedFile;
import pojo.EAlbum;

/**
 *
 * @author Nicolas Peralta
 */
@Named(value = "compraDiscoController")
@RequestScoped
public class compraDiscoController {

    private int id;
    private String nombre;
    private UploadedFile caratula;
    private int valor;
    private EAlbum album;
    private List<EAlbum> albumnes;
    
    @PostConstruct
    public void init() {
        albumnes = new Daos.DaoCompra().listaAlbumnes(); 
    }
    
    public void cancion(int id) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("idAlbumCompra",id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("compraCanciones.xhtml");
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

    public UploadedFile getCaratula() {
        return caratula;
    }

    public void setCaratula(UploadedFile caratula) {
        this.caratula = caratula;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public EAlbum getAlbum() {
        return album;
    }

    public void setAlbum(EAlbum album) {
        this.album = album;
    }

    public List<EAlbum> getAlbumnes() {
        return albumnes;
    }

    public void setAlbumnes(List<EAlbum> albumnes) {
        this.albumnes = albumnes;
    }
    
    
    
}

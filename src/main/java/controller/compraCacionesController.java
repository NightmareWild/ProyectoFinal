/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import pojo.ECancion;

/**
 *
 * @author Nicolas Peralta
 */
@Named(value = "compraCacionesController")
@RequestScoped
public class compraCacionesController {

    private String nombre;
    private int valor;
    private ECancion cancion;
    private List<ECancion> canciones;
    private int idAlbum;
    
    @PostConstruct
    public void init() {
        this.idAlbum = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idAlbumCompra");
        canciones = new Daos.DaoArtista().listaCanciones(this.idAlbum); 
    }
    
}

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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import pojo.ECancion;

/**
 *
 * @author Nicolas Peralta
 */
@Named(value = "listaCancionesController")
@RequestScoped
public class listaCancionesController {

    private String nombre;
    private int valor;
    private ECancion cancion;
    private List<ECancion> canciones;
    private int idAlbum;
    
    @PostConstruct
    public void init() {
        this.idAlbum = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idAlbum");
        canciones = new Daos.DaoArtista().listaCanciones(this.idAlbum); 
    }
    
    public void ingresarCancion() throws IOException{
        ECancion cancionI = new ECancion(valor, nombre, valor, this.idAlbum);
        FacesContext context = FacesContext.getCurrentInstance(); 
        boolean registro = new Daos.DaoArtista().agregarCancion(cancionI);
        if (registro == true){          
            init();
            context.addMessage(null, new FacesMessage("Canción registrado")); 
        }else {  
            context.addMessage(null, new FacesMessage("Canción ya existente"));
        } 
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public ECancion getCancion() {
        return cancion;
    }

    public void setCancion(ECancion cancion) {
        this.cancion = cancion;
    }

    public List<ECancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<ECancion> canciones) {
        this.canciones = canciones;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
    
    @Inject
    private loginSession rolSession; 
    
    @PostConstruct
    public void init() {
        if (rolSession.getRol().equals("Usuario")){
            this.idAlbum = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idAlbumCompra");
        canciones = new Daos.DaoArtista().listaCanciones(this.idAlbum); 
        }else{
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/mavenproject3/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(listaArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public void comprarCancion(int id) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance().getCurrentInstance();
        boolean entrada = new Daos.DaoCompra().comprarCancion(id);
        if (entrada == true){         
            context.addMessage(null, new FacesMessage("Canción comprada"));            
        }else {  
            context.addMessage(null, new FacesMessage("No se pudo comprar la canción"));
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

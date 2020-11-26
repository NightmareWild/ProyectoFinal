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
import org.primefaces.event.RowEditEvent;
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
    
    @Inject
    private loginSession rolSession; 
    
    @PostConstruct
    public void init() {
        if (rolSession.getRol().equals("Administrador")){
            this.idAlbum = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idAlbum");
            canciones = new Daos.DaoArtista().listaCanciones(this.idAlbum); 
        }else{
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/mavenproject3/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(listaArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void ingresarCancion() throws IOException{
        System.out.println(this.idAlbum);
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
    
    public void onRowEdit(RowEditEvent<ECancion> event) {        
        boolean entrada = new Daos.DaoArtista().editarCancion(event.getObject());        
        if (entrada == true){
            FacesMessage msg = new FacesMessage("Canción editada");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            FacesMessage msg = new FacesMessage("Canción no editada");
            FacesContext.getCurrentInstance().addMessage(null, msg);
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

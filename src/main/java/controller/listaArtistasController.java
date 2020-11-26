/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;
import pojo.EArtista;

/**
 *
 * @author Nicolas Peralta
 */
@Named(value = "listaArtistasController")
@ViewScoped
public class listaArtistasController implements Serializable{
    
    private List<EArtista> artistas;
    private EArtista artista;
    private int idArtista;
    private Date fecha;
    
    @Inject
    private loginSession rolSession; 
    
    @PostConstruct
    public void init() {
        if (rolSession.getRol().equals("Administrador")){
            artistas = new Daos.DaoArtista().listaArtistas();
        }else{
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/mavenproject3/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(listaArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public void discos(int id) throws IOException{         
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("idArtista",id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("listaDisco.xhtml");
    }
    
    public void onRowEdit(RowEditEvent<EArtista> event) {
        boolean entrada = new Daos.DaoArtista().editarArtista(event.getObject());        
        if (entrada == true){
            FacesMessage msg = new FacesMessage("Artista editado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            FacesMessage msg = new FacesMessage("Artista no editado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }        
        
    }
        
    public List<EArtista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<EArtista> artistas) {
        this.artistas = artistas;
    }

    public EArtista getArtista() {
        return artista;
    }

    public void setArtista(EArtista artista) {
        this.artista = artista;
    }      
}

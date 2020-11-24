/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
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
    
    @PostConstruct
    public void init() {
        artistas = new Daos.DaoArtista().listaArtistas();
    }

    public void discos(int id) throws IOException{        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("idArtista",id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("listaDisco.xhtml");
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

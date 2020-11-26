/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.file.UploadedFile;
import pojo.EArtista;

/**
 *
 * @author Nicolas Peralta
 */
@Named(value = "registroArtista")
@ViewScoped
public class registroArtista implements Serializable{

    private UploadedFile foto;
    private String nombre;
    private String genero;
    private Date fechaNacimiento;
    private String url = "D:\\Linea de Profundización\\mavenproject3\\src\\main\\webapp\\assets\\";
    
    @Inject
    private loginSession rolSession; 
    
    @PostConstruct
    public void init() {
        if (rolSession.getRol().equals("Administrador")){
        }else{
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/mavenproject3/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(listaArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    public void registro() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance().getCurrentInstance();
        copyFile(foto.getFileName(), foto.getInputStream());
        EArtista artista = new EArtista(nombre, genero, sqlDate(fechaNacimiento), foto.getFileName());
        boolean entrada = new Daos.DaoArtista().agregarArtista(artista);
        if (entrada == true){         
            context.addMessage(null, new FacesMessage("Artista registrado"));            
        }else {  
            context.addMessage(null, new FacesMessage("Artista ya existente"));
        }
    }
    
    public void copyFile(String fileName, InputStream in) {
       try {           
           OutputStream out = new FileOutputStream(new File(this.url + fileName));
           int read = 0;
           byte[] bytes = new byte[1024];
           while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
           }
           in.close();
           out.flush();
           out.close();
       } catch (IOException e) {
          System.out.println(e.getMessage());
       }
    }
   
    public java.sql.Date sqlDate(java.util.Date calendarDate) {
        return new java.sql.Date(calendarDate.getTime());
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}

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
import org.primefaces.model.file.UploadedFile;
import pojo.EAlbum;
import pojo.EArtista;

/**
 *
 * @author Nicolas Peralta
 */
@Named(value = "listaDiscoController")
@RequestScoped
public class listaDiscoController {
    
    private int id;
    private String nombre;
    private UploadedFile caratula;
    private int valor;
    private EAlbum album;
    private List<EAlbum> albumnes;
    private int idArtista;
    private final String url = "D:\\Linea de Profundización\\mavenproject3\\src\\main\\webapp\\assets\\";
    
    @Inject
    private loginSession rolSession; 
    
    @PostConstruct
    public void init() {
        if (rolSession.getRol().equals("Administrador")){
            this.idArtista = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idArtista");
            albumnes = new Daos.DaoArtista().listaAlbumnes(this.idArtista); 
        }else{
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/mavenproject3/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(listaArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void cancion(int id) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("idAlbum",id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("listaCanciones.xhtml");
    }
    
    public void ingresarAlbum() throws IOException{
        EAlbum albumI = new EAlbum(nombre, this.idArtista, caratula.getFileName(), valor);
        FacesContext context = FacesContext.getCurrentInstance(); 
        copyFile(caratula.getFileName(), caratula.getInputStream());
        boolean registro = new Daos.DaoArtista().agregarAlbum(albumI);
        if (registro == true){           
            init();            
            context.addMessage(null, new FacesMessage("Album registrado"));
        }else {  
            context.addMessage(null, new FacesMessage("Album ya existente"));
        }   
    }
    
    public void onRowEdit(RowEditEvent<EAlbum> event) {        
        boolean entrada = new Daos.DaoArtista().editarAlbum(event.getObject());        
        if (entrada == true){
            FacesMessage msg = new FacesMessage("Album editado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            FacesMessage msg = new FacesMessage("Album no editado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
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

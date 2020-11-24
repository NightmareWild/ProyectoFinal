/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import pojo.EUsuario;

/**
 *
 * @author Nicolas Peralta
 */
@Named(value = "login")
@RequestScoped
public class login implements Serializable {

    private String nombre;    
    private String clave;
    
    public void logeo() throws IOException {
        EUsuario usuario = new EUsuario(nombre, clave);
        FacesContext context = FacesContext.getCurrentInstance(); 
        List<EUsuario> lista = new Daos.DaoUsuario().login(usuario);
        if(lista.get(0).getRol().equals("Administrador")) {
            context.addMessage(null, new FacesMessage("Éxito",  "Bienvenido " + usuario.getUsuario()));            
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/listaArtistas.xhtml");
        } else if (lista.get(0).getRol().equals("Usuario")){
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/compraDisco.xhtml");
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",  "Usuario o Contraseña incorrecto") );
        } 
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    public login() {
    }
    
}

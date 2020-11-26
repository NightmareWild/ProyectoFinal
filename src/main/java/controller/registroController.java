/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Daos.DaoUsuario;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import pojo.EUsuario;

/**
 *
 * @author Nicolas Peralta
 */
@Named(value = "registroController")
@ViewScoped
public class registroController implements Serializable{

    private String nombre;
    private String clave;
    
    public void registro() throws IOException{        
        FacesContext context = FacesContext.getCurrentInstance().getCurrentInstance();
        EUsuario usuario = new EUsuario(nombre, clave);
        boolean entrada = new DaoUsuario().agregarUsuario(usuario);       
        if (entrada == true){         
            context.addMessage(null, new FacesMessage("Usuario registrado"));
            
        }else {  
            context.addMessage(null, new FacesMessage("Usuario ya existente"));
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
    
    
    
}

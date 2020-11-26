/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Nicolas Peralta
 */
@Named(value = "loginSession")
@SessionScoped
public class loginSession implements Serializable {
    
    private String rol;
    /**
     * Creates a new instance of loginSession
     */
    public loginSession() {
        this.rol = "vacio";
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}

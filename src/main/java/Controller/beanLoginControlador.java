/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import Model.Usuario;
import Model.UsuarioDB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Oscar0
 */
@Named(value = "loginControlador")
@SessionScoped
public class beanLoginControlador implements Serializable {
       
    String UsuarioIngresado;
    String Password;
    Usuario Usuario1;

     /**
     * Creates a new instance of LoginControlador
     */
    public beanLoginControlador() {
    }
    
    public String getUsuarioIngresado() {
        return UsuarioIngresado;
    }

    public void setUsuarioIngresado(String Usuario) {
        this.UsuarioIngresado = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
   public void autenticar(){
       try{
           Usuario1=UsuarioDB.Autenticar(this.getUsuarioIngresado(), this.getPassword());
           
           if (Usuario1 != null){
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario",UsuarioIngresado);
               FacesContext.getCurrentInstance().getExternalContext().redirect("PagPrincipal.xhtml");
           }
       }catch (Exception e){
       
       }
       
   }
    
}


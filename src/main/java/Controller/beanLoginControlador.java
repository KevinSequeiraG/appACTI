/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import Model.Usuario;
import Model.UsuarioDB;
import java.sql.SQLException;
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
    String mensaje;
    
    UsuarioDB user = new UsuarioDB();

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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
   public void autenticar(){
       try{
           if (this.getUsuarioIngresado().equals("")) {
            mensaje = "Usuario o Contraseña incorrectos.";
        } 
           if (this.getPassword().equals("")) {
            mensaje = "Usuario o Contraseña incorrectos.";
        }
           
           Usuario1=UsuarioDB.Autenticar(this.getUsuarioIngresado(), this.getPassword());
           
           if (Usuario1 != null){
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario",UsuarioIngresado);
               FacesContext.getCurrentInstance().getExternalContext().redirect("PagPrincipal.xhtml");
           }else{
               mensaje = "Usuario o Contraseña incorrectos.";
           }
       }catch (Exception e){
       
       }
       
   }
    
}


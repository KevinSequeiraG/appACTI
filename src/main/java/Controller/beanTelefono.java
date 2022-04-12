/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TelefonoDB;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanTelefono {
    String NumTelefono;
    String NumTelefonoOpc;
    String idUsuario;
    String TipoTelefono;
    LinkedList<SelectItem> listaTiposTelefono = new LinkedList();
    
    public beanTelefono() {
    }



    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoTelefono() {
        return TipoTelefono;
    }

    public void setTipoTelefono(String TipoTelefono) {
        this.TipoTelefono = TipoTelefono;
    }

    public LinkedList<SelectItem> getListaTiposTelefono() {
        TelefonoDB telefonoDB = new TelefonoDB();       
        return telefonoDB.TiposTelefono();
    }

    public void setListaTiposTelefono(LinkedList<SelectItem> listaTiposTelefono) {
        this.listaTiposTelefono = listaTiposTelefono;
    }

    public String getNumTelefono() {
        return NumTelefono;
    }

    public void setNumTelefono(String NumTelefono) {
        this.NumTelefono = NumTelefono;
    }

    public String getNumTelefonoOpc() {
        return NumTelefonoOpc;
    }

    public void setNumTelefonoOpc(String NumTelefonoOpc) {
        this.NumTelefonoOpc = NumTelefonoOpc;
    }


    
}

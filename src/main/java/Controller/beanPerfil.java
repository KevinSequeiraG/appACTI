/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Perfil;
import Model.PerfilDB;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanPerfil {
    int ID;
    String Descripcion;
    LinkedList<SelectItem> listaPerfiles = new LinkedList<>();

    public beanPerfil() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    public LinkedList<SelectItem> getListaPerfiles()
            throws SNMPExceptions, SQLException{
        String desc="";
        String id="";
        
        LinkedList<Perfil> lista = new LinkedList<Perfil>();
        PerfilDB pDB = new PerfilDB();
        
        lista = pDB.moTodo();
        
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, 
                "Seleccione perfil"));
        
        for (Iterator iter= lista.iterator();
                iter.hasNext();) {
        
            Perfil pro = (Perfil) iter.next();
            resultList.add(new SelectItem(pro.getID(), pro.getDescripcion()));
         }         
         return resultList; 
    }

    public void setListaPerfiles(LinkedList<SelectItem> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

}

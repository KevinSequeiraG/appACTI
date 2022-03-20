/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Provincia;
import Model.ProvinciaDB;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanProvincia {
    int ID;
    String Descripcion;
    LinkedList<SelectItem> listaPro = new LinkedList<>();

    public beanProvincia(int ID, String Descripcion) {
        this.ID = ID;
        this.Descripcion = Descripcion;
    }

    public beanProvincia() {
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

    public LinkedList<SelectItem> getListaPro()
            throws SNMPExceptions, SQLException{
        String desc="";
        int idProvincia=0;
        
        LinkedList<Provincia> lista = new 
        LinkedList<Provincia>();
        ProvinciaDB pDB = new ProvinciaDB();
        
        lista = pDB.moTodo();
        
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, 
                "Seleccione Provincia"));
        
        for (Iterator iter= lista.iterator();
                iter.hasNext();) {
        
            Provincia pro = (Provincia) iter.next();
            
            idProvincia=pro.getID();
            resultList.add(new SelectItem(pro.getID(), pro.getDescripcion()));
         }         
         return resultList; 
    }

    public void setListaPro(LinkedList<SelectItem> listaPro) {
        this.listaPro = listaPro;
    }
    
    
    
}

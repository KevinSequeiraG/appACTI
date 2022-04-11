/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Sede;
import Model.SedeDB;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanSede {
    String ID;
    String Descripcion;
    LinkedList<SelectItem> listaSedes = new LinkedList<>();

    public beanSede() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public LinkedList<SelectItem> getListaSedes()
            throws SNMPExceptions, SQLException{
        String desc="";
        String id="";
        
        LinkedList<Sede> lista = new LinkedList<Sede>();
        SedeDB pDB = new SedeDB();
        
        lista = pDB.moTodo();
        
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, 
                "Seleccione la Sede"));
        
        for (Iterator iter= lista.iterator();
                iter.hasNext();) {
        
            Sede pro = (Sede) iter.next();
            resultList.add(new SelectItem(pro.getID(), pro.getDescripcion()));
         }         
         return resultList; 
    }

    public void setListaSedes(LinkedList<SelectItem> listaSedes) {
        this.listaSedes = listaSedes;
    }
    
}

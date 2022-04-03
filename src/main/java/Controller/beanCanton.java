/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Canton;
import Model.CantonDB;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanCanton {

    int ID;
    String Descripcion;
    int idProvincia;
    LinkedList<SelectItem> listaCan = new LinkedList<>();

    public beanCanton() {
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

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public LinkedList<SelectItem> getListaCan(int idProvincia) 
            throws SNMPExceptions, SQLException{
        String dscCortaCanton="";
        int codigoCanton=0;
        
        LinkedList<Canton> lista = new LinkedList<Canton>();
        CantonDB cDB = new CantonDB();
        
        lista = cDB.moID(idProvincia);
        
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, 
                "Seleccione Cantón"));
        
        for (Iterator iter= lista.iterator();
                iter.hasNext();) {
        
            Canton can = (Canton) iter.next();
            codigoCanton=can.getID();
            
            resultList.add(new SelectItem(codigoCanton, 
                    can.getDescripcion().toLowerCase()));
         }         
         return resultList; 
        
         
    }

    public void setListaCan(LinkedList<SelectItem> listaCan) {
        this.listaCan = listaCan;
    }

}

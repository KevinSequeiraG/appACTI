/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.TipoID;
import Model.TipoIDDB;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanTipoID {

    int ID;
    String Descripcion;
    LinkedList<SelectItem> listaTipoID = new LinkedList<>();

    public beanTipoID() {
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

    public LinkedList<SelectItem> getListaTipoID() throws SNMPExceptions, SQLException {
        String descripcion = "";
        int ID = 0;

        LinkedList<TipoID> lista = new LinkedList<TipoID>();
        TipoIDDB cDB = new TipoIDDB();

        lista = cDB.moTipoID();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0,
                "Tipo de Identificación"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            TipoID tipoID = (TipoID) iter.next();
            ID = tipoID.getID();
            descripcion = tipoID.getDescripcion();

            resultList.add(new SelectItem(ID,
                    descripcion));
        }
        return resultList;
    }

    public void setListaTipoID(LinkedList<SelectItem> listaTipoID) {
        this.listaTipoID = listaTipoID;
    }

}

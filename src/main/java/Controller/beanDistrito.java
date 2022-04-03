/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Distrito;
import Model.DistritoDB;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanDistrito {
    int ID;
    String Descripcion;
    int idCanton;
    int idProvincia;
    LinkedList<SelectItem> listaDis = new LinkedList<>();
    public beanDistrito() {
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

    public int getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(int idCanton) {
        this.idCanton = idCanton;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }
    public LinkedList<SelectItem> getListaDis() {
        return listaDis;
    }

    public void setListaDis(LinkedList<SelectItem> listaDis) {
        this.listaDis = listaDis;
    }

    public LinkedList<SelectItem> getListaDis(int idProvincia, int idCanton)
            throws SNMPExceptions, SQLException {
        String descripcion = "";
        int ID = 0;

        LinkedList<Distrito> lista = new LinkedList<Distrito>();
        DistritoDB cDB = new DistritoDB();

        lista = cDB.moID(idProvincia, idCanton);

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0,
                "Seleccione Distrito"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Distrito dis = (Distrito) iter.next();
            ID = dis.getID();
            descripcion = dis.getDescripcion();
            resultList.add(new SelectItem(ID,
                    descripcion.toLowerCase()));
        }
        return resultList;

    }
}

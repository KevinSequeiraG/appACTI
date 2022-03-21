/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Barrio;
import Model.BarrioDB;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanBarrio {

    int ID;
    String Descripcion;
    int idProvincia;
    int idCanton;
    int idDistrito;
    LinkedList<SelectItem> listaBar = new LinkedList<>();

    public beanBarrio() {
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

    public int getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(int idCanton) {
        this.idCanton = idCanton;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public LinkedList<SelectItem> getListaBar(int idProvincia, int idCanton, int idDistrito)
            throws SNMPExceptions, SQLException {
        String descripcion = "";
        int ID = 0;

        LinkedList<Barrio> lista = new LinkedList<Barrio>();
        BarrioDB cDB = new BarrioDB();

        lista = cDB.moID(idProvincia, idCanton, idDistrito);

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0,
                "Seleccione Barrio"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Barrio bar = (Barrio) iter.next();
            ID = bar.getID();
            descripcion = bar.getDescripcion();
            resultList.add(new SelectItem(ID,
                    descripcion));
        }
        return resultList;

    }

    public void setListaBar(LinkedList<SelectItem> listaBar) {
        this.listaBar = listaBar;
    }
    
}

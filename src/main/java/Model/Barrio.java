/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author erick
 */
public class Barrio {
    int ID;
    String Descripcion;
    int idProvincia;
    int idCanton;
    int idDistrito;

    public Barrio(int ID, String Descripcion, int idProvincia, int idCanton, int idDistrito) {
        this.ID = ID;
        this.Descripcion = Descripcion;
        this.idProvincia = idProvincia;
        this.idCanton = idCanton;
        this.idDistrito = idDistrito;
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
    
}

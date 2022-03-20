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
public class TipoID {
    int ID;
    String Descripcion;

    public TipoID() {
    }

    public TipoID(int ID, String Descripcion) {
        this.ID = ID;
        this.Descripcion = Descripcion;
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
}

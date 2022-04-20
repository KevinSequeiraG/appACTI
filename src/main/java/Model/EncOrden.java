/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author erick
 */
public class EncOrden {
    int ID;
    String Descripcion;
    String idUserEntrega;
    String idUserRecibe;
    String TipoOrden;
    char Estado;
    Date FechaOrdenD;
    String FechaOrden;
    String FechaRecepcion;
    String idSedeDestino;

    public EncOrden() {
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
    
    public Date getFechaOrdenD() {
        return FechaOrdenD;
    }

    public void setFechaOrdenD(Date FechaOrdenD) {
        this.FechaOrdenD = FechaOrdenD;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getIdUserEntrega() {
        return idUserEntrega;
    }

    public void setIdUserEntrega(String idUserEntrega) {
        this.idUserEntrega = idUserEntrega;
    }

    public String getIdUserRecibe() {
        return idUserRecibe;
    }

    public void setIdUserRecibe(String idUserRecibe) {
        this.idUserRecibe = idUserRecibe;
    }

    public String getTipoOrden() {
        return TipoOrden;
    }

    public void setTipoOrden(String TipoOrden) {
        this.TipoOrden = TipoOrden;
    }

    public char getEstado() {
        return Estado;
    }

    public void setEstado(char Estado) {
        this.Estado = Estado;
    }

    public String getFechaOrden() {
        return FechaOrden;
    }

    public void setFechaOrden(String FechaOrden) {
        this.FechaOrden = FechaOrden;
    }

    public String getFechaRecepcion() {
        return FechaRecepcion;
    }

    public void setFechaRecepcion(String FechaRecepcion) {
        this.FechaRecepcion = FechaRecepcion;
    }   

    public String getIdSedeDestino() {
        return idSedeDestino;
    }

    public void setIdSedeDestino(String idSedeDestino) {
        this.idSedeDestino = idSedeDestino;
    }
}

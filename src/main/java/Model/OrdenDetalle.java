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
public class OrdenDetalle {
    String idActivo;
    int idOrdenEncabezadoActivo;
    int cant;

    public OrdenDetalle() {
    }

    public OrdenDetalle(String idActivo, int idOrdenEncabezadoActivo, int cant) {
        this.idActivo = idActivo;
        this.idOrdenEncabezadoActivo = idOrdenEncabezadoActivo;
        this.cant = cant;
    }
    
    
    public String getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(String idActivo) {
        this.idActivo = idActivo;
    }

    public int getIdOrdenEncabezadoActivo() {
        return idOrdenEncabezadoActivo;
    }

    public void setIdOrdenEncabezadoActivo(int idOrdenEncabezadoActivo) {
        this.idOrdenEncabezadoActivo = idOrdenEncabezadoActivo;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}

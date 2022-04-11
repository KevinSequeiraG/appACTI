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
public class Telefono {
    int NumTelefono;
    String idUsuario;
    String TipoTelefono;

    public Telefono() {
    }

    public Telefono(int NumTelefono, String idUsuario, String TipoTelefono) {
        this.NumTelefono = NumTelefono;
        this.idUsuario = idUsuario;
        this.TipoTelefono = TipoTelefono;
    }
    

    public int getNumTelefono() {
        return NumTelefono;
    }

    public void setNumTelefono(int NumTelefono) {
        this.NumTelefono = NumTelefono;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoTelefono() {
        return TipoTelefono;
    }

    public void setTipoTelefono(String TipoTelefono) {
        this.TipoTelefono = TipoTelefono;
    }
}

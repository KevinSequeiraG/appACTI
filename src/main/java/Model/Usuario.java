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
public class Usuario {
    String ID;
    int idTipoID;
    String Nombre;
    String Apellido1;
    String Apellido2;
    String FechNac;
    int idProvincia;
    int idCanton;
    int idDistrito;
    int idBarrio;
    String OtrasSennas;
    String Email;
    String idSede;
    int CodSeg;
    String Password;
    int idPerfil;
    char EstadoSolicitud;
    String FechaSolicitud;
    Date fecNacDate;
    
    boolean editFunc=true;

    public Usuario() {
    }

    public Usuario(String ID, int idTipoID, String Password, char EstadoSolicitud) {
        this.ID = ID;
        this.idTipoID = idTipoID;
        this.Password = Password;
        this.EstadoSolicitud = EstadoSolicitud;
    }
    
    public Usuario(String ID, int idTipoID, String nombre,String apellido1, String apellido2, Date fechaNac, String email, String Sede){
        this.ID = ID;
        this.idTipoID = idTipoID;
        this.Nombre = nombre;
        this.Apellido1 = apellido1;
        this.Apellido2 = apellido2;
        this.FechNac = String.valueOf(fechaNac);
        this.Email = email;
        this.idSede = Sede;
        this.fecNacDate =fechaNac;
        editFunc = true;
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getIdTipoID() {
        return idTipoID;
    }

    public void setIdTipoID(int idTipoID) {
        this.idTipoID = idTipoID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
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

    public int getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(int idBarrio) {
        this.idBarrio = idBarrio;
    }

    public String getOtrasSennas() {
        return OtrasSennas;
    }

    public void setOtrasSennas(String OtrasSennas) {
        this.OtrasSennas = OtrasSennas;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getIdSede() {
        return idSede;
    }

    public void setIdSede(String idSede) {
        this.idSede = idSede;
    }

    public int getCodSeg() {
        return CodSeg;
    }

    public void setCodSeg(int CodSeg) {
        this.CodSeg = CodSeg;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public char getEstadoSolicitud() {
        return EstadoSolicitud;
    }

    public void setEstadoSolicitud(char EstadoSolicitud) {
        this.EstadoSolicitud = EstadoSolicitud;
    }

    public String getFechNac() {
        return FechNac;
    }

    public void setFechNac(String FechNac) {
        this.FechNac = FechNac;
    }

    public String getFechaSolicitud() {
        return FechaSolicitud;
    }

    public void setFechaSolicitud(String FechaSolicitud) {
        this.FechaSolicitud = FechaSolicitud;
    }

    public boolean isEditFunc() {
        return editFunc;
    }

    public void setEditFunc(boolean editFunc) {
        this.editFunc = editFunc;
    }

    public Date getFecNacDate() {
        return fecNacDate;
    }

    public void setFecNacDate(Date fecNacDate) {
        this.fecNacDate = fecNacDate;
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Usuario;
import Model.UsuarioDB;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Oscar
 */
public class beanUsuario {

    String ID;
    int idTipoID;
    String Nombre;
    String Apellido1;
    String Apellido2;
    Date FechNac;
    String SFechaNac;
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
    Date FechaSolicitud;
    String SFechaSolicitud;
    String edad = "15";

    //BD
    Usuario user;
    UsuarioDB userDB = new UsuarioDB();

    //Para darle formato a los Date
    String pattern = "dd/MM/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public beanUsuario() {
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

    public Date getFechNac() {
        return FechNac;
    }

    public void setFechNac(Date FechNac) {
        this.FechNac = FechNac;
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

    public Date getFechaSolicitud() {
        return FechaSolicitud;
    }

    public void setFechaSolicitud(Date FechaSolicitud) {
        this.FechaSolicitud = FechaSolicitud;
    }

    /**
     * Inserta un usuario
     *
     * @throws SNMPExceptions
     * @throws SQLException return void
     */
    public void InsertarUsuario() throws SNMPExceptions, SQLException {

        if (ValidarDatos()) {
            
            this.user = new Usuario();
            user.setID(this.getID());
            user.setIdTipoID(this.getIdTipoID());
            user.setNombre(this.getNombre());
            user.setApellido1(this.getApellido1());
            user.setApellido2(this.getApellido2());            
            user.setFechNac(this.getSFechaNac());
            user.setIdProvincia(this.getIdProvincia());
            user.setIdCanton(this.getIdCanton());
            user.setIdDistrito(this.getIdDistrito());
            user.setOtrasSennas(this.getOtrasSennas());
            user.setEmail(this.getEmail());
            user.setIdSede(this.getIdSede());
            user.setCodSeg(this.getCodSeg());
            user.setPassword(this.getPassword());
            user.setIdBarrio(this.getIdBarrio());
            user.setIdPerfil(this.getIdPerfil());
            user.setEstadoSolicitud(this.getEstadoSolicitud());
            user.setFechaSolicitud(this.getSFechaSolicitud());
            
            //consulta si el usuario ya existe
            if (!ExisteUsuario(this.getID())) { 
                userDB.InsertarUsuario(user); //lo inserta
            } else {
                //el usuario ya existe (hacer mensaje con validaciones)
            }
        }

    }
    /**
     * Valida los datos del usuario
     * @return boolean
     */
    public boolean ValidarDatos() {
        
        return true;
    }
    
    /**
     * Consulta si el usuario existe en la BD
     * @param id
     * @return boolean
     * @throws SNMPExceptions
     * @throws SQLException 
     */
    public boolean ExisteUsuario(String id) throws SNMPExceptions, SQLException {
        return userDB.consultarUsuario(id); //consulta si el usuario ya existe
    }

    /**
     * Convertir Date a String
     *
     * @param fecha
     * @return String
     */
    public String FechaConFormato(Date fecha) {
        return this.simpleDateFormat.format(fecha);
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSFechaNac() {
        return simpleDateFormat.format(this.getFechNac());
    }

    public void setSFechaNac(String SFechaNac) {
        this.SFechaNac = SFechaNac;
    }

    public String getSFechaSolicitud() {
        return simpleDateFormat.format(this.getFechaSolicitud());
    }

    public void setSFechaSolicitud(String SFechaSolicitud) {
        this.SFechaSolicitud = SFechaSolicitud;
    }
    
}

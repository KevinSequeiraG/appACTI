/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Provincia;
import Model.Telefono;
import Model.TelefonoDB;
import Model.Usuario;
import Model.UsuarioDB;
import java.io.Console;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import javax.faces.model.SelectItem;

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
    String edad = "0";

    //BD
    Usuario user;
    UsuarioDB userDB = new UsuarioDB();

    //Para darle formato a los Date
    String pattern = "yyyy/MM/dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String mensaje = "";
    String mensaje2 = "";

    //Lista para el crud de Funcionarios
    ArrayList<Usuario> listaFuncionarios = new ArrayList<>();

    //pruebas
    boolean editFunc = false;

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
        return (Date) Calendar.getInstance().getTime();
    }

    public void setFechaSolicitud(Date FechaSolicitud) {
        this.FechaSolicitud = FechaSolicitud;
    }

    public void RegistrarFuncionario(int TipoId, int proId, int canId, int disId, int barId, String numTelefono, String tipoTelefono, String numTelefono2, String sede, int tipoPerfil) throws SNMPExceptions, SQLException {
        setMensaje("");
        setMensaje2("");
        this.setIdTipoID(TipoId);
        this.setIdProvincia(proId);
        this.setIdCanton(canId);
        this.setIdDistrito(disId);
        this.setIdBarrio(barId);
        this.setIdSede(sede);
        this.setIdPerfil(tipoPerfil);
        this.setEstadoSolicitud('P');
        if (tipoTelefono.equals("Tipo de teléfono")) {
            mensaje = "Debe elegir un tipo de telefono.";
        } else if (numTelefono.equals("")) {
            mensaje = "Debe ingresar su numero de telefono.";
        } else if ((!numTelefono.equals("")) & !numTelefono.matches("[+-]?\\d*(\\.\\d+)?")) {
            mensaje = "Debe verificar que lo ingresado si sean numeros solamente.";
        } else if (numTelefono2.equals("")) {
            mensaje = "Debe ingresar un segundo telefono.";
        } else if ((!numTelefono2.equals("")) & !numTelefono2.matches("[+-]?\\d*(\\.\\d+)?")) {
            mensaje = "Debe ingresar numero en el espacio segundo telefono.";
        } else {
            if (InsertarUsuario()) {
                mensaje2 = "Usuario registrado exitosamente";

                Telefonos(numTelefono, tipoTelefono, numTelefono2);
            }

        }
    }

    public void Telefonos(String numTelefono, String tipoTelefono, String numTelefono2) throws SNMPExceptions, SQLException {
        TelefonoDB tDB = new TelefonoDB();
        int numero = 0, numero2 = 0;

        //Validaciones
        /**
         * if (tipoTelefono.equals("Tipo de teléfono")) { this.mensaje =
         * "Necesita elegir el tipo de teléfono"; } try { numero =
         * Integer.parseInt(numTelefono); if (!numTelefono2.equals("")) {
         * numero2 = Integer.parseInt(numTelefono2); } } catch (Exception e) {
         * this.mensaje = "Formato incocorrecto de números telefónicos.";
         * return; } if (numTelefono.equals(numTelefono2)) { this.mensaje = "Los
         * números de teléfono no pueden ser iguales"; }
         */
        numero = Integer.parseInt(numTelefono);
        numero2 = Integer.parseInt(numTelefono2);
        Telefono tel = new Telefono(numero, this.getID(), tipoTelefono);
        Telefono tel2 = new Telefono(numero2, this.getID(), tipoTelefono);

        if (numero != 0) {
            tDB.InsertarTelefono(tel);
        }
        if (numero2 != 0) {
            tDB.InsertarTelefono(tel2);
        }

    }

    /**
     * Inserta un usuario
     *
     * @return boolean
     * @throws SNMPExceptions
     * @throws SQLException return void
     */
    public boolean InsertarUsuario() throws SNMPExceptions, SQLException {
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

        if (Validacion()) {
            //consulta si el usuario ya existe
            if (!ExisteUsuario(this.getID())) {
                userDB.InsertarUsuario(user); //lo inserta
                return true;
            } else {
                mensaje = "El usuario ya se encuentra registrado";//el usuario ya existe (hacer mensaje con validaciones)
                return false;
            }
        }

        return false;

    }

    /**
     * Consulta si el usuario existe en la BD
     *
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
        if (FechNac != null) {

            String resp = "0";
            Date date = new Date();
            if (!FechNac.equals("")) {
                Period periodo = Period.between(this.getFechNac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                resp = String.valueOf(periodo.getYears());
            }
            return resp;
        }
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public boolean Validacion() {
        boolean resp = true;
        Date date = new Date();

        if (this.getID().equals("")) {
            mensaje = "Debe llenar el campo Identificación." + String.valueOf(this.getIdTipoID());
            resp = false;
        } else if (String.valueOf(this.getIdTipoID()).equals("0")) {
            mensaje = "Debe elegir un tipo de identificación.";
            resp = false;
        } else if (this.getNombre().equals("")) {
            mensaje = "Debe llenar el campo Nombre.";
            resp = false;
        } else if (this.getApellido1().equals("")) {
            mensaje = "Debe llenar el campo Primer Apellido.";
            resp = false;
        } else if (this.getApellido2().equals("")) {
            mensaje = "Debe llenar el campo Segundo Apellido.";
            resp = false;
        } else if (this.getApellido1().equals("")) {
            mensaje = "Debe llenar el campo Primer Apellido.";
            resp = false;
        } /*else if (this.getFechNac().equals(date)) {
            mensaje = "La fecha de nacimiento debe ser un dia diferente a hoy.";
            resp = false;
        } else if (this.getFechNac().after(date)) {
            mensaje = "La fecha de nacimiento no puede ser mayor a la fecha de hoy.";
            resp = false;
        }*/ else if (this.getEdad().equals("")) {
            mensaje = "Debe llenar el campo Edad.";
            resp = false;
        } else if ((!this.getEdad().equals("")) & !this.getEdad().matches("[+-]?\\d*(\\.\\d+)?")) {
            mensaje = "El campo edad debe contener solo números.";
            return false;
        } else if (String.valueOf(this.getIdProvincia()).equals("0")) {
            mensaje = "Debe seleccionar una Provincia";
            resp = false;
        } else if (String.valueOf(this.getIdCanton()).equals("0")) {
            mensaje = "Debe seleccionar un Canton.";
            resp = false;
        } else if (String.valueOf(this.getIdDistrito()).equals("0")) {
            mensaje = "Debe seleccionar un Distrito.";
            resp = false;
        } else if (String.valueOf(this.getIdBarrio()).equals("0")) {
            mensaje = "Debe seleccionar un Barrio.";
            resp = false;
        } else if (this.getOtrasSennas().equals("")) {
            mensaje = "Debe incluir información en el espacio de otras señas.";
            resp = false;
        } else if (this.getEmail().equals("")) {
            mensaje = "Debe agregar su correo en el campo definido.";
            resp = false;
        } else if (String.valueOf(this.getIdSede()).equals("0")) {
            mensaje = "Debe elegir una sede.";
            resp = false;
        } else if (String.valueOf(this.getIdPerfil()).equals("0")) {
            mensaje = "Debe elegir un tipo de perfil";
        }
        return resp;
    }

    public ArrayList<Usuario> getListaFuncionarios() throws SNMPExceptions, SQLException {
        UsuarioDB logica = new UsuarioDB();

        return logica.ListaFuncionarios();
    }

    public void setListaFuncionarios(ArrayList<Usuario> ListaFuncionarios) {
        this.listaFuncionarios = ListaFuncionarios;
    }

    public void eliminarFuncionario(String ID) throws SNMPExceptions, SQLException {
        UsuarioDB logica = new UsuarioDB();
        logica.eliminarFuncionario(ID);
    }

    public Usuario retornarUsuario(String ID) throws SNMPExceptions, SQLException {
        UsuarioDB logica = new UsuarioDB();
        return logica.retornarUsuario(ID);
    }

    public void editarFuncionario() throws SNMPExceptions, SQLException {
        if (Nombre.equals("") || Apellido1.equals("") || Apellido2.equals("") || idTipoID == 0 || Email.equals("") || idSede.equals("")) {
            mensaje = "Debe presionar el botón editar del funcionario que desea modificar.";
            mensaje2 = "";
        } else {
            UsuarioDB logica = new UsuarioDB();
            logica.editarFuncionario(ID, idTipoID, Nombre, Apellido1, Apellido2, simpleDateFormat.format(FechNac), Email, idSede);
            mensaje2 = "Funcionario Editado Correctamente.";
            mensaje = "";
            this.Nombre = "";
            this.Apellido1 = "";
            this.Apellido2 = "";
            this.idTipoID = 0;
            this.Email = "";
            this.idSede = "";
        }
    }

    public void AsignaDatosEdit(Usuario user) throws Exception {
        this.ID = user.getID();
        this.idTipoID = user.getIdTipoID();
        this.Nombre = user.getNombre();
        this.Apellido1 = user.getApellido1();
        this.Apellido2 = user.getApellido2();
        this.FechNac = user.getFecNacDate();
        this.Email = user.getEmail();
        this.idSede = user.getIdSede();

    }

    public boolean isEditFunc() {
        return editFunc;
    }

    public void setEditFunc(boolean editFunc) {
        this.editFunc = editFunc;
    }

}

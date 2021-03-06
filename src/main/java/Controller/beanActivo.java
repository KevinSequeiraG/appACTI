/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Activo;
import Model.ActivoDB;
import Model.UsuarioDB;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanActivo {
    String ID;
    String Descripcion;
    float Valor;
    String idSede;
    Date FechaRegistro;
    String SFechaRegistro;
    String idUsuario;
    
    int Cantidad = 1;
    ArrayList<Activo> listaAct = new ArrayList<>();
    //BD
    Activo activo;
    ActivoDB activoDB = new ActivoDB();

    //Para darle formato a los Date
    String pattern = "yyyy/MM/dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String mensaje = "";
    String mensaje2 = "";
    
    Date Fech1;
    String SFech1;
    Date Fech2;
    String SFech2;
    ArrayList<Activo> listaActivosRepo = new ArrayList<>();
    
    public beanActivo() {
    }

    public void Limpiar(){
        this.setID("");        
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float Valor) {
        this.Valor = Valor;
    }

    public String getIdSede() {
        return idSede;
    }

    public void setIdSede(String idSede) {
        this.idSede = idSede;
    }

    public Date getFechaRegistro() {
        return (Date) Calendar.getInstance().getTime();
    }

    public void setFechaRegistro(Date FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
    
    public String getSFechaRegistro() {
        return simpleDateFormat.format(this.getFechaRegistro());
    }

    public void setSFechaRegistro(String SFechaRegistro) {
        this.SFechaRegistro = SFechaRegistro;
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

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
    
    
    public void RegistrarActivos(String idSede, String idUsuario) throws SNMPExceptions, SQLException {
        setMensaje("");
        setMensaje2("");
        this.setIdSede(idSede);
        this.setIdUsuario(idUsuario);
        
            if (InsertarActivo()) {
                mensaje2 = "Activo registrado exitosamente";
            }

    }
    
    public boolean InsertarActivo() throws SNMPExceptions, SQLException {
        this.activo = new Activo();
        activo.setID(this.getID());
        activo.setDescripcion(this.getDescripcion());
        activo.setValor(this.getValor());
        activo.setIdSede(this.getIdSede());
        activo.setFechaRegistro(this.getSFechaRegistro());
        activo.setIdUsuario(this.getIdUsuario());
        activo.setCantidad(1);

        if (Validacion()) {
            //consulta si el activo ya existe
            if (!ExisteActivo(this.getID())) {
                activoDB.InsertarActivo(activo); //lo inserta
                this.limpiar2();
                return true;
            } else {
                mensaje = "El activo ya se encuentra registrado";//el activo ya existe (hacer mensaje con validaciones)
                
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
    public boolean ExisteActivo(String id) throws SNMPExceptions, SQLException {
        return activoDB.consultarActivo(id); //consulta si el activo ya existe
    }
    
    public boolean Validacion() throws SNMPExceptions, SQLException {
        UsuarioDB userDB = new UsuarioDB();
        ActivoDB activoDB = new ActivoDB();
        boolean resp = true;
        //Date date = new Date();

        if (this.getID().equals("")) {
            mensaje = "Debe llenar el campo Codigo del Activo.";
            resp = false;
            return resp;
        } else if (this.getDescripcion().equals("")) {
            mensaje = "Debe llenar la descripci??n.";
            resp = false;
            return resp;
        } else if (String.valueOf(this.getValor()).equals("")) {
            mensaje = "Debe llenar el campo Valor.";
            resp = false;
            return resp;
        }else if (!String.valueOf(this.getValor()).matches("[+-]?\\d*(\\.\\d+)?")) {
            mensaje = "Debe ingresar n??meros en el espacio de Valor.";
            resp = false;
            return resp;
        }
        else if(this.getValor() <= 0.0){
            mensaje = "El valor debe ser un n??mero mayor a 0.";
            resp = false;
            return resp;
        } 
        else if(this.getIdUsuario().equals("")){
            mensaje = "Debe llenar el campo Identificaci??n del funcionario.";
            resp = false;
            return resp;
        } 
        else if (!userDB.consultarUsuario(this.getIdUsuario())) {
            mensaje = "El usuario digitado no existe.";
            resp = false;
            return resp;
        } else if (userDB.consultarPerfil(this.getIdUsuario()) != 2) {
            mensaje = "El usuario digitado no es un funcionario.";
            resp = false;
            return resp;
        } else if (String.valueOf(this.getIdSede()).equals("0")) {
            mensaje = "Debe elegir una sede.";
            resp = false;
            return resp;
        } 
        return resp;
    }
    
    public void limpiar2(){
        this.ID = "";
        this.Descripcion = "";
        this.Valor =0;
        this.idUsuario = "";
    }

    public ArrayList<Activo> getListaAct(String idUsuario) throws SNMPExceptions, SQLException {
        return activoDB.consultarActivosPorFunc(idUsuario);
    }

    public void setListaAct(ArrayList<Activo> listaAct) {
        this.listaAct = listaAct;
    }

    public Date getFech1() {
        return Fech1;
    }

    public void setFech1(Date Fech1) {
        this.Fech1 = Fech1;
    }

    public String getSFech1() {
        return simpleDateFormat.format(this.getFech1());
    }

    public void setSFech1(String SFech1) {
        this.SFech1 = SFech1;
    }

    public Date getFech2() {
        return Fech2;
    }

    public void setFech2(Date Fech2) {
        this.Fech2 = Fech2;
    }

    public String getSFech2() {
        return simpleDateFormat.format(this.getFech2());
    }

    public void setSFech2(String SFech2) {
        this.SFech2 = SFech2;
    }
    
    

    public ArrayList<Activo> getListaActivosRepo() throws SNMPExceptions, SQLException {
        if (Fech1==null || Fech2==null) {
            return listaActivosRepo;
        }else {
            ActivoDB logica = new ActivoDB();
            return logica.ReportePorFechas(this.getSFech1(), this.getSFech2());
        }
        
    }

    public void setListaActivosRepo(ArrayList<Activo> listaActivosRepo) {
        this.listaActivosRepo = listaActivosRepo;
    }
    
}

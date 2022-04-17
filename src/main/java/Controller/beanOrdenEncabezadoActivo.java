/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.ActivoDB;
import Model.EncOrden;
import Model.EncOrdenDB;
import Model.OrdenDetalle;
import Model.OrdenDetalleDB;
import Model.SedeDB;
import Model.UsuarioDB;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Oscar
 */
public class beanOrdenEncabezadoActivo {

    int ID;
    String Descripcion;
    String idUserEntrega;
    String idUserRecibe;
    String TipoOrden;
    char Estado;
    Date FechaOrden;
    String SFechaOrden;
    Date FechaRecepcion;
    String SFechaRecepcion;
    String idSedeDestino;
    String descSede;
    LinkedList<SelectItem> listaTipoOrden = new LinkedList();
    LinkedList<OrdenDetalle> carrito = new LinkedList<OrdenDetalle>();
    String pattern = "yyyy/MM/dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String mensaje = "";
    String mensaje2 = "";

    public beanOrdenEncabezadoActivo() {
    }

    public int getID() throws SNMPExceptions, SQLException {
        EncOrdenDB encOrdenDB = new EncOrdenDB();
        this.ID = encOrdenDB.UltimoID() + 1;
        return encOrdenDB.UltimoID() + 1;
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

    public String getIdUserEntrega() {
        return idUserEntrega;
    }

    public void setIdUserEntrega(String idUserEntrega) {
        this.idUserEntrega = idUserEntrega;
    }

    public String getDescSede(String sede) throws SNMPExceptions, SQLException {
        SedeDB sedeDB = new SedeDB();
        return sedeDB.sedePorIdD(sede);
    }

    public void setDescSede(String descSede) {
        this.descSede = descSede;
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

    public Date getFechaOrden() {
        return (Date) Calendar.getInstance().getTime();
    }

    public void setFechaOrden(Date FechaOrden) {
        this.FechaOrden = FechaOrden;
    }

    public Date getFechaRecepcion() {
        return FechaRecepcion;
    }

    public void setFechaRecepcion(Date FechaRecepcion) {
        this.FechaRecepcion = FechaRecepcion;
    }

    public String getIdSedeDestino() {
        return idSedeDestino;
    }

    public void setIdSedeDestino(String idSedeDestino) {
        this.idSedeDestino = idSedeDestino;
    }

    /*public int GenerarIDOrden() throws SNMPExceptions, SQLException {
        int num = (int) (Math.random() * 4000 + 1000);
        EncOrdenDB encOrden = new EncOrdenDB();
        while (encOrden.ExisteOrden(num)) {
            num = (int) (Math.random() * 4000 + 1000);
        }
        return num;
    }*/
    public LinkedList<SelectItem> getListaTipoOrden() {
        EncOrdenDB encOrden = new EncOrdenDB();
        return encOrden.listaTipoOrden();
    }

    public void setListaTipoOrden(LinkedList<SelectItem> listaTipoOrden) {
        this.listaTipoOrden = listaTipoOrden;
    }

        public void InsertarOrden(String idSedeD, String idUsuarioEntrega, String idActivo, int cantidad) throws SNMPExceptions, SQLException {
        mensaje = "";
        mensaje2 = "";
        EncOrdenDB encOrden = new EncOrdenDB();
        OrdenDetalleDB lineaD = new OrdenDetalleDB();
        EncOrden orden = new EncOrden();
        orden.setDescripcion(this.getDescripcion());
        orden.setIdUserEntrega(idUsuarioEntrega);
        orden.setIdUserRecibe(this.getIdUserRecibe());
        orden.setTipoOrden(this.getTipoOrden());
        orden.setEstado('P');
        orden.setFechaOrden(this.getSFechaOrden());
        this.setIdSedeDestino(idSedeD);
        orden.setIdSedeDestino(this.getIdSedeDestino());
        if (Validar(idActivo, cantidad, idSedeD)) {
            if (carrito.isEmpty()) {
                mensaje = "Debe añadir al menos un activo a la lista.";
                return;
            }
            encOrden.InsertarEncOrden(orden);

            for (OrdenDetalle ordenDetalle : carrito) {
                lineaD.InsertarLineaDetalle(ordenDetalle);
            }
            this.mensaje2 = "Orden efectuada satisfactoriamente";
        }

    }

    public boolean Validar(String idActivo, int cantidad,String idSedeD) throws SNMPExceptions, SQLException {
        boolean resp = true;
        UsuarioDB userDB = new UsuarioDB();
        ActivoDB activoDB = new ActivoDB();
        this.setIdSedeDestino(idSedeD);
        if (String.valueOf(this.getIdSedeDestino()).equals("0")) {
            mensaje = "Debe elegir una sede de destino.";
            resp = false;
            return resp;
        } else if (this.getIdUserRecibe().equals("")) {
            mensaje = "Debe digitar la identificación del usuario que recibe.";
            resp = false;
            return resp;
        } else if (!userDB.consultarUsuario(this.getIdUserRecibe())) {
            mensaje = "El usuario digitado no existe.";
            resp = false;
            return resp;
        } else if (this.getDescripcion().equals("")) {
            mensaje = "Debe digitar la justificación de la Orden.";
            resp = false;
            return resp;
        } else if (this.getTipoOrden().equals("Seleccione Tipo")) {
            mensaje = "Debe elegir el Tipo de Orden.";
            resp = false;
            return resp;
        } else if (idActivo.equals("")) {
            mensaje = "Debe digitar el identificador del activo.";
            resp = false;
            return resp;
        } else if (!activoDB.consultarActivo(idActivo)) {
            mensaje = "El activo digitado no existe.";
            resp = false;
            return resp;
        } else if (cantidad < 1) {
            mensaje = "Cantidad inválida.";
            resp = false;
            return resp;
        } else if (activoDB.consultarCantidad(idActivo) < cantidad) {
            mensaje = "El activo digitado no cuenta con la cantidad suficiente.";
            resp = false;
            return resp;
        }
        mensaje = "";
        return resp;
    }

    public void GenerarLineaDetalle(String idActivo, int cantidad, String idSedeD) throws SNMPExceptions, SQLException {

        if (Validar(idActivo, cantidad,idSedeD)) {
            for (OrdenDetalle ordenDetalle : carrito) {
                if (ordenDetalle.getIdActivo().equals(idActivo)) {
                    mensaje = "El activo ya está agregado en la lista";
                    return;
                }
            }
            OrdenDetalle linea = new OrdenDetalle(idActivo, this.getID(), cantidad);
            this.carrito.add(linea);
            mensaje = "";
        }
    }

    public void EliminarLineaDetalle(String idActivo) {
        for (OrdenDetalle ordenDetalle : carrito) {
            if (ordenDetalle.getIdActivo().equals(idActivo)) {
                this.carrito.remove(ordenDetalle);
            }
        }
    }

    public String getSFechaOrden() {
        return this.simpleDateFormat.format(this.getFechaOrden());
    }

    public void setSFechaOrden(String SFechaOrden) {
        this.SFechaOrden = SFechaOrden;
    }

    public String getSFechaRecepcion() {
        return SFechaRecepcion;
    }

    public void setSFechaRecepcion(String SFechaRecepcion) {
        this.SFechaRecepcion = SFechaRecepcion;
    }

    public LinkedList<OrdenDetalle> getCarrito() {
        return carrito;
    }

    public void setCarrito(LinkedList<OrdenDetalle> carrito) {
        this.carrito = carrito;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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

}

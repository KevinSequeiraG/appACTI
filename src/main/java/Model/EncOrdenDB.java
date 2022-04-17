/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author erick
 */
public class EncOrdenDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public EncOrdenDB() {
        super();
    }

    public EncOrdenDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public AccesoDatos getAccesoDatos() {
        return accesoDatos;
    }

    public void setAccesoDatos(AccesoDatos accesoDatos) {
        this.accesoDatos = accesoDatos;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * Insertar EncabezadoOrden
     *
     * @param orden
     * @throws SNMPExceptions
     * @throws SQLException return void
     */
    public void InsertarEncOrden(EncOrden orden) throws SNMPExceptions,
            SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Usuario
            strSQL = "INSERT INTO [dbo].[OrdenEncabezadoActivo]\n"
                    + "([Descripcion]"
                    + ",[idUserEntrega]"
                    + ",[idUserRecibe]"
                    + ",[TipoOrden]"
                    + ",[Estado]"
                    + ",[FechaOrden]"
                    + ",[idSedeDestino])"
                    + " VALUES"
                    + "(" + "'" + orden.getDescripcion() + "'" + ","
                    + "'" + orden.getIdUserEntrega() + "'" + ","
                    + "'" + orden.getIdUserRecibe() + "'" + ","
                    + "'" + orden.getTipoOrden() + "'" + ","
                    + "'" + orden.getEstado() + "'" + ","
                    + "'" + orden.getFechaOrden() + "'" + ","
                    + "'" + orden.getIdSedeDestino() + "'"
                    + ")";
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    /**
     * Verificar el último ID generado en EncOrden
     *
     * @return
     */
    public int UltimoID() throws SNMPExceptions,
            SQLException {
        String select = "";
        int ultimo = 0;
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "Select max(ID) as ulltimo from OrdenEncabezadoActivo";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                ultimo = rsPA.getInt("ulltimo");
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }

        return ultimo;
    }

    /**
     * Verificar que no se repitan ID's de EncabezadoOrden
     *
     * @param num
     * @return boolean
     */
    public boolean ExisteOrden(int num) throws SNMPExceptions, SQLException {
        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from OrdenEncabezadoActivo where ID = " + num;

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            if (rsPA.next()) {

                existe = true;
            }

            rsPA.close();

            return existe;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public LinkedList<SelectItem> listaTipoOrden() {
        LinkedList ListaConsulta = new LinkedList();
        ListaConsulta.add(new SelectItem("Seleccione Tipo"));
        ListaConsulta.add(new SelectItem("Traslado"));
        ListaConsulta.add(new SelectItem("Préstamo"));
        return ListaConsulta;
    }

}

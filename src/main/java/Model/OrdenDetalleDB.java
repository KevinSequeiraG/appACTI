/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author erick
 */
public class OrdenDetalleDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public OrdenDetalleDB() {
        super();
    }

    public OrdenDetalleDB(Connection conn) {
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
     * Insertar LineaDetalle
     *
     * @param orden
     * @throws SNMPExceptions
     * @throws SQLException return void
     */
    public void InsertarLineaDetalle(OrdenDetalle lineaD) throws SNMPExceptions,
            SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Usuario
            strSQL = "INSERT INTO [dbo].[OrdenDetalleActivo]"
                    + " VALUES"
                    + "(" + "'" + lineaD.getIdActivo()+ "'" + ","
                    + lineaD.getIdOrdenEncabezadoActivo()+ ","
                    + lineaD.getCant() + ")";
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
}

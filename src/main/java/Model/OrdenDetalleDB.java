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
import java.util.ArrayList;

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
                    + 1 + ")";
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
     * Lista de lineas detalle por orden
     * @return ArrayList
     * @throws SNMPExceptions
     * @throws SQLException 
     */
    public ArrayList<OrdenDetalle> ListaLineaDPorOrden(int idOrden) throws SNMPExceptions, SQLException {        
        String select = "";
        OrdenDetalle ordenD;        
        ArrayList<OrdenDetalle> lista = new ArrayList<OrdenDetalle>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT [idActivo],[idOrdenEncabezadoActivo],[cant] FROM [dbo].[OrdenDetalleActivo] WHERE [idOrdenEncabezadoActivo] = " + idOrden;
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                ordenD = new OrdenDetalle();
                ordenD.idActivo = rsPA.getString("idActivo");
                ordenD.cant = rsPA.getInt("cant");                
                lista.add(ordenD);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return lista;
    }
}

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
public class TelefonoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;


    public TelefonoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public TelefonoDB() {
        super();
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
     * Inserta un teléfono
     * @param tel
     * @throws SNMPExceptions
     * @throws SQLException 
     */
    public void InsertarTelefono(Telefono tel) throws SNMPExceptions,
            SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Usuario
            strSQL = "INSERT INTO [dbo].[Telefono]"
                    + " ([NumTelefonico]"
                    + ",[idUsuario]"
                    + ",[TipoTelefono])"  
                    + " VALUES"
                    + "(" +tel.getNumTelefono()+","
                    + "'" + tel.getIdUsuario()+ "'" + ","
                    + "'" + tel.getTipoTelefono()+ "'"
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
    public LinkedList<SelectItem> TiposTelefono() {
        LinkedList ListaConsulta = new LinkedList();
        ListaConsulta.add(new SelectItem("Tipo de teléfono"));
        ListaConsulta.add(new SelectItem("Residencial"));
        ListaConsulta.add(new SelectItem("Celular"));
        return ListaConsulta;
    }

}

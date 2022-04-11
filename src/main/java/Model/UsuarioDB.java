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

/**
 *
 * @author erick
 */
public class UsuarioDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    private LinkedList<Usuario> listaU = new LinkedList<Usuario>();

    public UsuarioDB() {
    }

    public UsuarioDB(Connection conn) {
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

    public LinkedList<Usuario> getListaU() {
        return listaU;
    }

    public void setListaU(LinkedList<Usuario> listaU) {
        this.listaU = listaU;
    }

    /**
     * Insertar funcionario
     *
     * @param user
     * @throws SNMPExceptions
     * @throws SQLException return void
     */
    public void InsertarUsuario(Usuario user) throws SNMPExceptions,
            SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Usuario
            strSQL = "INSERT INTO [dbo].[Usuario]"
                    + " ([ID]"
                    + ",[idTipoID]"
                    + ",[Nombre]"
                    + ",[Apellido1]"
                    + ",[Apellido2]"
                    + ",[FechNac]"
                    + ",[idProvincia]"
                    + ",[idCanton]"
                    + ",[idDistrito]"
                    + ",[OtraSennas]"
                    + ",[Email]"
                    + ",[idSede]"
                    + ",[CodSec]"
                    + ",[Password]"
                    + ",[idBarrio]"
                    + ",[idPerfil]"
                    + ",[EstadoSolicitud]"
                    + ",[FechaSolicitud]) "
                    + " VALUES"
                    + "(" + "'" + user.getID() + "'" + ","
                    + user.getIdTipoID() + ","
                    + "'" + user.getNombre() + "'" + ","
                    + "'" + user.getApellido1() + "'" + ","
                    + "'" + user.getApellido2() + "'" + ","
                    + "'" + user.getFechNac() + "'" + ","
                    + user.getIdProvincia() + ","
                    + user.getIdCanton() + ","
                    + user.getIdDistrito() + ","
                    + "'" + user.getOtrasSennas() + "'" + ","
                    + "'" + user.getEmail() + "'" + ","
                    + "'" + user.getIdSede() + "'" + ","
                    + user.getCodSeg() + ","
                    + "'" + user.getPassword() + "'" + ","
                    + user.getIdBarrio() + ","
                    + user.getIdPerfil() + ","
                    + "'" + user.getEstadoSolicitud() + "'" + ","
                    + "'" + user.getFechaSolicitud() + "'" + ","
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
     * Consulta si un usuario ya existe
     *
     * @param ID
     * @return boolean
     * @throws SNMPExceptions
     * @throws SQLException
     */
    public boolean consultarUsuario(String ID)
            throws SNMPExceptions, SQLException {

        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from Usuario where ID = " + "'" + ID + "'";

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

}

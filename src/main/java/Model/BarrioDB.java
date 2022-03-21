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
public class BarrioDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    
    public BarrioDB() {
    }

    public BarrioDB(Connection conn) {
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

    public LinkedList<Barrio> moID(int idProvincia, int idCanton, int idDistrito) throws SNMPExceptions,
            SQLException {
        String select = "";
        LinkedList<Barrio> listaBar = new LinkedList<Barrio>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT ID, Descripcion, idCanton, idProvincia, idDistrito FROM Barrio WHERE idProvincia = " + idProvincia + " and idCanton = " + idCanton + " and idDistrito = " + idDistrito;
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int codigoProvincia = rsPA.getInt("idProvincia");
                int codigoCanton = rsPA.getInt("idCanton");
                int codigoDistrito = rsPA.getInt("idDistrito");
                int ID = rsPA.getInt("ID");
                String descripcion = rsPA.getString("Descripcion");

                Barrio perProCanDis = new Barrio(ID,descripcion,codigoProvincia,codigoCanton,codigoDistrito);
                listaBar.add(perProCanDis);
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

        return listaBar;
    }
}

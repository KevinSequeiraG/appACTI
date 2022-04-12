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
public class TipoIDDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public TipoIDDB() {
        super();
    }

    public TipoIDDB(Connection conn) {
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

    
    public  LinkedList<TipoID> moTipoID() throws SNMPExceptions, 
            SQLException {
      String select = "";
      LinkedList<TipoID> listaTipoID = new LinkedList<TipoID>();
          
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = "SELECT ID, Descripcion FROM TipoID";
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                String descripcion = rsPA.getString("Descripcion");
                
                TipoID perTipo = new TipoID(ID,descripcion);
                listaTipoID.add(perTipo);
              }
              rsPA.close();
              
          } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          }catch (Exception e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());
          } finally {
              
          }
          
          return listaTipoID;
      }
}

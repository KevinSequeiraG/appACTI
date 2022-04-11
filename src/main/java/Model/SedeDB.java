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
public class SedeDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public SedeDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public SedeDB() {
        super();
    }

    public  LinkedList<Sede> moTodo() throws SNMPExceptions, 
            SQLException {
      String select = "";
      LinkedList<Sede> listaSedes = new LinkedList<Sede>();
          
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = 
                      "SELECT ID,DESCRIPCION FROM Sede";
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                String id = rsPA.getString("ID");
                String descripcion = rsPA.getString("DESCRIPCION");
                
                Sede perSede = new Sede(id, descripcion);
                listaSedes.add(perSede);
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
          return listaSedes;
      }
}

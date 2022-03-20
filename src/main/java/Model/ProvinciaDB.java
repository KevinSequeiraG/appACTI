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
 * @author kevin
 */
public class ProvinciaDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public ProvinciaDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public ProvinciaDB() {
        super();
    }

    public  LinkedList<Provincia> moTodo() throws SNMPExceptions, 
            SQLException {
      String select = "";
      LinkedList<Provincia> listaPro = new LinkedList<Provincia>();
          
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = 
                      "SELECT ID,DESCRIPCION FROM Provincia";
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                int codigoProvincia = rsPA.getInt("ID");
                String dscProvincia = rsPA.getString("DESCRIPCION");
                
                Provincia perProvincia = new Provincia(codigoProvincia, dscProvincia);
                listaPro.add(perProvincia);
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
          return listaPro;
      }
}

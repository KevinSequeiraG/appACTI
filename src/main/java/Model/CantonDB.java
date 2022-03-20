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
public class CantonDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    private LinkedList<Canton> listaC = new LinkedList<Canton>();

    public CantonDB() {
    }

    public CantonDB(Connection conn) {
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

    public LinkedList<Canton> getListaC() {
        return listaC;
    }

    public void setListaC(LinkedList<Canton> listaC) {
        this.listaC = listaC;
    }
    
    public  LinkedList<Canton> moID(int idProvincia) throws SNMPExceptions, 
            SQLException {
      String select = "";
      LinkedList<Canton> listaCan = new LinkedList<Canton>();
          
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = 
                      "SELECT * FROM Canton WHERE idProvincia = "+idProvincia;
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                int codigoProvincia = rsPA.getInt("idProvincia");
                int ID = rsPA.getInt("ID");
                String descripcion = rsPA.getString("Descripcion");
                
                Canton perProvincia = new Canton(ID,descripcion,codigoProvincia);
                listaCan.add(perProvincia);
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
          
          return listaCan;
      }
    
    
}

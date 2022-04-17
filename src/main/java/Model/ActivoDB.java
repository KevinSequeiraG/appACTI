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
 * @author Oscar
 */
public class ActivoDB {
     private AccesoDatos accesoDatos = new AccesoDatos();
     private Connection conn;
     private LinkedList<Activo> listaA = new LinkedList<Activo>();

    public ActivoDB() {
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

    public LinkedList<Activo> getListaA() {
        return listaA;
    }

    public void setListaA(LinkedList<Activo> listaA) {
        this.listaA = listaA;
    }
    
    /**
     * Insertar Activo
     *
     * @param  activo
     * @throws SNMPExceptions
     * @throws SQLException return void
     */
    public void InsertarActivo(Activo activo) throws SNMPExceptions,
            SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Usuario
            strSQL = "INSERT INTO [dbo].[Activo]"
                    + " ([ID]"
                    + ",[Descripcion]"
                    + ",[Valor]"
                    + ",[idSede]"
                    + ",[FechaRegistro]"
                    + ",[idUsuario]"
                    + ",[Cantidad]"
                    + ")"
                    + " VALUES"
                    + "(" + "'" + activo.getID() + "'" + ","
                    + "'" +activo.getDescripcion()+ "'" + ","
                    + activo.getValor() + ","
                    + "'" + activo.getIdSede()+ "'" + ","
                    + "'" + activo.getFechaRegistro()+ "'" + ","
                    + "'" + activo.getIdUsuario()+ "'" + ","
                    + activo.getCantidad()
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
    
    public boolean consultarActivo(String ID)
            throws SNMPExceptions, SQLException {

        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from Activo where ID = " + "'" + ID + "'";

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
    
    public int consultarCantidad(String ID) throws SNMPExceptions, 
            SQLException {
      String select = "";
          int cant = 0;
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = "SELECT Cantidad FROM Activo where ID ='"+ ID + "'" ;
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {
                cant = rsPA.getInt("Cantidad");
                
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
          
         return cant; 
      }
     
}

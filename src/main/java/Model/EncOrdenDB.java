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
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author erick
 */
public class EncOrdenDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public EncOrdenDB() {
        super();
    }

    public EncOrdenDB(Connection conn) {
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
     * Insertar EncabezadoOrden
     *
     * @param orden
     * @throws SNMPExceptions
     * @throws SQLException return void
     */
    public void InsertarEncOrden(EncOrden orden) throws SNMPExceptions,
            SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Usuario
            strSQL = "INSERT INTO [dbo].[OrdenEncabezadoActivo]\n"
                    + "([Descripcion]"
                    + ",[idUserEntrega]"
                    + ",[idUserRecibe]"
                    + ",[TipoOrden]"
                    + ",[Estado]"
                    + ",[FechaOrden]"
                    + ",[idSedeDestino])"
                    + " VALUES"
                    + "(" + "'" + orden.getDescripcion() + "'" + ","
                    + "'" + orden.getIdUserEntrega() + "'" + ","
                    + "'" + orden.getIdUserRecibe() + "'" + ","
                    + "'" + orden.getTipoOrden() + "'" + ","
                    + "'" + orden.getEstado() + "'" + ","
                    + "'" + orden.getFechaOrden() + "'" + ","
                    + "'" + orden.getIdSedeDestino() + "'"
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
     * Verificar el último ID generado en EncOrden
     *
     * @return
     */
    public int UltimoID() throws SNMPExceptions,
            SQLException {
        String select = "";
        int ultimo = 0;
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "Select max(ID) as ulltimo from OrdenEncabezadoActivo";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                ultimo = rsPA.getInt("ulltimo");
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

        return ultimo;
    }

    /**
     * Verificar que no se repitan ID's de EncabezadoOrden
     *
     * @param num
     * @return boolean
     */
    public boolean ExisteOrden(int num) throws SNMPExceptions, SQLException {
        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from OrdenEncabezadoActivo where ID = " + num;

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

    /**
     * Lista de órdenes estado 'P'
     *
     * @return ArrayList
     * @throws SNMPExceptions
     * @throws SQLException
     */
    public EncOrden DatosOrden(int ID) throws SNMPExceptions, SQLException {
        String select = "";
        EncOrden ordenP = new EncOrden();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT [ID], [idUserRecibe],[idSedeDestino] FROM [dbo].[OrdenEncabezadoActivo] WHERE [ID] = " + ID;
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                ordenP = new EncOrden();
                ordenP.ID = rsPA.getInt("ID");
                ordenP.idUserRecibe = rsPA.getString("idUserRecibe");
                ordenP.idSedeDestino = rsPA.getString("idSedeDestino");
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return ordenP;
    }

    /**
     * Lista de órdenes estado 'P'
     *
     * @return ArrayList
     * @throws SNMPExceptions
     * @throws SQLException
     */
    public ArrayList<EncOrden> ListaOrdenesPendientes() throws SNMPExceptions, SQLException {
        SedeDB sedeDB = new SedeDB();
        String select = "";
        EncOrden ordenP;
        ArrayList<EncOrden> lista = new ArrayList<EncOrden>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT [ID],[Descripcion],[idUserEntrega],[idUserRecibe],[TipoOrden],[Estado],[FechaOrden],[idSedeDestino] FROM [dbo].[OrdenEncabezadoActivo] WHERE [Estado] = 'P'";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                ordenP = new EncOrden();
                ordenP.ID = rsPA.getInt("ID");
                ordenP.Descripcion = rsPA.getString("Descripcion");
                ordenP.idUserEntrega = rsPA.getString("idUserEntrega");
                ordenP.idUserRecibe = rsPA.getString("idUserRecibe");
                ordenP.FechaOrdenD = rsPA.getDate("FechaOrden");
                ordenP.idSedeDestino = sedeDB.sedePorIdD(rsPA.getString("idSedeDestino"));

                lista.add(ordenP);
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

    /**
     * Lista de órdenes (Préstamos)
     *
     * @return ArrayList
     * @throws SNMPExceptions
     * @throws SQLException
     */
    public ArrayList<EncOrden> ListaPrestamosPendientes() throws SNMPExceptions, SQLException {
        SedeDB sedeDB = new SedeDB();
        String select = "";
        EncOrden ordenP;
        ArrayList<EncOrden> lista = new ArrayList<EncOrden>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT [ID],[Descripcion],[idUserEntrega],[idUserRecibe],[TipoOrden],[Estado],[FechaOrden],[idSedeDestino] FROM [dbo].[OrdenEncabezadoActivo] WHERE [TipoOrden] = 'Préstamo' AND [Estado] = 'P'";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                ordenP = new EncOrden();
                ordenP.ID = rsPA.getInt("ID");
                ordenP.Descripcion = rsPA.getString("Descripcion");
                ordenP.idUserEntrega = rsPA.getString("idUserEntrega");
                ordenP.idUserRecibe = rsPA.getString("idUserRecibe");
                ordenP.FechaOrdenD = rsPA.getDate("FechaOrden");
                ordenP.idSedeDestino = sedeDB.sedePorIdD(rsPA.getString("idSedeDestino"));

                lista.add(ordenP);
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

    /**
     * Lista de órdenes (Préstamos)
     *
     * @return ArrayList
     * @throws SNMPExceptions
     * @throws SQLException
     */
    public ArrayList<EncOrden> ListaTrasladosPendientes() throws SNMPExceptions, SQLException {
        SedeDB sedeDB = new SedeDB();
        String select = "";
        EncOrden ordenP;
        ArrayList<EncOrden> lista = new ArrayList<EncOrden>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT [ID],[Descripcion],[idUserEntrega],[idUserRecibe],[TipoOrden],[Estado],[FechaOrden],[idSedeDestino] FROM [dbo].[OrdenEncabezadoActivo] WHERE [TipoOrden] = 'Traslado' AND [Estado] = 'P'";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                ordenP = new EncOrden();
                ordenP.ID = rsPA.getInt("ID");
                ordenP.Descripcion = rsPA.getString("Descripcion");
                ordenP.idUserEntrega = rsPA.getString("idUserEntrega");
                ordenP.idUserRecibe = rsPA.getString("idUserRecibe");
                ordenP.FechaOrdenD = rsPA.getDate("FechaOrden");
                ordenP.idSedeDestino = sedeDB.sedePorIdD(rsPA.getString("idSedeDestino"));

                lista.add(ordenP);
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

    /**
     * Aprobar EncabezadoOrden
     *
     * @param ID
     * @param orden
     * @throws SNMPExceptions
     * @throws SQLException return void
     */
    public boolean AprobarOrden(int ID, ArrayList<OrdenDetalle> listaD) throws SNMPExceptions,
            SQLException {
        String strSQL = "";
        ActivoDB activo = new ActivoDB();
        EncOrden orden = this.DatosOrden(ID);

        try {
            //Se cambia el valor del Estado a A de Aprobado
            //La fecha se agarra del sistema
            
            for (OrdenDetalle ordenDetalle : listaD) {
                //Por cada activo de la lineaDetalle se cambia la sede, el funcionario dueño y se cambia la cantidad
                if (activo.consultarCantidad(ordenDetalle.idActivo) >= ordenDetalle.cant) {
                    strSQL = "Update Activo set idSede = '" + orden.idSedeDestino + "' , idUsuario= '" + orden.idUserRecibe + "' where ID = '" + ordenDetalle.idActivo + "'";
                    accesoDatos.ejecutaSQL(strSQL);
                }
                else{
                    return false;
                }

            }
            strSQL = "Update OrdenEncabezadoActivo set Estado = 'A', FechaRecepcion = GETDATE() WHERE ID = " + orden.ID;
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
            return true;
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    
    public boolean RechazarOrden(int ID) throws SNMPExceptions,
            SQLException {
        String strSQL = "";
        ActivoDB activo = new ActivoDB();

        try {           
            strSQL = "Update OrdenEncabezadoActivo set Estado = 'R', FechaRecepcion = GETDATE() WHERE ID = " + ID;
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
            return true;
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public LinkedList<SelectItem> listaTipoOrden() {
        LinkedList ListaConsulta = new LinkedList();
        ListaConsulta.add(new SelectItem("Seleccione Tipo"));
        ListaConsulta.add(new SelectItem("Traslado"));
        ListaConsulta.add(new SelectItem("Préstamo"));
        return ListaConsulta;
    }

}

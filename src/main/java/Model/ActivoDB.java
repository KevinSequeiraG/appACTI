/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
     * @param activo
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
                    + "'" + activo.getDescripcion() + "'" + ","
                    + activo.getValor() + ","
                    + "'" + activo.getIdSede() + "'" + ","
                    + "'" + activo.getFechaRegistro() + "'" + ","
                    + "'" + activo.getIdUsuario() + "'" + ","
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

    public ArrayList<Activo> consultarActivosPorFunc(String idUsuario) throws SNMPExceptions,
            SQLException {
        String select = "";
        SedeDB sedeDB = new SedeDB();
        ArrayList<Activo> lista = new ArrayList<>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de b??squeda
            select
                    = "SELECT * FROM Activo WHERE idUsuario = " + "'" + idUsuario + "'";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String ID = rsPA.getString("ID");
                String dsc = rsPA.getString("Descripcion");
                float valor = rsPA.getFloat("Valor");
                String idSede = sedeDB.sedePorIdD(rsPA.getString("idSede"));
                String fechaRegistro = rsPA.getString("FechaRegistro").toString();
                int cantidad = rsPA.getInt("Cantidad");
                Activo perActivo = new Activo(ID, dsc, valor, idSede, fechaRegistro, cantidad);
                lista.add(perActivo);
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
        return lista;
    }

    public int consultarCantidad(String ID) throws SNMPExceptions,
            SQLException {
        String select = "";
        int cant = 0;
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de b??squeda
            select = "SELECT Cantidad FROM Activo where ID ='" + ID + "'";
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
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }

        return cant;
    }

    public ArrayList<Activo> ReportePorFechas(String fecha1, String fecha2) throws SNMPExceptions, SQLException {
        String select = "";
        ArrayList<Activo> lista = new ArrayList<>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de b??squeda
            select = "select distinct Activo.ID, Activo.Descripcion, Valor, (select Descripcion from Sede where ID=idSede) as Sede, FechaRegistro, idUsuario, TipoOrden from Activo, OrdenDetalleActivo, OrdenEncabezadoActivo where Activo.id=idActivo and idOrdenEncabezadoActivo=OrdenEncabezadoActivo.ID and FechaRegistro between '"+fecha1+"' and '"+fecha2+"'";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String ID = rsPA.getString("ID");
                String desc = rsPA.getString("Descripcion");
                float valor = rsPA.getFloat("Valor");
                String Sede = rsPA.getString("Sede");
                String fechaReg = rsPA.getString("FechaRegistro");
                String idUsuario = rsPA.getString("idUsuario");
                String TipoOrden = rsPA.getString("TipoOrden");

                Activo activo = new Activo();
                activo.setID(ID);
                activo.setDescripcion(desc);
                activo.setValor(valor);
                activo.setIdSede(Sede);
                activo.setFechaRegistro(fechaReg);
                activo.setIdUsuario(idUsuario);
                activo.setTipoMovimiento(TipoOrden);
                lista.add(activo);
            }
            
            //Se crea la sentencia de b??squeda
            select = "select Activo.ID, Activo.Descripcion, Valor, (select Descripcion from Sede where ID=idSede) as Sede, FechaRegistro, idUsuario from Activo left join OrdenDetalleActivo on Activo.ID=OrdenDetalleActivo.idActivo where OrdenDetalleActivo.idActivo is null and FechaRegistro between '"+fecha1+"' and '"+fecha2+"'";
            //Se ejecuta la sentencia SQL
            rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String ID = rsPA.getString("ID");
                String desc = rsPA.getString("Descripcion");
                float valor = rsPA.getFloat("Valor");
                String Sede = rsPA.getString("Sede");
                String fechaReg = rsPA.getString("FechaRegistro");
                String idUsuario = rsPA.getString("idUsuario");
                String TipoOrden = "Asignado";

                Activo activo = new Activo();
                activo.setID(ID);
                activo.setDescripcion(desc);
                activo.setValor(valor);
                activo.setIdSede(Sede);
                activo.setFechaRegistro(fechaReg);
                activo.setIdUsuario(idUsuario);
                activo.setTipoMovimiento(TipoOrden);
                lista.add(activo);
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

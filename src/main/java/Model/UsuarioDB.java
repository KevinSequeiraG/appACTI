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
 * @author erick
 */
public class UsuarioDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    private LinkedList<Usuario> listaU = new LinkedList<Usuario>();

    public UsuarioDB() {
        super();
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
                    + "'" + user.getFechaSolicitud() + "'"
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
    public ArrayList<Usuario> ListaFuncionarios(int tipoPerfil) throws SNMPExceptions, SQLException {
        String select = "";
        Usuario user;
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de b??squeda
            select = "select ID, idTipoID, Nombre,Apellido1, Apellido2, FechNac, Email, (select Descripcion from Sede where id = idSede) as Sede, EstadoSolicitud from Usuario where idPerfil=" + tipoPerfil;
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String ID = rsPA.getString("ID");
                int idTipoID = rsPA.getInt("idTipoID");
                String nombre = rsPA.getString("Nombre");
                String apellido1 = rsPA.getString("Apellido1");
                String apellido2 = rsPA.getString("Apellido2");
                Date fechaNac = rsPA.getDate("FechNac");
                String email = rsPA.getString("Email");
                String sede = rsPA.getString("Sede");
                char EstadoSoli = rsPA.getString("EstadoSolicitud").charAt(0);

                user = new Usuario(ID, idTipoID, nombre, apellido1, apellido2, fechaNac, email, sede, EstadoSoli);
                lista.add(user);
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

    public void eliminarFuncionario(String ID) throws SNMPExceptions, SQLException {
        String select = "";
        try {
            if (consultarUsuario(ID)) {
                //Se intancia la clase de acceso a datos
                AccesoDatos accesoDatos = new AccesoDatos();

                //Se borran primero los numeros pertenecientes al user
                select = "delete from telefono where idUsuario = " + "'" + ID + "'";
                accesoDatos.ejecutaSQL(select);

                //Se borran primero las lineasDetalle pertenecientes al user
                select = "Select ID FROM OrdenEncabezadoActivo where idUserEntrega = " + "'" + ID + "' or idUserRecibe= " + "'" + ID + "'";
                ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
                while (rsPA.next()) {
                    String orden = rsPA.getString("ID");
                    select = "delete from OrdenDetalleActivo where idOrdenEncabezadoActivo = " + "'" + orden + "'";
                    accesoDatos.ejecutaSQL(select);
                }
                
//                select = "Select ID FROM OrdenEncabezadoActivo where idUserRecibe= " + "'" + ID + "'";
//                ResultSet rsPA2 = accesoDatos.ejecutaSQLRetornaRS(select);
//                while (rsPA2.next()) {
//                    String orden = rsPA2.getString("ID");
//                    select = "delete from OrdenDetalleActivo where idOrdenEncabezadoActivo = " + "'" + orden + "')";
//                    accesoDatos.ejecutaSQL(select);
//                }
                
                select = "Select ID FROM Activo where idUsuario = " + "'" + ID + "'";
                ResultSet rsPA3 = accesoDatos.ejecutaSQLRetornaRS(select);
                while (rsPA3.next()) {
                    String orden = rsPA3.getString("ID");
                    select = "delete from OrdenDetalleActivo where idActivo = " + "'" + orden + "'";
                    accesoDatos.ejecutaSQL(select);
                }
                

                //Se borran primero los activos pertenecientes al user
                select = "Update Activo set idUsuario = null where idUsuario = " + "'" + ID + "'";
                accesoDatos.ejecutaSQL(select);

                //Se borran primero las ordenes pertenecientes al user
                select = "delete from OrdenEncabezadoActivo where idUserEntrega = " + "'" + ID + "'";
                accesoDatos.ejecutaSQL(select);
                select = "delete from OrdenEncabezadoActivo where idUserRecibe = " + "'" + ID + "'";
                accesoDatos.ejecutaSQL(select);

                select = "delete from Usuario where ID = " + "'" + ID + "'";
                accesoDatos.ejecutaSQL(select);

                rsPA.close();
                //rsPA2.close();
                rsPA3.close();
            }
            

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public void editarFuncionario(String ID, int idPerfil, int idTipoID, String nombre, String apellido1, String apellido2, String fechaNac, String email, String Sede, int idProvincia, int idCanton, int idDistrito, int idBarrio, String otrasSennas, char estadoSoli) throws SNMPExceptions, SQLException {
        String select = "";

        try {
            if (consultarUsuario(ID)) {
                //Se intancia la clase de acceso a datos
                AccesoDatos accesoDatos = new AccesoDatos();

                //Se borran primero los numeros pertenecientes al user
                select = "update usuario set idTipoID=" + idTipoID + ", idPerfil=" + idPerfil + ", nombre='" + nombre + "', apellido1='" + apellido1 + "', apellido2='" + apellido2 + "', fechnac='" + fechaNac + "'," + "Email='" + email + "', idSede='" + Sede + "', idProvincia=" + idProvincia + ", idCanton=" + idCanton + ",idDistrito=" + idDistrito + ",idBarrio=" + idBarrio + ",OtraSennas='" + otrasSennas + "', estadoSolicitud='" + estadoSoli + "' where id='" + ID + "'";
                System.out.println(select);
                accesoDatos.ejecutaSQL(select);

            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

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

    public Usuario retornarUsuario(String ID) throws SNMPExceptions, SQLException {
        Usuario user = new Usuario();

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

                String IDu = rsPA.getString("ID");
                int idTipoID = rsPA.getInt("idTipoID");
                String nombre = rsPA.getString("Nombre");
                String apellido1 = rsPA.getString("Apellido1");
                String apellido2 = rsPA.getString("Apellido2");
                Date fechaNac = rsPA.getDate("FechNac");
                int idProvincia = rsPA.getInt("idProvincia");
                int idCanton = rsPA.getInt("idCanton");
                int idDistrito = rsPA.getInt("idDistrito");
                String otrasSennas = rsPA.getString("OtraSennas");
                String email = rsPA.getString("Email");
                String sede = rsPA.getString("idSede");
                int codSeg = rsPA.getInt("CodSec");
                String password = rsPA.getString("Password");
                int idBarrio = rsPA.getInt("idBarrio");
                char EstadoSoli = rsPA.getString("EstadoSolicitud").charAt(0);

                user.setID(IDu);
                user.setIdTipoID(idTipoID);
                user.setNombre(nombre);
                user.setApellido1(apellido1);
                user.setApellido2(apellido2);
                user.setFechNac(fechaNac.toString());
                user.setIdProvincia(idProvincia);
                user.setIdCanton(idCanton);
                user.setIdDistrito(idDistrito);
                user.setOtrasSennas(otrasSennas);
                user.setEmail(email);
                user.setIdSede(sede);
                user.setCodSeg(codSeg);
                user.setPassword(password);
                user.setIdBarrio(idBarrio);
                user.setEstadoSolicitud(EstadoSoli);
            }

            rsPA.close();

            return user;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public int consultarPerfil(String Login) throws SNMPExceptions,
            SQLException {
        String select = "";
        int idPerfil = 0;
        Usuario user = new Usuario();
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de b??squeda
            select = "SELECT ID, idPerfil FROM Usuario where ID ='" + Login + "'";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                idPerfil = rsPA.getInt("idPerfil");
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

        return idPerfil;
    }

    public static Usuario login(String Login) throws SNMPExceptions,
            SQLException {
        String select = "";
        Usuario user = new Usuario();
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de b??squeda
            select = "SELECT ID, idPerfil, Password, EstadoSolicitud FROM Usuario where ID ='" + Login + "'";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String ID = rsPA.getString("ID");
                String Password = rsPA.getString("Password");
                int idPerfil = rsPA.getInt("idPerfil");
                char EstadoSolicitud = rsPA.getString("EstadoSolicitud").charAt(0);

                user = new Usuario(ID, idPerfil, Password, EstadoSolicitud);
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

        return user;
    }

    public static Usuario Autenticar(String ID, String Password) throws SNMPExceptions,
            SQLException {

        Usuario u = login(ID);

        if (u.ID.equals(ID) && u.Password.equals(Password)) {
            if (u.EstadoSolicitud == 'A') {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Usuario> ListaAprobacionFunc() throws SNMPExceptions, SQLException {
        String select = "";
        Usuario user;
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de b??squeda
            select = "select ID, idTipoID, Nombre,Apellido1, Apellido2, FechNac, Email, (select Descripcion from Sede where id = idSede) as Sede from Usuario where estadoSolicitud = 'P' ";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String ID = rsPA.getString("ID");
                int idTipoID = rsPA.getInt("idTipoID");
                String nombre = rsPA.getString("Nombre");
                String apellido1 = rsPA.getString("Apellido1");
                String apellido2 = rsPA.getString("Apellido2");
                Date fechaNac = rsPA.getDate("FechNac");
                String email = rsPA.getString("Email");
                String sede = rsPA.getString("Sede");

                user = new Usuario(ID, idTipoID, nombre, apellido1, apellido2, fechaNac, email, sede);
                lista.add(user);
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

    public void aceptarFuncionarios(String ID) throws SNMPExceptions, SQLException {
        String select = "";

        try {
            if (consultarUsuario(ID)) {
                //Se intancia la clase de acceso a datos
                AccesoDatos accesoDatos = new AccesoDatos();

                select = "update usuario set estadoSolicitud='A' where id='" + ID + "'";
                System.out.println(select);
                accesoDatos.ejecutaSQL(select);

            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public void contraPorPrimeraVez(String ID, String password) throws SNMPExceptions, SQLException {
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();
            if (consultarUsuario(ID)) {
                //Se crea la sentencia de Busqueda
                select = "Update Usuario set Password = '" + password + "' where ID = " + "'" + ID + "'";

                //se ejecuta la sentencia sql
                accesoDatos.ejecutaSQL(select);
                //se llama el array con los proyectos
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
}

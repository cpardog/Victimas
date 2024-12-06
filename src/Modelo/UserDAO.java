package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final String SQL_SELECT = "SELECT * FROM users";
    private static final String SQL_INSERT = "INSERT INTO users (nombre,apellido) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE users SET nombre=?, apellido=? WHERE id = ? ";
    private static final String SQL_DELETE = "DELETE FROM users WHERE id=?";
    private static final String SQL_CANT_USER = "SELECT COUNT(*)  FROM users WHERE login=? AND clave=?";
    private static final String SQL_SELECT_USER = "SELECT rolnombre FROM users WHERE login=? AND clave=?";

    public List<User> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User usuario = null;
        List<User> usuarios;
        usuarios = new ArrayList<>();

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //Identificacion de registro a nivel de base de datos
                int id = rs.getInt("id");

                //Datos personales
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                usuario = new User();
                usuario.setId_usuario(id);
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuarios.add(usuario);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuarios;
    }

    public int insert(User usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);

            //Datos personales
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            // Se  deben setear el resto de parametros

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(User usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConexion();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            //Datos personales
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setInt(4, usuario.getId_usuario());

            rows = stmt.executeUpdate();
            System.out.println("Registro actualizado:" + rows);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(User usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConexion();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getId_usuario());
            rows = stmt.executeUpdate();
            System.out.println("Registro eliminado:" + rows);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int selectcantuser(String user, String clave) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User usuario = null;
        int qres = 0;
        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SQL_CANT_USER);
            ps.setString(1, user.trim());
            ps.setString(2, clave.trim());
            rs = ps.executeQuery();
            rs.next();
            //rs.next();
            //Identificacion de registro a nivel de base de datos
            // int id_res
            //Datos de Resolucion
            qres = rs.getInt(1);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return qres;
    }

    public boolean detAdminusuario(String login, String clave) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User usuario = null;
        boolean resp=false;
        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SQL_SELECT_USER);
            ps.setString(1, login);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            rs.next();
            //rs.next();
            //Identificacion de registro a nivel de base de datos
            // int id_res
            //Datos de Resolucion
            String myrol="";
            myrol =rs.getString(1).trim();
            if (myrol.equals("admin")) {
                resp = true;
            }
            else
            {
                resp = false;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return resp;

    }
}

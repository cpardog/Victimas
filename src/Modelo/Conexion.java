package Modelo;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class Conexion {

    //public static final String URL = "jdbc:mysql://192.168.80.103:3306/pruebajava";
    public static final String URL = "jdbc:mysql://192.168.20.24:3306/pruebajava";
    public static final String USER = "root";
    public static final String CLAVE = "12345";

    private Conexion() {
    }

    public static Connection getConexion() throws ClassNotFoundException {

        Connection cnx = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection(URL, USER, CLAVE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return cnx;
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void close(Connection cnx) {
        try {
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}

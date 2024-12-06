package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitanteDAO {

    private static final String SQL_SELECT = "SELECT * FROM solicitante";
    private static final String SQL_INSERT = "INSERT INTO solicitante (motivo_visita, tipo_Doc, \n"
            + "            num_doc, primer_nombre, segundo_nombre, \n"
            + "            primer_apellido, segundo_apellido, barrio, \n"
            + "            direccion, celular, cabeza_hogar, tercera_edad, \n"
            + "            genero, edad, registrado_por, fecha_registro) VALUES(?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    private static final String SQL_UPDATE = "UPDATE solicitante SET motivo_visita=?, tipo_doc=?, num_doc=?, primer_nombre=? , \n"
            + "segundo_nombre=?, primer_apellido=?, segundo_apellido=?, barrio=? ,\n "
            + " direccion=?, celular=?, cabeza_hogar=?, tercera_edad=?, genero=?, \n"
            + " registrado_por=?, fecha_registro=?  WHERE id_solicitante = ?";
    private static final String SQL_DELETE = "DELETE FROM solicitante WHERE id_solicitante=?";

    public List<Solicitante> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Solicitante solicitante = null;
        List<Solicitante> solicitantes = new ArrayList<Solicitante>();

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //Identificacion de registro a nivel de base de datos
                int id_solicitante = rs.getInt("id_solicitante");
                //Motivo de la visita
                String motivo = rs.getString("motivo_visita");
                //Datos personales
                String tipo_doc = rs.getString("tipo_doc");
                String num_doc = rs.getString("num_doc");
                String pnombre = rs.getString("primer_nombre");
                String snombre = rs.getString("segundo_nombre");
                String papellido = rs.getString("primer_apellido");
                String sapellido = rs.getString("segundo_apellido");
                //Ubicacion
                String barrio = rs.getString("barrio");
                String direccion = rs.getString("direccion");
                String celular = rs.getString("celular");
                //Caracterizaciones
                boolean cabeza = rs.getBoolean("cabeza_hogar");
                boolean tercera = rs.getBoolean("tercera_edad");
                String genero = rs.getString("genero");
                String regpor = rs.getString("registrado_por");
                Date fechareg = rs.getDate("fecha_registro");

                solicitante = new Solicitante();
                solicitante.setId_Solicitante(id_solicitante);
                solicitante.setMotivo_Visita(motivo);
                solicitante.setTipo_Documento(tipo_doc);
                solicitante.setNum_Documento(num_doc);
                solicitante.setPrimer_Nombre(pnombre);
                solicitante.setSegundo_Nombre(snombre);
                solicitante.setPrimer_Apellido(papellido);
                solicitante.setSegundo_Apellido(sapellido);
                solicitante.setBarrio(barrio);
                solicitante.setDireccion(direccion);
                solicitante.setCelular(celular);
                solicitante.setCabeza_Hogar(cabeza);
                solicitante.setTercera_Edad(tercera);
                solicitante.setGenero(genero);
                solicitante.setRegistrado_Por(regpor);
                solicitante.setFecha_Registro(fechareg);

                solicitantes.add(solicitante);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return solicitantes;
    }

    public int insert(Solicitante solicitante) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            //Motivo visita
            stmt.setString(1, solicitante.getMotivo_Visita());
            //Datos personales
            stmt.setString(2, solicitante.getTipo_Documento());
            stmt.setString(3, solicitante.getNum_Documento());
            stmt.setString(4, solicitante.getPrimer_Nombre());
            stmt.setString(5, solicitante.getSegundo_Nombre());
            stmt.setString(6, solicitante.getPrimer_Apellido());
            stmt.setString(7, solicitante.getSegundo_Apellido());
            //Ubicación
            stmt.setString(8, solicitante.getBarrio());
            stmt.setString(9, solicitante.getDireccion());
            stmt.setString(10, solicitante.getCelular());
            //Caracterizaciones
            stmt.setBoolean(11, solicitante.isCabeza_Hogar());
            stmt.setBoolean(12, solicitante.isTercera_Edad());
            stmt.setString(13, solicitante.getGenero());
            stmt.setString(14, solicitante.getRegistrado_Por());
            stmt.setDate(15, (Date) solicitante.getFecha_Registro());

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

    public int update(Solicitante solicitante) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConexion();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            //Motivo visita
            stmt.setString(1, solicitante.getMotivo_Visita());
            //Datos personales
            stmt.setString(2, solicitante.getTipo_Documento());
            stmt.setString(3, solicitante.getNum_Documento());
            stmt.setString(4, solicitante.getPrimer_Nombre());
            stmt.setString(5, solicitante.getSegundo_Nombre());
            stmt.setString(6, solicitante.getPrimer_Apellido());
            stmt.setString(7, solicitante.getSegundo_Apellido());
            //Ubicación
            stmt.setString(8, solicitante.getBarrio());
            stmt.setString(9, solicitante.getDireccion());
            stmt.setString(10, solicitante.getCelular());
            //Caracterizaciones
            stmt.setBoolean(11, solicitante.isCabeza_Hogar());
            stmt.setBoolean(12, solicitante.isTercera_Edad());
            stmt.setString(13, solicitante.getGenero());
            stmt.setString(14, solicitante.getRegistrado_Por());
            stmt.setDate(15, (Date) solicitante.getFecha_Registro());
            stmt.setInt(16, solicitante.getId_Solicitante());
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(Solicitante solicitante) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConexion();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, solicitante.getId_Solicitante());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
}

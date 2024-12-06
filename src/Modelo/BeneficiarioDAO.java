package Modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BeneficiarioDAO {

    private static final String SQL_SELECT = "SELECT * FROM beneficiarios";
    private static final String SQL_INSERT = "INSERT INTO beneficiarios (resolucion_id,id_beneficiario,nombre_beneficiario,apellido_beneficiario) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE beneficiarios SET id_beneficiario=?,nombre_beneficiario=?, apellido_beneficiario=? WHERE id_beneficiario=?";
    private static final String SQL_DELETE = "DELETE FROM beneficiarios WHERE id_beneficiario=?";
    private static final String SQL_BUSCAR1 = "SELECT * FROM beneficiarios WHERE id_beneficiario LIKE  CONCAT('%',?,'%')";
    private static final String SQL_BUSCAR2 = "SELECT * FROM beneficiarios WHERE resolucion_id LIKE  CONCAT('%',?,'%')";
    private static final String SQL_SELECT_BENE = "SELECT * FROM beneficiarios WHERE resolucion_id=?";
    private static final String SQL_CANT_BENEF = "SELECT COUNT(*) as cantidad FROM beneficiarios WHERE resolucion_id=?";
//    private static final String SQL_POR_FECHA = "SELECT fecha_resolucion, count(*) as cantidad, month(fecha_resolucion) as mes, sum(monto_resolucion) as Total from resoluciones  group by fecha_resolucion  order by fecha_resolucion";
//    private static final String SQL_POR_MES = "SELECT month(fecha_resolucion) as mes, year(fecha_resolucion) as vigencia,  count(*)  as cantidad, sum(monto_resolucion) as Total from resoluciones  group by mes,vigencia order by vigencia,mes";

    public BeneficiarioDAO() {
    }
// Obtener Todos los beneficiarios de una resolucion
//  Se devuelven un listado de tipo ArrayList

    public ArrayList<Beneficiario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Beneficiario bene = null;
        ArrayList<Beneficiario> listado = new ArrayList<>();

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //Identificacion de registro a nivel de base de datos
                // int id_res
                //Datos de Resolucion
                int id_res = rs.getInt("resolucion_id");
                String id_beneficiario = rs.getString("id_beneficiario");
                String nombre_beneficiario = rs.getString("nombre_beneficiario");
                String apellido_beneficiario = rs.getString("apellido_beneficiario");
                // Las asigna al Objeto
                bene = new Beneficiario();
                bene.setResolucion_id(id_res);
                bene.setId_beneficiario(id_beneficiario);
                bene.setNombre_beneficiario(nombre_beneficiario);
                bene.setApellido_beneficiario(apellido_beneficiario);

                listado.add(bene);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return listado;
    }

// Obtener Solamente los beneficiarios de una resolucion específica
// Se envía la resolución como parámetro
//  Se devuelven un listado de tipo ArrayList
    public ArrayList<Beneficiario> selectbene(int num_res) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Beneficiario bene = null;
        ArrayList<Beneficiario> listado = new ArrayList<>();

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_BENE);
            stmt.setInt(1, num_res);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //Identificacion de registro a nivel de base de datos
                // int id_res
                //Datos de Resolucion
                int id_res = rs.getInt("resolucion_id");
                String id_beneficiario = rs.getString("id_beneficiario");
                String nombre_beneficiario = rs.getString("nombre_beneficiario");
                String apellido_beneficiario = rs.getString("apellido_beneficiario");
                // Las asigna al Objeto
                bene = new Beneficiario();
                bene.setResolucion_id(id_res);
                bene.setId_beneficiario(id_beneficiario);
                bene.setNombre_beneficiario(nombre_beneficiario);
                bene.setApellido_beneficiario(apellido_beneficiario);
                listado.add(bene);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return listado;
    }

 // Obtener Solamente los beneficiarios de una resolucion específica
// Se envía la resolución como parámetro
//  Se devuelve la cantidad total de beneficiarios
    public int selectcantbene(int numres) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Beneficiario bene = null;
        int qres = 0;
        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SQL_CANT_BENEF);
            ps.setInt(1, numres);
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

//    public ArrayList<Resumen> selectPorFecha() {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Resolucion resolucion = null;
//        ArrayList<Resumen> resumenes = new ArrayList<>();
//
//        try {
//            conn = Conexion.getConexion();
//            stmt = conn.prepareStatement(SQL_POR_FECHA);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                //Datos Resumen
////                int id_res = rs.getInt("id_resolucion");
////                String num_resolucion = rs.getString("num_resolucion");
//                String f_resol = rs.getString(String.valueOf("fecha_resolucion"));
//                String cantidad = rs.getString("cantidad");
//                String mes = rs.getString("mes");
//                String total_monto = rs.getString("Total");
////                String emitida_por = rs.getString("emitida_por");
////                java.sql.Date f_registro = rs.getDate(String.valueOf("fecha_registro"));
//                // Las asigna al Objeto Resumen
//                Resumen resumen = new Resumen();
////                resolucion.setId_resolucion(id_res);
////                resolucion.setNum_resolucion(num_resolucion);
//                resumen.setCampo1(mes);
//                resumen.setCampo2(cantidad);
//                resumen.setCampo3(mes);
//                resumen.setCampo4(total_monto);
////                resolucion.setEmitida_por(emitida_por);
////                resolucion.setFecha_registro(f_registro);
//                resumenes.add(resumen);
//            }
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            Conexion.close(rs);
//            Conexion.close(stmt);
//            Conexion.close(conn);
//        }
//
//        return resumenes;
//    }
//
//    public ArrayList<Resumen> selectPorMes() {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Resolucion resolucion = null;
//        ArrayList<Resumen> resumenes = new ArrayList<>();
//
//        try {
//            conn = Conexion.getConexion();
//            stmt = conn.prepareStatement(SQL_POR_MES);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                //Datos Resumen
////                int id_res = rs.getInt("id_resolucion");
////                String num_resolucion = rs.getString("num_resolucion");
//                String fecha = rs.getString(String.valueOf("mes"));
//                String vigencia = rs.getString("vigencia");
//                String cantidad = rs.getString("cantidad");
//                String total_monto = rs.getString("Total");
////                String emitida_por = rs.getString("emitida_por");
////                java.sql.Date f_registro = rs.getDate(String.valueOf("fecha_registro"));
//                // Las asigna al Objeto Resumen
//                Resumen resumen = new Resumen();
////                resolucion.setId_resolucion(id_res);
////                resolucion.setNum_resolucion(num_resolucion);
//                resumen.setCampo1(fecha);
//                resumen.setCampo2(vigencia);
//                resumen.setCampo3(cantidad);
//                resumen.setCampo4(total_monto);
////                resolucion.setEmitida_por(emitida_por);
////                resolucion.setFecha_registro(f_registro);
//                resumenes.add(resumen);
//            }
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            Conexion.close(rs);
//            Conexion.close(stmt);
//            Conexion.close(conn);
//        }
//
//        return resumenes;
//    }
// Sirve para insertar un registro en la base de datos
    public int insert(Beneficiario bene) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SQL_INSERT);

            //Datos de la resolucion
            ps.setInt(1, bene.getResolucion_id());
            ps.setString(2, bene.getId_beneficiario());
            ps.setString(3, bene.getNombre_beneficiario());
            ps.setString(4, bene.getApellido_beneficiario());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = ps.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }
// Sirve para actualizar un registro en la base de datos
    public int update(Beneficiario bene, String beneanterior) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConexion();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            //Datos del beneficiario
            //stmt.setInt(1, bene.getResolucion_id());
            stmt.setString(1, bene.getId_beneficiario());
            stmt.setString(2, bene.getNombre_beneficiario());
            stmt.setString(3, bene.getApellido_beneficiario());
            stmt.setString(4, beneanterior);
            //stmt.setString(4, bene.getId_beneficiario());

            rows = stmt.executeUpdate();
              JOptionPane.showMessageDialog(null, "Registro Actualizado"); 
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
// Sirve para eliminar un registro en la base de datos
    public int delete(Beneficiario bene) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = Conexion.getConexion();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setString(1, bene.getId_beneficiario());
            rows = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro eliminado"); 
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }

//    public ArrayList<Beneficiario> buscarPorCedulaPrincipal(String cadenabuscar) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Beneficiario beneficiario = null;
//        ArrayList<Resolucion> beneficiarios = new ArrayList<>();
//
//        try {
//            conn = Conexion.getConexion();
//            stmt = conn.prepareStatement(SQL_BUSCAR1);
//            stmt.setString(1, cadenabuscar.trim());
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                //Identificacion de registro a nivel de base de datos
//                // int id_res
//                //Datos de Resolucion
//                int id_res = rs.getInt("id_resolucion");
//                String num_resolucion = rs.getString("num_resolucion");
//                String cedula_principal = rs.getString("cedula_principal");
//                java.sql.Date f_resol = rs.getDate(String.valueOf("fecha_resolucion"));
//                int f_beneficiarias = rs.getInt("fam_beneficiarias");
//                Double monto = rs.getDouble("monto_resolucion");
//                String emitida_por = rs.getString("emitida_por");
//                java.sql.Date f_registro = rs.getDate(String.valueOf("fecha_registro"));
//                // Creo el objeto y las asigna al Objeto
//                beneficiario = new Resolucion();
//                beneficiario.setId_resolucion(id_res);
//                beneficiario.setNum_resolucion(num_resolucion);
//                beneficiario.setCedula_principal(cedula_principal);
//                resolucion.setFecha_resolucion(f_resol);
//                resolucion.setFam_beneficiarias(f_beneficiarias);
//                resolucion.setMonto_resolucion(monto);
//                resolucion.setEmitida_por(emitida_por);
//                resolucion.setFecha_registro(f_registro);
//                resoluciones.add(resolucion);
//            }
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            Conexion.close(rs);
//            Conexion.close(stmt);
//            Conexion.close(conn);
//        }
//        return beneficiarios;
//    }
//
//    public ArrayList<Beneficiario> buscarPorNumeroResolucion(String cadenabuscar) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Beneficiario beneficiario = null;
//        ArrayList<Beneficiario> beneficiarios = new ArrayList<>();
//
//        try {
//            conn = Conexion.getConexion();
//            stmt = conn.prepareStatement(SQL_BUSCAR2);
//            stmt.setString(1, cadenabuscar.trim());
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                //Identificacion de registro a nivel de base de datos
//                // int id_res
//                //Datos de Resolucion
//                int id_res = rs.getInt("id_resolucion");
//                String num_resolucion = rs.getString("num_resolucion");
//                String cedula_principal = rs.getString("cedula_principal");
//                java.sql.Date f_resol = rs.getDate(String.valueOf("fecha_resolucion"));
//                int f_beneficiarias = rs.getInt("fam_beneficiarias");
//                Double monto = rs.getDouble("monto_resolucion");
//                String emitida_por = rs.getString("emitida_por");
//                java.sql.Date f_registro = rs.getDate(String.valueOf("fecha_registro"));
//                // Creo el objeto y las asigna al Objeto
//                resolucion = new Resolucion();
//                resolucion.setId_resolucion(id_res);
//                resolucion.setNum_resolucion(num_resolucion);
//                resolucion.setCedula_principal(cedula_principal);
//                resolucion.setFecha_resolucion(f_resol);
//                resolucion.setFam_beneficiarias(f_beneficiarias);
//                resolucion.setMonto_resolucion(monto);
//                resolucion.setEmitida_por(emitida_por);
//                resolucion.setFecha_registro(f_registro);
//                resoluciones.add(resolucion);
//            }
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            Conexion.close(rs);
//            Conexion.close(stmt);
//            Conexion.close(conn);
//        }
//        return resoluciones;
//    }
}

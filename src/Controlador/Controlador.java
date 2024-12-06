/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Resolucion;
import Modelo.ResolucionDAO;
import Vista.FrmResoluciones;
import Vista.FrmPrincipal;
import Vista.FrmResumen;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;
import Modelo.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Admin
 */
public class Controlador implements ActionListener {

    private Resolucion resol;
    private ResolucionDAO resolDAO;
    public FrmPrincipal frP;
    public FrmResoluciones frC;
    public FrmResumen frR;
    public BeneficiarioDAO beneDAO;
    public Beneficiario bene;

    public Controlador() {
        frC = new FrmResoluciones();
        frP = new FrmPrincipal();
        resol = new Resolucion();
        resolDAO = new ResolucionDAO();
        bene = new Beneficiario();
        beneDAO = new BeneficiarioDAO();
        frR = new FrmResumen();
    }

    public void iniciar() {
        frC.setTitle("MVC Inicial");
        frP.mnuresoluciones.addActionListener(this);
        frP.mnutotales.addActionListener(this);
        frC.optdocumento.addActionListener(this);
        frC.optresolucion.addActionListener(this);
        frC.txtidresolucion.enable(false);

        frC.btnLimpiar.addActionListener(this);
        frC.btnAgregar.addActionListener(this);
        frC.btneliminar.addActionListener(this);

        frC.btnActualizar.addActionListener(this);
        frC.btnimprimir.addActionListener(this);
        frC.btnimprimir1.addActionListener(this);
        frC.btnbuscar.addActionListener(this);
        frC.btnbeneficiarios.addActionListener(this);

        frC.btneliminarb.addActionListener(this);
        frC.btnActualizarb.addActionListener(this);
        frC.btnAgregarb.addActionListener(this);
        frC.btnLimpiarb.addActionListener(this);

        frR.btnporfecha.addActionListener(this);
        frR.btnpormes.addActionListener(this);
        frP.setLocationRelativeTo(null);
        frP.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //ResolucionDAO resDAO = new ResolucionDAO();
        //resDAO = new ResolucionDAO();
        switch (e.getActionCommand()) {

            case "Resoluciones" -> {
                frP.setExtendedState(Frame.MAXIMIZED_BOTH);
                frP.getEscritorio().add(frC);
                frC.setVisible(true);
                llenarTabla(resolDAO.select());
            }
            case "Por Fecha" -> {
                limpiarTablaResumen();
                frR.iniciotablaPorFecha();
                frR.lblaviso.setText("Resumen Por Fecha");
                llenarTablaResumen(resolDAO.selectPorFecha());
                double[] arrResumen = new double[3];
                arrResumen = calcularTotales(resolDAO.selectPorFecha());
                frR.txttotalpersonas.setText(String.valueOf(arrResumen[0]));
                frR.txttotalmontos.setText(String.valueOf(FormatoNum(arrResumen[1])));
                frR.txttotalbeneficarios.setText(String.valueOf(FormatoNum(arrResumen[2])));

                frR.txttotalpersonas.enable(false);
                frR.txttotalmontos.enable(false);
                frR.txttotalbeneficarios.enable(false);
            }
            case "Por Mes" -> {
                limpiarTablaResumen();
                frR.iniciotablaPorMes();
                frR.lblaviso.setText("Resumen Por Mes");
                llenarTablaResumen(resolDAO.selectPorMes());
            }
            case "Totales" -> {
                frP.setExtendedState(Frame.MAXIMIZED_BOTH);
                frP.getEscritorio().add(frR);
                frR.setVisible(true);
                //llenarTabla(resolDAO.select());
            }
            case "Limpiar" -> {
                limpiarCampos();
            }
            case "Limpiar_Bene" -> {
                limpiarCamposbene();
            }
            case "Agregar" -> {
                if (frC.txtfresol.getText().isEmpty() || frC.txtbeneficiarias.getText().isEmpty()
                        || Double.parseDouble(frC.txtmonto.getText()) <= 0) {
                    JOptionPane.showMessageDialog(frC, "No se admiten campos en blanco");
                } else {
                    resolDAO = new ResolucionDAO();
                    resol.setNum_resolucion(frC.txtnumresolucion.getText());
                    resol.setCedula_principal(frC.txtcedulaprincipal.getText());
                    resol.setNom_principal(frC.txtnomprincipal.getText());
                    resol.setApe_principal(frC.txtapeprincipal.getText());
                    resol.setFecha_resolucion(cadena_fecha(frC.txtfresol.getText()));
                    resol.setFam_beneficiarias(Integer.parseInt(frC.txtbeneficiarias.getText()));
                    resol.setMonto_resolucion(Double.parseDouble(frC.txtmonto.getText()));
                    resol.setEmitida_por(frC.txtemitidapor.getText());
                    resol.setFecha_registro(cadena_fecha(frC.txtfregistro.getText()));
                    resolDAO.insert(resol);
                    limpiarCampos();
                }
                limpiarTabla();
                llenarTabla(resolDAO.select());
            }
            case "Eliminar" -> {
                if (!frC.txtidresolucion.getText().isEmpty()) {
                    if (JOptionPane.showConfirmDialog(frC, "Borrado", "Confirmación Borrado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        eliminareg();
                        limpiarTabla();
                        llenarTabla(resolDAO.select());
                        limpiarCampos();
                    }
                } else {
                    JOptionPane.showMessageDialog(frC, "Debe seleccionar un registro");
                }
            }
            case "Eliminar_Bene" -> {
                if (frC.txtidbeneficiario.getText().isEmpty() || frC.txtnombrebeneficiario.getText().isEmpty() || frC.txtapellidobeneficiario.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frC, "No se admiten campos en blanco");
                    JOptionPane.showMessageDialog(frC, "Debe seleccionar un registro");
                } else if (JOptionPane.showConfirmDialog(frC, "Borrado", "Confirmación Borrado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    {
                        eliminaregbene();
                        limpiarTablabene();
                        llenarTablaBene(beneDAO.selectbene(Integer.parseInt(frC.txtnumresolucionb.getText())));
                        limpiarCamposbene();
                    }
                }
            }
            case "Beneficiarios" -> {
                if (frC.txtidresolucion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
                    //eliminareg();
                    //limpiarTabla();
                    //llenarTabla(resolDAO.select());
                    //limpiarCampos();
                } else {
                    String numres = frC.txtidresolucion.getText();
                    //JOptionPane.showMessageDialog(null, frC.txtidresolucion.getText());
                    frC.pestResoluciones.setSelectedIndex(1);
                    frC.txtnumresolucionb.setText(numres);
                    frC.txtnumresolucionb.enable(false);
                    llenarTablaBene(beneDAO.selectbene(Integer.parseInt(numres)));
                    int nbenef = beneDAO.selectcantbene(Integer.parseInt((frC.txtidresolucion.getText())));
                    //JOptionPane.showMessageDialog(null, nbenef);
                }

            }

            case "Actualizar" -> {
                if (frC.txtfresol.getText().isEmpty() || frC.txtbeneficiarias.getText().isEmpty() || Double.parseDouble(frC.txtmonto.getText()) <= 0) {
                    JOptionPane.showMessageDialog(frC, "No se admiten campos en blanco");
                } else {
                    resolDAO = new ResolucionDAO();
                    resol.setId_resolucion(Integer.parseInt(frC.txtidresolucion.getText()));
                    resol.setCedula_principal(frC.txtcedulaprincipal.getText());
                    resol.setNom_principal(frC.txtnomprincipal.getText());
                    resol.setApe_principal(frC.txtapeprincipal.getText());
                    resol.setNum_resolucion(frC.txtnumresolucion.getText());
                    resol.setFecha_resolucion(cadena_fecha(frC.txtfresol.getText()));
                    resol.setFam_beneficiarias(Integer.parseInt(frC.txtbeneficiarias.getText()));
                    resol.setMonto_resolucion(Double.parseDouble(frC.txtmonto.getText()));
                    resol.setEmitida_por(frC.txtemitidapor.getText());
                    resol.setFecha_registro(cadena_fecha(frC.txtfregistro.getText()));

                    resolDAO.update(resol);
                    limpiarCampos();
                }
                limpiarTabla();
                llenarTabla(resolDAO.select());
            }
            case "Act_Bene" -> {
                if (this.frC.txtidbeneficiario.getText().isEmpty()
                        || this.frC.txtnombrebeneficiario.getText().isEmpty() || this.frC.txtapellidobeneficiario.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frC, "No se admiten campos en blanco");
                } else {
                    beneDAO = new BeneficiarioDAO();
                    bene.setId_beneficiario(this.frC.txtidbeneficiario.getText());
                    bene.setNombre_beneficiario(this.frC.txtnombrebeneficiario.getText());
                    bene.setApellido_beneficiario(this.frC.txtapellidobeneficiario.getText());
                    int filasect = this.frC.tablab.getSelectedRow();
                    String strbene = String.valueOf(this.frC.tablab.getValueAt(filasect, 1));
                    beneDAO.update(bene, strbene);
                    limpiarCamposbene();
                }
                limpiarTablabene();
                llenarTablaBene(beneDAO.selectbene(Integer.parseInt(this.frC.txtnumresolucionb.getText())));
            }
            case "Agregar_Bene" -> {
                if (frC.txtnumresolucionb.getText().isEmpty() || frC.txtidbeneficiario.getText().isEmpty()
                        || frC.txtnombrebeneficiario.getText().isEmpty() || frC.txtapellidobeneficiario.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frC, "No se admiten campos en blanco");
                } else {
                    beneDAO = new BeneficiarioDAO();
                    bene.setResolucion_id(Integer.parseInt(frC.txtnumresolucionb.getText()));
                    bene.setId_beneficiario(frC.txtidbeneficiario.getText());
                    bene.setNombre_beneficiario(frC.txtnombrebeneficiario.getText());
                    bene.setApellido_beneficiario(frC.txtapellidobeneficiario.getText());

                    beneDAO.insert(bene);
                    limpiarCamposbene();
                }
                //Pendiente (Estos dos)
                limpiarTablabene();
                llenarTablaBene(beneDAO.selectbene(bene.getResolucion_id()));
            }
            case "Buscar" -> {
                //1.Limpiamos la ventana antes de mostrar nada
                limpiarTabla();
                ArrayList<Resolucion> listaresoluciones;
                //2. Abrimos la Conexión
                //conn= Conexion.obtenercoinexion();
                //Obtenemos el nombre del producto a buscar
                String nombrebuscar = frC.txtbuscar.getText();
                //4. Buscamos el usuario segun nombre cedula
                if (frC.optdocumento.isSelected()) {
                    listaresoluciones = resolDAO.buscarPorCedulaPrincipal(nombrebuscar);
                } else {
                    listaresoluciones = resolDAO.buscarPorNumeroResolucion(nombrebuscar);
                }
                //5.Cargamos la tabla con los resultados
                //llenartabla(llenarTabla(resolDAO.select()););
                llenarTabla(listaresoluciones);
                // 6.Cerramos los conexión
                //conn.close();
            }
            case "Imprimir" -> {
                String reporte = "src\\Reportes\\Resoluciones.jasper";
                imprimir(reporte);
            }

            case "Imprimir_Bene" -> {
                String reporte = "src\\Reportes\\Beneficiarios.jasper";
                imprimir(reporte);
            }

        }
    }

    public String FormatoNum(double par) {

        NumberFormat numberformat = new DecimalFormat("#,###,###,###.##");
        return numberformat.format(par);
    }

    public void imprimir(String rptImprimir) {

        Connection conn;

        try {

            String Path;
            Path = rptImprimir;
            conn = Conexion.getConexion();
            JasperPrint informe;
            informe = JasperFillManager.fillReport(Path, null, conn);
            JasperViewer ventanavisor;
            ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setVisible(true);
        } catch (JRException e) {
            System.err.println("Error :" + e.getMessage());
            System.err.println("Error :" + e.getMessageKey());
        } catch (ClassNotFoundException ex) {
            System.err.println("Error" + ex.getMessage());
        }
    }

    public void llenarTabla(ArrayList<Resolucion> listado) {
        limpiarTabla();
        //ResolucionJDBC resJDBC = new ResolucionDAO();
        DefaultTableModel tabla;
        tabla = (DefaultTableModel) frC.tabla.getModel();
        String[] camposResol = new String[10];
        for (Resolucion res : listado) {
            camposResol[0] = String.valueOf(res.getId_resolucion());
            camposResol[1] = String.valueOf(res.getNum_resolucion());
            camposResol[2] = String.valueOf(res.getCedula_principal());
            camposResol[3] = String.valueOf(res.getNom_principal());
            camposResol[4] = String.valueOf(res.getApe_principal());
            camposResol[5] = String.valueOf(res.getFecha_resolucion());
            camposResol[6] = String.valueOf(res.getFam_beneficiarias());
            camposResol[7] = String.valueOf(res.getMonto_resolucion());
            camposResol[8] = String.valueOf(res.getEmitida_por());
            camposResol[9] = String.valueOf(res.getFecha_registro());
            tabla.addRow(camposResol);
        }
    }

    public void llenarTablaBene(ArrayList<Beneficiario> listado) {
        limpiarTablabene();
        //ResolucionJDBC resJDBC = new ResolucionDAO();
        DefaultTableModel tabla;
        tabla = (DefaultTableModel) frC.tablab.getModel();
        String[] camposBene = new String[4];
        for (Beneficiario bene : listado) {
            camposBene[0] = String.valueOf(bene.getResolucion_id());
            camposBene[1] = String.valueOf(bene.getId_beneficiario());
            camposBene[2] = String.valueOf(bene.getNombre_beneficiario());
            camposBene[3] = String.valueOf(bene.getApellido_beneficiario());
            tabla.addRow(camposBene);
        }
    }

    public void llenarTablaResumen(ArrayList<Resumen> listado) {
        limpiarTabla();
        //ResolucionJDBC resJDBC = new ResolucionDAO();
        DefaultTableModel tabla;
        tabla = (DefaultTableModel) frR.tablaresumen.getModel();
        String[] camposResumen = new String[5];
        for (Resumen res : listado) {

            camposResumen[0] = String.valueOf(res.getFecha());
            camposResumen[1] = String.valueOf(res.getCantRes());
            camposResumen[2] = String.valueOf(res.getMes());
            camposResumen[3] = FormatoNum(Double.parseDouble(String.valueOf(res.getTotalAyudas())));
            camposResumen[4] = FormatoNum(Double.parseDouble(String.valueOf(res.getTotalBeneficiarios())));
            tabla.addRow(camposResumen);
        }
    }

    public double[] calcularTotales(ArrayList<Resumen> listado) {
        //limpiarTabla();
        //ResolucionJDBC resJDBC = new ResolucionDAO();
        //DefaultTableModel tabla;
        //tabla = (DefaultTableModel) frR.tablaresumen.getModel();
        double[] camposTotales = new double[3];
        double totalResoluciones = 0;
        double totalMontos = 0;
        double totalBeneficiarios = 0;
        for (Resumen res : listado) {
            totalResoluciones += Double.valueOf(res.getCantRes());
            totalMontos += Double.valueOf(res.getTotalAyudas());
            totalBeneficiarios += Double.valueOf(res.getTotalBeneficiarios());
        }
        camposTotales[0] = totalResoluciones;
        camposTotales[1] = totalMontos;
        camposTotales[2] = totalBeneficiarios;
        return camposTotales;
    }

    public void limpiarCampos() {
        frC.txtidresolucion.setText("");
        frC.txtnumresolucion.setText("");
        frC.txtnumresolucionb.setText("");
        frC.txtcedulaprincipal.setText("");
        frC.txtnomprincipal.setText("");
        frC.txtapeprincipal.setText("");
        frC.txtfresol.setText("");
        frC.txtbeneficiarias.setText("");
        frC.txtmonto.setText("");
        frC.txtemitidapor.setText("");
        frC.txtfregistro.setText("");
        frC.txtbuscar.setText("");

    }

    public void limpiarCamposbene() {
//        frC.txtidresolucion.setText("");
        frC.txtidbeneficiario.setText("");
        frC.txtnombrebeneficiario.setText("");
        frC.txtapellidobeneficiario.setText("");
        frC.txtbuscarb.setText("");
    }

    public void limpiarTabla() {

        DefaultTableModel temp = (DefaultTableModel) frC.tabla.getModel();
        int filas = frC.tabla.getRowCount();

        for (int a = 0; filas > a; a++) {
            temp.removeRow(0);
        }
    }

    public void limpiarTablabene() {

        DefaultTableModel temp = (DefaultTableModel) frC.tablab.getModel();
        int filas = frC.tablab.getRowCount();

        for (int a = 0; filas > a; a++) {
            temp.removeRow(0);
        }
    }

    public void limpiarTablaResumen() {

        DefaultTableModel temp = (DefaultTableModel) frR.tablaresumen.getModel();
        int filas = frR.tablaresumen.getRowCount();

        for (int a = 0; filas > a; a++) {
            temp.removeRow(0);
        }
    }

    public void eliminareg() {
        // Declara variable DAO
        ResolucionDAO resDAO = new ResolucionDAO();
        Resolucion res = new Resolucion();
        int fila = this.frC.tabla.getSelectedRow();
        res.setId_resolucion(Integer.parseInt(String.valueOf(this.frC.tabla.getValueAt(fila, 0))));
        res.setNum_resolucion(String.valueOf(this.frC.tabla.getValueAt(fila, 1)));
        res.setCedula_principal(String.valueOf(this.frC.tabla.getValueAt(fila, 2)));
        res.setNom_principal(String.valueOf(this.frC.tabla.getValueAt(fila, 3)));
        res.setApe_principal(String.valueOf(this.frC.tabla.getValueAt(fila, 4)));
        res.setFecha_resolucion(cadena_fecha(String.valueOf(this.frC.tabla.getValueAt(fila, 5))));
        res.setFam_beneficiarias(Integer.parseInt(String.valueOf(this.frC.tabla.getValueAt(fila, 6))));
        res.setMonto_resolucion(Double.parseDouble(String.valueOf(this.frC.tabla.getValueAt(fila, 7))));
        res.setEmitida_por(String.valueOf(this.frC.tabla.getValueAt(fila, 8)));
        res.setFecha_resolucion(cadena_fecha(String.valueOf(this.frC.tabla.getValueAt(fila, 9))));
        resDAO.delete(res);
    }

    public void eliminaregbene() {
        // Declara variable DAO
        BeneficiarioDAO beneDAO = new BeneficiarioDAO();
        Beneficiario bene = new Beneficiario();
        int fila = this.frC.tablab.getSelectedRow();
        bene.setResolucion_id(Integer.parseInt(String.valueOf(frC.tablab.getValueAt(fila, 0))));
        bene.setId_beneficiario(String.valueOf(frC.tablab.getValueAt(fila, 1)));
        bene.setNombre_beneficiario(String.valueOf(frC.tablab.getValueAt(fila, 2)));
        bene.setApellido_beneficiario(String.valueOf(this.frC.tablab.getValueAt(fila, 3)));

        beneDAO.delete(bene);
    }

    public static java.sql.Date cadena_fecha(String cadena) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ff = LocalDate.parse(cadena, dtf);
        java.sql.Date res = java.sql.Date.valueOf(ff);
        return res;
    }

}

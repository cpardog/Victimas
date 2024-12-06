/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.util.Date;

/**
 *
 * @author Admin
 */
public class Resolucion {
    private int  id_resolucion;
    private String num_resolucion;
    private String cedula_principal;
    private String nom_principal;
    private String ape_principal;
    private java.sql.Date fecha_resolucion;
    private int fam_beneficiarias;
    private double monto_resolucion;
    private String emitida_por;
    private java.sql.Date fecha_registro;

    public Resolucion() {
    }

    public Resolucion(String num_resolucion, String cedula_principal, String nom_principal,String ape_principal,java.sql.Date fecha_resolucion, int fam_beneficiarias, double monto_resolucion, String emitida_por, java.sql.Date fecha_registro) {
        this.num_resolucion=num_resolucion;
        this.cedula_principal=cedula_principal;
        this.nom_principal= nom_principal;
         this.ape_principal= ape_principal;
        this.fecha_resolucion = fecha_resolucion;
        this.fam_beneficiarias = fam_beneficiarias;
        this.monto_resolucion = monto_resolucion;
        this.emitida_por = emitida_por;
        this.fecha_registro = fecha_registro;
    }

    public String getNom_principal() {
        return nom_principal;
    }

    public void setNom_principal(String nom_principal) {
        this.nom_principal = nom_principal;
    }

    public String getApe_principal() {
        return ape_principal;
    }

    public void setApe_principal(String ape_principal) {
        this.ape_principal = ape_principal;
    }

    public java.sql.Date getFecha_registro() {
        return fecha_registro;
    }

    public String getNum_resolucion() {
        return num_resolucion;
    }

    public void setNum_resolucion(String num_resolucion) {
        this.num_resolucion = num_resolucion;
    }

    public void setFecha_registro(java.sql.Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getId_resolucion() {
        return id_resolucion;
    }

    public void setId_resolucion(int id_resolucion) {
        this.id_resolucion = id_resolucion;
    }

    public Date getFecha_resolucion() {
        return fecha_resolucion;
    }

    public void setFecha_resolucion(java.sql.Date fecha_resolucion) {
        this.fecha_resolucion = fecha_resolucion;
    }

    public int getFam_beneficiarias() {
        return fam_beneficiarias;
    }

    public void setFam_beneficiarias(int fam_beneficiarias) {
        this.fam_beneficiarias = fam_beneficiarias;
    }

    public double getMonto_resolucion() {
        return monto_resolucion;
    }

    public void setMonto_resolucion(double monto_resolucion) {
        this.monto_resolucion = monto_resolucion;
    }

    public String getEmitida_por() {
        return emitida_por;
    }

    public void setEmitida_por(String emitida_por) {
        this.emitida_por = emitida_por;
    }

    @Override
    public String toString() {
        return "Resolucion{" + "id_resolucion=" + id_resolucion + ", num_resolucion=" + num_resolucion + ", cedula_principal=" + cedula_principal + ", nom_principal=" + nom_principal + ", ape_principal=" + ape_principal + ", fecha_resolucion=" + fecha_resolucion + ", fam_beneficiarias=" + fam_beneficiarias + ", monto_resolucion=" + monto_resolucion + ", emitida_por=" + emitida_por + ", fecha_registro=" + fecha_registro + '}';
    }

    public String getCedula_principal() {
        return cedula_principal;
    }

    public void setCedula_principal(String cedula_principal) {
        this.cedula_principal = cedula_principal;
    }

    
    
}

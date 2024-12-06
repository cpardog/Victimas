package Modelo;

import java.util.Date;

public class Solicitante {

    private int id_Solicitante;
    private String motivo_Visita;// pocos valores -enum
    private String tipo_Documento;// pocos valores -enum
    private String num_Documento;
    private String primer_Nombre;
    private String segundo_Nombre;
    private String primer_Apellido;
    private String segundo_Apellido;
    private String barrio;
    private String direccion;
    private String celular;
    private boolean cabeza_Hogar;
    private boolean tercera_Edad;
    private String genero;
    private int edad;
    private String registrado_Por; // pocos valores -enum
    private Date fecha_Registro;

    public String getMotivo_Visita() {
        return motivo_Visita;
    }

    public void setMotivo_Visita(String motivo_Visita) {
        this.motivo_Visita = motivo_Visita;
    }

    public int getId_Solicitante() {
        return id_Solicitante;
    }

    public void setId_Solicitante(int id_Solicitante) {
        this.id_Solicitante = id_Solicitante;
    }

    public String getTipo_Documento() {
        return tipo_Documento;
    }

    public void setTipo_Documento(String tipo_Documento) {
        this.tipo_Documento = tipo_Documento;
    }

    public String getNum_Documento() {
        return num_Documento;
    }

    public void setNum_Documento(String num_Documento) {
        this.num_Documento = num_Documento;
    }

    public String getPrimer_Nombre() {
        return primer_Nombre;
    }

    public void setPrimer_Nombre(String primer_Nombre) {
        this.primer_Nombre = primer_Nombre;
    }

    public String getSegundo_Nombre() {
        return segundo_Nombre;
    }

    public void setSegundo_Nombre(String segundo_Nombre) {
        this.segundo_Nombre = segundo_Nombre;
    }

    public String getPrimer_Apellido() {
        return primer_Apellido;
    }

    public void setPrimer_Apellido(String primer_Apellido) {
        this.primer_Apellido = primer_Apellido;
    }

    public String getSegundo_Apellido() {
        return segundo_Apellido;
    }

    public void setSegundo_Apellido(String segundo_Apellido) {
        this.segundo_Apellido = segundo_Apellido;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isCabeza_Hogar() {
        return cabeza_Hogar;
    }

    public void setCabeza_Hogar(boolean cabeza_Hogar) {
        this.cabeza_Hogar = cabeza_Hogar;
    }

    public boolean isTercera_Edad() {
        return tercera_Edad;
    }

    public void setTercera_Edad(boolean tercera_Edad) {
        this.tercera_Edad = tercera_Edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRegistrado_Por() {
        return registrado_Por;
    }

    public void setRegistrado_Por(String registrado_Por) {
        this.registrado_Por = registrado_Por;
    }

    public Date getFecha_Registro() {
        return fecha_Registro;
    }

    public void setFecha_Registro(Date fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }

}

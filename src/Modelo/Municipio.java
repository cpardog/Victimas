/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Carlos Pardo
 */
public class Municipio {
    private Integer codigoDepartamento;
    private String nombreMunicipio;

    public Municipio() {
    }

    public Municipio(Integer codigoDepartamento, String nombreMunicipio) {
        this.codigoDepartamento = codigoDepartamento;
        this.nombreMunicipio = nombreMunicipio;
    }

    public Integer getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Integer codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Municipio{");
        sb.append("codigoDepartamento=").append(codigoDepartamento);
        sb.append(", nombreMunicipio=").append(nombreMunicipio);
        sb.append('}');
        return sb.toString();
    }
    

    
}

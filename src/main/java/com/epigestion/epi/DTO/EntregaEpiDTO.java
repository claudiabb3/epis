package com.epigestion.epi.DTO;

import java.util.Date;

public class EntregaEpiDTO {
    private int idEntrega;
    private String nombreEmp;
    private String apellido1Emp;
    private String apellido2Emp;
    private String puestoEmp;
    private String descripcionEpi;
    private Date fechaEntrega;
    private int cantidad;
    private String talla;

    public EntregaEpiDTO(int idEntrega, Date fechaEntrega,String nombreEmp, String apellido1Emp, String apellido2Emp, String puestoEmp, String descripcionEpi, String talla,int cantidad ) {
        this.idEntrega = idEntrega;
        this.fechaEntrega = fechaEntrega;
        this.nombreEmp = nombreEmp;
        this.apellido1Emp = apellido1Emp;
        this.apellido2Emp = apellido2Emp;
        this.puestoEmp = puestoEmp;
        this.descripcionEpi = descripcionEpi;
        this.cantidad = cantidad;
        this.talla = talla;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getApellido1Emp() {
        return apellido1Emp;
    }

    public void setApellido1Emp(String apellido1Emp) {
        this.apellido1Emp = apellido1Emp;
    }

    public String getApellido2Emp() {
        return apellido2Emp;
    }

    public void setApellido2Emp(String apellido2Emp) {
        this.apellido2Emp = apellido2Emp;
    }

    public String getPuestoEmp() {
        return puestoEmp;
    }

    public void setPuestoEmp(String puestoEmp) {
        this.puestoEmp = puestoEmp;
    }

    public String getDescripcionEpi() {
        return descripcionEpi;
    }

    public void setDescripcionEpi(String descripcionEpi) {
        this.descripcionEpi = descripcionEpi;
    }
}

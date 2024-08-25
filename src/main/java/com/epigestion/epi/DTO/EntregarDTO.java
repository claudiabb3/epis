package com.epigestion.epi.DTO;

import java.util.Date;

public class EntregarDTO {
    private int idEmpleado;
    private int idEpi;
    private Date fechaEntrega;
    private int cantidad;

    public EntregarDTO(int idEmpleado, int idEpi, Date fechaEntrega, int cantidad) {
        this.idEmpleado = idEmpleado;
        this.idEpi = idEpi;
        this.fechaEntrega = fechaEntrega;
        this.cantidad = cantidad;
    }
    // Getters y Setters
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdEpi() {
        return idEpi;
    }

    public void setIdEpi(int idEpi) {
        this.idEpi = idEpi;
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
}

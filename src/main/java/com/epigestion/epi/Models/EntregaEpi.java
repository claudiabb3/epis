package com.epigestion.epi.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ENTREGA_EPI")
public class EntregaEpi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private int idEntrega;

    @Column(name = "id_empleado", nullable = false)
    private int idEmpleado;

    @Column(name = "id_epi", nullable = false)
    private int idEpi;

    @Column(name = "fecha_entrega", nullable = false)
    private Date fechaEntrega;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    // Getters and Setters

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

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
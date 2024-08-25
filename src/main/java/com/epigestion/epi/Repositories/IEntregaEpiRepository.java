package com.epigestion.epi.Repositories;

import com.epigestion.epi.DTO.EntregaEpiDTO;
import com.epigestion.epi.Models.EntregaEpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEntregaEpiRepository extends JpaRepository<EntregaEpi, Integer> {


    @Query("SELECT new com.epigestion.epi.DTO.EntregaEpiDTO(" +
            "e.idEntrega, " +
            "e.fechaEntrega, " +
            "emp.nombre, " +
            "emp.apellido1, " +
            "emp.apellido2, " +
            "emp.puesto, " +
            "epi.descripcion, " +
            "epi.talla," +
            "e.cantidad)"+
            "FROM EntregaEpi e " +
            "JOIN Empleado emp ON e.idEmpleado = emp.id_Empleado " +
            "JOIN Epi epi ON e.idEpi = epi.id_epi")
    List<EntregaEpiDTO> findAllEntregas();

    @Query("SELECT new com.epigestion.epi.DTO.EntregaEpiDTO(" +
            "e.idEntrega, " +
            "e.fechaEntrega, " +
            "emp.nombre, " +
            "emp.apellido1, " +
            "emp.apellido2, " +
            "emp.puesto, " +
            "epi.descripcion, " +
            "epi.talla, " +
            "e.cantidad) " +
            "FROM EntregaEpi e " +
            "JOIN Empleado emp ON e.idEmpleado = emp.id_Empleado " +
            "JOIN Epi epi ON e.idEpi = epi.id_epi " +
            "WHERE epi.id_epi = :idEpi")
    List<EntregaEpiDTO> findByIdEpi(@Param("idEpi") int idEpi);

    @Query("SELECT new com.epigestion.epi.DTO.EntregaEpiDTO(" +
            "e.idEntrega, " +
            "e.fechaEntrega, " +
            "emp.nombre, " +
            "emp.apellido1, " +
            "emp.apellido2, " +
            "emp.puesto, " +
            "epi.descripcion, " +
            "epi.talla, " +
            "e.cantidad) " +
            "FROM EntregaEpi e " +
            "JOIN Empleado emp ON e.idEmpleado = emp.id_Empleado " +
            "JOIN Epi epi ON e.idEpi = epi.id_epi " +
            "WHERE emp.id_Empleado = :idEmp")
    List<EntregaEpiDTO> findEntregaEpisByEmp(@Param("idEmp") Integer idEmp);

}

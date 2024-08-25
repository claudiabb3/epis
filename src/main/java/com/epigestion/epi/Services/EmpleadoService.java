package com.epigestion.epi.Services;

import com.epigestion.epi.Models.Empleado;
import com.epigestion.epi.Repositories.IEmpleadoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    IEmpleadoRepository iEmpleadoRepository;

    //Devolver todos los empleados
    public List<Empleado> empleados(){

        try{
            return (List<Empleado>) iEmpleadoRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Error al obtener empleados");
        }
    }

    //Devolver empleado por id
    public Empleado empleadoId(int id){

        return iEmpleadoRepository.findById(id).get();

    }

    //Añadir empleado
    @Transactional
    public Empleado addEmpleado(Empleado empleado){
        if(empleado ==null){
            throw new IllegalArgumentException("Debes indicar un empleado");
        }
        //validar campos
        if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Nombre es obligatorio");
        }
        if (empleado.getApellido1() == null || empleado.getApellido1().isEmpty()) {
            throw new IllegalArgumentException("Primer apellido es obligatorio");
        }
        if (empleado.getApellido2() == null || empleado.getApellido2().isEmpty()) {
            throw new IllegalArgumentException("Segundo apellido es obligatorio");
        }
        if (empleado.getPuesto() == null || empleado.getPuesto().isEmpty()) {
            throw new IllegalArgumentException("Puesto es obligatorio");
        }

        return iEmpleadoRepository.save(empleado);
    }

    //Actualizar datos de un empleado
    @Transactional
    public Empleado updateEmpleado(int id, Empleado empleado){
      Optional<Empleado> empleadoOptional = Optional.of(iEmpleadoRepository.getReferenceById(id));

      if(!empleadoOptional.isPresent()){
          throw new EntityNotFoundException("No se encontro el id del empleado "+ id);
      }
      Empleado empleadoExist = empleadoOptional.get();

      //Actualizar solo los campos no nulos
        if(empleado.getNombre() !=null){
            empleadoExist.setNombre(empleado.getNombre());
        }
        if(empleado.getApellido1() !=null){
            empleadoExist.setApellido1(empleado.getApellido1());
        }
        if(empleado.getApellido2() !=null){
            empleadoExist.setApellido2(empleado.getApellido2());
        }
        if(empleado.getPuesto() !=null){
            empleadoExist.setPuesto(empleado.getPuesto());
        }

        return iEmpleadoRepository.save(empleadoExist);
    }

    //Eliminar empleado
    @Transactional
    public Boolean deleteEmpleado(int id){
     try{
         Optional<Empleado>empleadoOptional = iEmpleadoRepository.findById(id);
         if(!empleadoOptional.isPresent()){
             return false;
         }
         iEmpleadoRepository.deleteById(id);
         return true;

     }catch (Exception e){
         throw new RuntimeException("Error al eliminar el empleado "+ id);
     }
    }


}

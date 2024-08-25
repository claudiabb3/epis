package com.epigestion.epi.Controllers;

import com.epigestion.epi.Models.Empleado;
import com.epigestion.epi.Services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;



    //EndPoint para obtener la lista de empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEpis() {
        List<Empleado> Empleado = this.empleadoService.empleados();
        if(Empleado.isEmpty()){
            return  ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(Empleado, HttpStatus.OK);
    }

    //EndPoint para obtener empleado por id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getEpisById(@PathVariable int id) {

        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Debe introducir un id correcto");
            }
            Empleado empleado = this.empleadoService.empleadoId(id);

            if (empleado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
            } else {
                return ResponseEntity.ok(empleado);
            }
        }catch (Exception e){
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al intentar recuperar el empleado");
        }
    }

    //EndPoint para añadir empleado nuevo
    @PostMapping(path = "/add/empleado")
    public ResponseEntity<Empleado> addEmpleado(@RequestBody Empleado empleado) {
        Empleado emp = this.empleadoService.addEmpleado(empleado);
        if(emp==null){
            return  ResponseEntity.badRequest().build();
        }else{
            return new ResponseEntity<>(emp, HttpStatus.CREATED);
        }
    }

    //EndPoint cambiar datos de usuario
    @PutMapping(path = "/update/empleado/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable int id, @RequestBody Empleado empleado) {
        Optional<Empleado> emp = Optional.ofNullable(this.empleadoService.empleadoId(id));

        if(emp.isPresent()){
            Empleado empleadoActualizado = this.empleadoService.updateEmpleado(id,empleado);
        }
        if(empleado==null){
            return  ResponseEntity.badRequest().build();
        }else{
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
    }

   //EndPoint para eliminar un empleado
    @DeleteMapping(path = "/delete/empleado/{id}")
    public ResponseEntity<String> deleteEmpleado(@PathVariable int id) {
        try {

            if (id <= 0) {
                return ResponseEntity.badRequest().body("Debe introducir un ID válido y positivo.");
            }


            Boolean delete = this.empleadoService.deleteEmpleado(id);

            if (Boolean.TRUE.equals(delete)) {
                return ResponseEntity.ok("Empleado eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado un empleado con el ID " + id);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al intentar eliminar el empleado.");
        }

}}

package com.epigestion.epi.Controllers;

import com.epigestion.epi.DTO.EntregaEpiDTO;
import com.epigestion.epi.DTO.EntregarDTO;
import com.epigestion.epi.Models.EntregaEpi;
import com.epigestion.epi.Services.EntregaEpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/entregasepi")
public class EntregaEpiController {
    @Autowired
    private EntregaEpiService entregaEpiService;


    //EndPoint para consultar todas las entregas
    @GetMapping(path = "/full/entregas")
    public ResponseEntity<List<EntregaEpiDTO>> obtenerEntregas() {
        try {
            List<EntregaEpiDTO> entregas = entregaEpiService.listarEntregas();
            if (entregas.isEmpty()) {
                return ResponseEntity.noContent().build();  // Devuelve 204 si no hay datos
            }
            return ResponseEntity.ok(entregas);  // Devuelve 200 OK con la lista de entregas
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // Devuelve 500  en caso de excepción
        }

    }


    //EndPoint para consultar las entregas por id epi
    @GetMapping("/entregas/idepi/{idepi}")
    public ResponseEntity<List<EntregaEpiDTO>> obtenerEntregasXid(@PathVariable int idepi) {
        try {
            List<EntregaEpiDTO> entregas = entregaEpiService.bucarEntregaIdepi(idepi);
            if(entregas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(entregas, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //EndPoint para consultar las entregas por id empleado
    @GetMapping("/enregas/idempleado/{idEmp}")
    public ResponseEntity<List<EntregaEpiDTO>> obtenerEntregasXidEmpleado(@PathVariable int idEmp) {
        try {
            List<EntregaEpiDTO> entregas = entregaEpiService.buscarEntregaEmpleado(idEmp);
            if(entregas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(entregas, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //EndPoint para realizar entregas
    @PostMapping("/entregar")
    public ResponseEntity<String> entregarEpi(@RequestBody EntregarDTO entregarDTO) {
        try {
            // Validar el DTO
            if (entregarDTO.getIdEmpleado() <= 0 || entregarDTO.getIdEpi() <= 0 || entregarDTO.getCantidad() <= 0) {
                return ResponseEntity.badRequest().body("Datos de entrada inválidos"); // 400 Bad Request
            }

            EntregaEpi nuevaEntrega = entregaEpiService.addEntregaEpi(entregarDTO);
            if (nuevaEntrega != null) {
                return new ResponseEntity<>("Entrega de EPI registrada con éxito", HttpStatus.CREATED); // 201 Created
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo registrar la entrega de EPI");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }

    //EndPoint para eliminar entrega
    @DeleteMapping(path = "/eliminar/entrega/{idEntrega}")
    public ResponseEntity<String>eliminarEntrega(@PathVariable int idEntrega){
       try{
           if(idEntrega<=0){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debes indicar un id de entrega");
           }
           Boolean detele = this.entregaEpiService.eliminarEntregaEpi(idEntrega);
           if (detele==false) {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar la entrega, revisa el id entrega");
           }
           return ResponseEntity.ok("Entrega eliminada corrrectamente");
       }catch (Exception e) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}

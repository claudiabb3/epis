package com.epigestion.epi.Controllers;

import com.epigestion.epi.Models.Epi;
import com.epigestion.epi.Services.EpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/epis")
public class EpiControllers {
    @Autowired
    EpiService epiService;


    //EndPoint para devolver todos los epis
    @GetMapping
    public ResponseEntity<List<Epi>> getAllEpis() {
        try{
            List<Epi> epis = this.epiService.epis();
            return new ResponseEntity<>(epis, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //EndPoint para devolver epi por ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> epiId(@PathVariable int id) {
        try{
            if(id<=0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debes introducir un ID EPI valido");
            }
            Epi epi = this.epiService.epiId(id);

            if(epi==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id " + id + " no existe");
            }
            return new ResponseEntity<>(epi, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al intentar obtener el epi con id "+id);
        }

    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> epiDeleteId(@PathVariable int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe indicar un ID EPI válido");
            }

            Boolean delete = this.epiService.epiIdDelete(id);
            if (delete) {
                return ResponseEntity.ok("EPI eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID "+ id +" no existe");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se ha podido eliminar el EPI con ID " + id);
        }
    }


    //añadir un epi nuevo
    @PostMapping(path = "/add")
  public ResponseEntity<?>nuevoEpi(@RequestBody Epi epi) {
        try{
          if(epi.getDescripcion()==null || epi.getDescripcion().equals("")){
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debes indicar descripcion");
          }
          if(epi.getTalla()==null || epi.getTalla().equals("")){
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debes indicar talla");
          }
          if(epi.getUnidades()<=0){
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debes indicar un unidades");
          }
          return ResponseEntity.ok(this.epiService.addEpi(epi));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se ha podido añadir el epi");
        }
    }


    //actulizar unidades de epi
    @PutMapping(path = "/actualizarunidades/{id}")
   public ResponseEntity<String> actualizarUnidades(@PathVariable int id, @RequestBody Map<String,Integer> requestBody) {

       try{
           Integer unidades = requestBody.get("unidades");
           if(unidades<=0 || unidades.toString().equals("")){
                   return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("debes indciar unidades");
               }
           Boolean delete = this.epiService.epiIdDelete(id);

         if(!delete){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID "+ id +" no existe");

         }
         return ResponseEntity.ok("Unidades actualizadas a " + unidades);


           }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se ha podido actualizar unidades");
       }

    }

}
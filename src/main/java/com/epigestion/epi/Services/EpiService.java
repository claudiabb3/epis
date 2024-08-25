package com.epigestion.epi.Services;

import com.epigestion.epi.Models.Epi;
import com.epigestion.epi.Repositories.IEpiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EpiService {
    @Autowired
    IEpiRepository iEpiRepository;

    //Devolver todos los epis
    public List<Epi> epis(){
        try{
            return (ArrayList<Epi>) iEpiRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Error al obtener empleados");
        }

    }
    //Devolver el epi buscado por ID
    public Epi epiId(int id){
        try{
            Optional<Epi> epi = iEpiRepository.findById(id);
            if(!epi.isPresent()){
           throw new RuntimeException("Error al obtener el id del epi");
            }
            iEpiRepository.findById(id);
            return epi.get();

        }catch (Exception e){
            return null;
        }
    }

    //eliminar un epi
    @Transactional
    public Boolean epiIdDelete(int id) {
        try{
            Optional<Epi> epi = iEpiRepository.findById(id);
            if(!epi.isPresent()){
                return false;
            }

            iEpiRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //añadir un epi
    @Transactional
    public Epi addEpi(Epi epi){
        if (epi.getDescripcion() == null || epi.getDescripcion().isEmpty() ||
                epi.getTalla() == null || epi.getTalla().isEmpty() ||
                epi.getUnidades() <= 0) {

            throw new IllegalArgumentException("Todos los campos deben estar completos y correctos");
        }
       try{

           return iEpiRepository.save(epi);
       }catch (Exception e){
        e.printStackTrace();
        return null;
       }
    }

    //actualizar unidades
    @Transactional
    public Boolean updateEpi(int id, int und) {
        Optional<Epi> optionalEpi = iEpiRepository.findById(id);

        if (optionalEpi.isPresent()) {
            Epi epi = optionalEpi.get();
            epi.setUnidades(und);

            try {
                iEpiRepository.save(epi);
                return true;
            } catch (Exception e) {

                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}

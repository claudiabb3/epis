package com.epigestion.epi.Services;

import com.epigestion.epi.DTO.EntregaEpiDTO;
import com.epigestion.epi.DTO.EntregarDTO;
import com.epigestion.epi.Models.EntregaEpi;
import com.epigestion.epi.Repositories.IEmpleadoRepository;
import com.epigestion.epi.Repositories.IEntregaEpiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntregaEpiService {

    @Autowired
    private IEntregaEpiRepository iEntregaEpiRepository;
    @Autowired
    private IEmpleadoRepository empleadoRepository;
    @Autowired
    private IEntregaEpiRepository epiRepository;


    //listar entregas
    public List<EntregaEpiDTO> listarEntregas(){
        try{
            return iEntregaEpiRepository.findAllEntregas();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //buscar por id epi
    public List<EntregaEpiDTO> bucarEntregaIdepi(int idEpi){
        try{
            return iEntregaEpiRepository.findByIdEpi(idEpi);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // buscar por idEmpleado
    public List<EntregaEpiDTO> buscarEntregaEmpleado(int idEmpleado){
       try{
           return iEntregaEpiRepository.findEntregaEpisByEmp(idEmpleado);

       }catch (Exception e){
           e.printStackTrace();
           return new ArrayList<>();
       }
    }

    //realizar entrega
    @Transactional
    public EntregaEpi addEntregaEpi(EntregarDTO entregarDTO) {
        try {
            // Verificar existencia del empleado
            if (!empleadoRepository.existsById(entregarDTO.getIdEmpleado())) {
                throw new IllegalArgumentException("El ID del empleado no existe");
            }


            EntregaEpi entregaEpi = new EntregaEpi();
            entregaEpi.setIdEmpleado(entregarDTO.getIdEmpleado());
            entregaEpi.setIdEpi(entregarDTO.getIdEpi());
            entregaEpi.setFechaEntrega(entregarDTO.getFechaEntrega());
            entregaEpi.setCantidad(entregarDTO.getCantidad());

            return iEntregaEpiRepository.save(entregaEpi);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error al añadir la entrega de EPI", e);
        }
    }

    //Eliminar una entrega
    @Transactional
    public Boolean eliminarEntregaEpi(int idEntrega) {
        Optional idOpcional = iEntregaEpiRepository.findById(idEntrega);

        if(!idOpcional.isPresent()){
            return false;
        }
        iEntregaEpiRepository.deleteById(idEntrega);
        return true;
    }

}


package com.broderie2.broderie2.services;

import com.broderie2.broderie2.Exception.ExceptionBroderie;
import com.broderie2.broderie2.entidades.Photos;
import com.broderie2.broderie2.entidades.Planning;
import com.broderie2.broderie2.repository.EventosSocialesRepo;
import com.broderie2.broderie2.repository.PlanningRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PlanningServicio {
    
     @Autowired
    private PhotoServicio phServ;
    @Autowired
    private PlanningRepo plaRep;

    public void crearPlanning(String nombre, MultipartFile archivo) throws ExceptionBroderie {

        if (nombre == null || nombre.isEmpty()) {
            throw new ExceptionBroderie("Tenes que ingresar el nombre salamina!");
        }
        if (archivo == null) {
            throw new ExceptionBroderie("Tenes que ingresar la foto salamina!");
        }
        Planning plan = new Planning();
        plan.setNombre(nombre);
        Photos ph = phServ.crearFoto(archivo);
        plan.setFoto(ph);
        plan.setAlta(true);
        plaRep.save(plan);
    }
    
    public void modificar(String nombre, MultipartFile archivo, String id) throws ExceptionBroderie{
        
       if(nombre.isEmpty()|| nombre == null){
                throw new ExceptionBroderie("Tenes que ingresar el nombre salamina!");
            }
        if (archivo == null) {
            throw new ExceptionBroderie("Tenes que ingresar la foto salamina!");
        }
        Optional<Planning> respuesta = plaRep.findById(id);
        
        if(respuesta.isPresent()){
            Planning plan = respuesta.get();
            Photos ph = phServ.crearFoto(archivo);
            plan.setNombre(nombre);
            plan.setFoto(ph);
            plaRep.save(plan);
        }else {
            throw new ExceptionBroderie("No existe el evento ingresado");
        }
    }
    
    public void darBaja( String id) throws ExceptionBroderie{
       
        Optional<Planning> respuesta = plaRep.findById(id);
        
        if(respuesta.isPresent()){
            Planning plan = respuesta.get();
            plan.setAlta(Boolean.FALSE);
            plaRep.save(plan);
        }else{
             throw new ExceptionBroderie("No existe el evento ingresado");
        }
    }
    
}

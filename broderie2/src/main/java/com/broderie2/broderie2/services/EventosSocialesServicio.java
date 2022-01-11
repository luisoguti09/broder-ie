package com.broderie2.broderie2.services;

import com.broderie2.broderie2.Exception.ExceptionBroderie;
import com.broderie2.broderie2.entidades.EventosSociales;
import com.broderie2.broderie2.entidades.Photos;
import com.broderie2.broderie2.repository.EventosSocialesRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EventosSocialesServicio {

    @Autowired
    private PhotoServicio phServ;
    @Autowired
    private EventosSocialesRepo evSRepo;

    public void crearEventoSocial(String nombre, MultipartFile archivo) throws ExceptionBroderie {

        if (nombre == null || nombre.isEmpty()) {
            throw new ExceptionBroderie("Tenes que ingresar el nombre salamina!");
        }
        if (archivo == null) {
            throw new ExceptionBroderie("Tenes que ingresar la foto salamina!");
        }
        EventosSociales evSoc = new EventosSociales();
        evSoc.setNombre(nombre);
        Photos ph = phServ.crearFoto(archivo);
        evSoc.setFoto(ph);
        evSoc.setAlta(true);
        evSRepo.save(evSoc);
    }
    
    public void modificar(String nombre, MultipartFile archivo, String id) throws ExceptionBroderie{
        
       if(nombre.isEmpty()|| nombre == null){
                throw new ExceptionBroderie("Tenes que ingresar el nombre salamina!");
            }
        if (archivo == null) {
            throw new ExceptionBroderie("Tenes que ingresar la foto salamina!");
        }
        Optional<EventosSociales> respuesta = evSRepo.findById(id);
        
        if(respuesta.isPresent()){
            EventosSociales evenS = respuesta.get();
            Photos ph = phServ.crearFoto(archivo);
            evenS.setNombre(nombre);
            evenS.setFoto(ph);
            evSRepo.save(evenS);
        }else {
            throw new ExceptionBroderie("No existe el evento ingresado");
        }
    }
    
    public void darBaja( String id) throws ExceptionBroderie{
       
        Optional<EventosSociales> respuesta = evSRepo.findById(id);
        
        if(respuesta.isPresent()){
            EventosSociales evenS = respuesta.get();
            evenS.setAlta(Boolean.FALSE);
            evSRepo.save(evenS);
        }else{
             throw new ExceptionBroderie("No existe el evento ingresado");
        }
    }

}

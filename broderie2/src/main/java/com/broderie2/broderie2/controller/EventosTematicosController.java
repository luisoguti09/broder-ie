
package com.broderie2.broderie2.controller;

import com.broderie2.broderie2.Exception.ExceptionBroderie;
import com.broderie2.broderie2.entidades.EventosTematicos;
import com.broderie2.broderie2.entidades.Photos;
import com.broderie2.broderie2.repository.EventosTematicosRepo;
import com.broderie2.broderie2.services.PhotoServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EventosTematicosController {
    
    @Autowired
    private PhotoServicio phServ;
    @Autowired
    private EventosTematicosRepo eveTRepo;
    
    public void crearEventoSocial(String nombre, MultipartFile archivo, String tematica) throws ExceptionBroderie {

        if (nombre == null || nombre.isEmpty()) {
            throw new ExceptionBroderie("Tenes que ingresar el nombre, salamina!");
        }
        if (archivo == null) {
            throw new ExceptionBroderie("Tenes que ingresar la foto, salamina!");
        }
        if (tematica == null || tematica.isEmpty()) {
            throw new ExceptionBroderie("Tenes que ingresar el nombre de la Tematica, salamina!");
        }
        EventosTematicos evTem = new EventosTematicos();
        evTem.setNombre(nombre);
        Photos ph = phServ.crearFoto(archivo);
        evTem.setFoto(ph);
        evTem.setTematica(tematica);
        evTem.setAlta(true);
        eveTRepo.save(evTem);
    }
    
    public void modificar(String nombre, MultipartFile archivo, String id, String tematica) throws ExceptionBroderie{
        
       if(nombre.isEmpty()|| nombre == null){
                throw new ExceptionBroderie("Tenes que ingresar el nombre salamina!");
            }
        if (archivo == null) {
            throw new ExceptionBroderie("Tenes que ingresar la foto salamina!");
        }
        if (tematica == null || tematica.isEmpty()) {
            throw new ExceptionBroderie("Tenes que ingresar el nombre de la Tematica, salamina!");
        }
        Optional<EventosTematicos> respuesta = eveTRepo.findById(id);
        
        if(respuesta.isPresent()){
            EventosTematicos evTem = respuesta.get();
            Photos ph = phServ.crearFoto(archivo);
            evTem.setNombre(nombre);
            evTem.setFoto(ph);
            evTem.setTematica(tematica);
            eveTRepo.save(evTem);
        }else {
            throw new ExceptionBroderie("No existe el evento ingresado");
        }
    }
    
    public void darBaja( String id) throws ExceptionBroderie{
       
        Optional<EventosTematicos> respuesta = eveTRepo.findById(id);
        
        if(respuesta.isPresent()){
           EventosTematicos evTem = respuesta.get();
            evTem.setAlta(Boolean.FALSE);
            eveTRepo.save(evTem);
        }else{
             throw new ExceptionBroderie("No existe el evento ingresado");
        }
    }
    
}

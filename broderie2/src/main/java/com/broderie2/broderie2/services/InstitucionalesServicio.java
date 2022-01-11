/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.broderie2.broderie2.services;

import com.broderie2.broderie2.Exception.ExceptionBroderie;
import com.broderie2.broderie2.entidades.Institucionales;
import com.broderie2.broderie2.entidades.Photos;
import com.broderie2.broderie2.repository.EventosSocialesRepo;
import com.broderie2.broderie2.repository.InstitucionalesRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InstitucionalesServicio {
    
     @Autowired
    private PhotoServicio phServ;
    @Autowired
    private InstitucionalesRepo insRep;

    public void crearIntitucional(String nombre, MultipartFile archivo) throws ExceptionBroderie {

        if (nombre == null || nombre.isEmpty()) {
            throw new ExceptionBroderie("Tenes que ingresar el nombre salamina!");
        }
        if (archivo == null) {
            throw new ExceptionBroderie("Tenes que ingresar la foto salamina!");
        }
        Institucionales insti = new Institucionales();
        insti.setNombre(nombre);
        Photos ph = phServ.crearFoto(archivo);
        insti.setFoto(ph);
        insti.setAlta(true);
        insRep.save(insti);
    }
    
    public void modificar(String nombre, MultipartFile archivo, String id) throws ExceptionBroderie{
        
       if(nombre.isEmpty()|| nombre == null){
                throw new ExceptionBroderie("Tenes que ingresar el nombre salamina!");
            }
        if (archivo == null) {
            throw new ExceptionBroderie("Tenes que ingresar la foto salamina!");
        }
        Optional<Institucionales> respuesta = insRep.findById(id);
        
        if(respuesta.isPresent()){
            Institucionales insti = respuesta.get();
            Photos ph = phServ.crearFoto(archivo);
            insti.setNombre(nombre);
            insti.setFoto(ph);
            insRep.save(insti);
        }else {
            throw new ExceptionBroderie("No existe el evento ingresado");
        }
    }
    
    public void darBaja( String id) throws ExceptionBroderie{
       
        Optional<Institucionales> respuesta = insRep.findById(id);
        
        if(respuesta.isPresent()){
            Institucionales insti = respuesta.get();
            insti.setAlta(Boolean.FALSE);
            insRep.save(insti);
        }else{
             throw new ExceptionBroderie("No existe el evento ingresado");
        }
    }
    
}

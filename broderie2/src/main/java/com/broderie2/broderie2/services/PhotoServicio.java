/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.broderie2.broderie2.services;

import com.broderie2.broderie2.Exception.ExceptionBroderie;
import com.broderie2.broderie2.entidades.Photos;
import com.broderie2.broderie2.repository.PhotosRepo;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoServicio {

    @Autowired
    private PhotosRepo phRepo;

    public Photos crearFoto(MultipartFile archivo) throws ExceptionBroderie {

        if (archivo != null) {
            try {
                Photos photos = new Photos();
                photos.setMimo(archivo.getContentType());
                photos.setNombre(archivo.getName());
                photos.setContenido(archivo.getBytes());
                return phRepo.save(photos);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public Photos actualizarPhoto(MultipartFile archivo, String id) throws ExceptionBroderie {
        if (archivo != null) {
            try {
                Photos photos = new Photos();
                if (id != null) {
                    Optional<Photos> respuesta = phRepo.findById(id);
                    if (respuesta.isPresent()) {
                        photos = respuesta.get();
                    }
                }
                photos.setMimo(archivo.getContentType());
                photos.setNombre(archivo.getName());
                photos.setContenido(archivo.getBytes());
                return phRepo.save(photos);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;

    }

}

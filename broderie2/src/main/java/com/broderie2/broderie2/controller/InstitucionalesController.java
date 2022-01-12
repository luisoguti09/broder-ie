package com.broderie2.broderie2.controller;

import com.broderie2.broderie2.Exception.ExceptionBroderie;
import com.broderie2.broderie2.entidades.Institucionales;
import com.broderie2.broderie2.repository.InstitucionalesRepo;
import com.broderie2.broderie2.services.InstitucionalesServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/institucional")
public class InstitucionalesController {

    @Autowired
    private InstitucionalesServicio is;

    @Autowired
    private InstitucionalesRepo ir;

    /*
     @GetMapping("/institucional")
    public String institucional() {
        return "institucionales.html";
    }*/

    @GetMapping("/guardar")
    public String guardar() {
        return "guardarInstitucionales.html";
    }

    @PostMapping("/guardar")
    public String guard(ModelMap modelo, @RequestParam String nombre, MultipartFile archivo) {
        try {
            is.crearIntitucional(nombre, archivo);
        } catch (ExceptionBroderie e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
        
            modelo.put("archivo", archivo);
            return "guardarInstitucionales.html";
        }
        modelo.put("mensaje", "El autor se registró de manera satisfactoria.");
        return "exito.html";
    }

    @GetMapping("/mostrar")
    public String mostrarInst(ModelMap mod) {
        List<Institucionales> institucionales = ir.findAll();
        mod.put("institucionales", institucionales);
        return "institucionales.html";
    }
}

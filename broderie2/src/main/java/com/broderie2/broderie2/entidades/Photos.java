
package com.broderie2.broderie2.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Photos {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name= "uuid", strategy="uuid2")
    private String id;
    private String nombre;
    private String mimo;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]contenido;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMimo() {
        return mimo;
    }

    public void setMimo(String mimo) {
        this.mimo = mimo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
    
    
    
    
}

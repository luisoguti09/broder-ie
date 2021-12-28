
package com.broderie.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Photos {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name= "uuid", strategy="uuid2")
    private String id;
    private String nombre;
    private String mimo;
    private byte[]contenido;
    
}

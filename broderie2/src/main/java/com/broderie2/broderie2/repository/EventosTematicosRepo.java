/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.broderie2.broderie2.repository;


import com.broderie2.broderie2.entidades.EventosTematicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosTematicosRepo extends JpaRepository<EventosTematicos, String> {
    
}


package com.broderie.repository;

import com.broderie.demo.entidades.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningRepo extends JpaRepository<Planning, String> {
    
}

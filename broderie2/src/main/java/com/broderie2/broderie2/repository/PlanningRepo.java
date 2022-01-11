
package com.broderie2.broderie2.repository;


import com.broderie2.broderie2.entidades.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningRepo extends JpaRepository<Planning, String> {
    
}

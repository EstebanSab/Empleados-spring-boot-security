package com.empleados.empleadosApi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.empleados.empleadosApi.model.Legajo;

public interface LegajoRepository extends JpaRepository<Legajo, Long>{

    //nativeQuery = true habilita que la query a utilizar sea
    //la creada en el value
    @Transactional
    @Modifying
    @Query(value ="DELETE FROM Legajo l WHERE l.empleado_id = ?1" ,nativeQuery = true)
    int deleteLegajoByEmpleadoid(Long id);
    
}

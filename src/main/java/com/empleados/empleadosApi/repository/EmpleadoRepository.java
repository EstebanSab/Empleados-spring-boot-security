package com.empleados.empleadosApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.empleados.empleadosApi.model.Empleado;


//jpql 
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    
    
    @Query("SELECT e FROM Empleado e WHERE e.nombre = ?1")
    List<Empleado> selectEmpleadosWhereNombre(
            String firstName);


    @Transactional
    @Modifying
    @Query("DELETE FROM Empleado e WHERE e.id = ?1")
    int deleteEmpleadoById(Long id);

    //@Query is for defining custom query and @Modifying is for telling 
    //spring-data-jpa that this query is an update operation 
    //and it requires executeUpdate() not executeQuery().

    @Transactional
    @Modifying
    @Query("update Empleado e set e.nombre = ?1 where e.id = ?2")
    void modificaNombreEmpleadoById(String nombre,Long id);
}

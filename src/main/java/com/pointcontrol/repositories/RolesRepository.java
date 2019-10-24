package com.pointcontrol.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pointcontrol.entities.Roles;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    @Override
    List<Roles> findAll();
    
    @Query(value = "SELECT codRole FROM roles WHERE role = :role", nativeQuery = true)
    Integer getCodRole(
        @Param("role") String role
    );
}
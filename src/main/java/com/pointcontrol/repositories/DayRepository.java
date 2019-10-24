package com.pointcontrol.repositories;

import com.pointcontrol.entities.Day;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {
    
    @Query(value = "SELECT * FROM day d WHERE d.codUser = :codUser AND d.codDay = :codDay", nativeQuery = true)
    Day findByCodDay(@Param("codDay") Integer codDay, @Param("codUser") Integer codUser);
    
    @Query(value = "SELECT * FROM day d WHERE d.codUser = :codUser", nativeQuery = true)
    List<Day> findAll(@Param("codUser") Integer codUser);
    
    @Query(value ="SELECT * FROM day d WHERE d.codUser = :codUser AND d.date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Day> findByDate(
        @Param("codUser") Integer codUser,
        @Param("startDate") String startDate,
        @Param("endDate") String endDate
    );
}
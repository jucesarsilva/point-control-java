package com.pointcontrol.services;

import com.pointcontrol.entities.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pointcontrol.repositories.DayRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class DayService {
    
    @Autowired
    private DayRepository dayRepository;
    
    public Day findByCodDay(Integer codDay, Integer codUser) {
        return dayRepository.findByCodDay(codDay, codUser);
    }
    
    public List<Day> findAll(Integer codUser) {
        return dayRepository.findAll(codUser);
    }
    
    public List<Day> findByDate(Integer codUser, String startDate, String endDate) {
        return dayRepository.findByDate(codUser, startDate, endDate);
    }
    
    public Day save(Day day) {
        return dayRepository.save(day);
    }
}

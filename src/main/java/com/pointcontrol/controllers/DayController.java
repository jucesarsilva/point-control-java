package com.pointcontrol.controllers;

import com.pointcontrol.entities.Day;
import com.pointcontrol.entities.Point;
import com.pointcontrol.security.AuthFacade;
import com.pointcontrol.services.DayService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DayController {
    
    @Autowired
    private DayService dayService;
    
    @Autowired
    private AuthFacade authFacade;
    
    @CrossOrigin(origins = "*")
    @GetMapping("/api/day/{codDay}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Day findByCodDay(@PathVariable("codDay") Integer codDay) {
        return dayService.findByCodDay(codDay, authFacade.getCodUser());
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/api/days")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Day> findAll() {
        return dayService.findAll(authFacade.getCodUser());
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/api/days/{startDate}/{endDate}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Day> findByDate(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        return dayService.findByDate(authFacade.getCodUser(), startDate, endDate);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/api/day/save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Day save(@RequestBody Day day) {
        List<Point> points = day.getPoints();
        day.setPoints(Collections.<Point>emptyList());
        Day d = dayService.save(day);
        for(Point p : points) {
            p.setCodDay(day.getCodDay());
        }
        d.setPoints(points);
        d = dayService.save(day);
        return d;
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/api/day/update")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Day update(@RequestBody Day day) {
        return dayService.save(day);
    }
}
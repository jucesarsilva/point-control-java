package com.pointcontrol.controllers;

import com.pointcontrol.entities.Point;
import com.pointcontrol.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {
    
    @Autowired
    private PointService pointService;
    
    @CrossOrigin(origins = "*")
    @PostMapping("/api/point/delete")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public void delete(@RequestBody Point point) {
        pointService.delete(point);
    }
}
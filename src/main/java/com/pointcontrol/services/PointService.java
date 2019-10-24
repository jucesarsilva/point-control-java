package com.pointcontrol.services;

import com.pointcontrol.entities.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pointcontrol.repositories.PointRepository;

@Service
public class PointService {
    
    @Autowired
    private PointRepository pointRepository;
    
    public void delete(Point point) {
        pointRepository.delete(point);
    }
}

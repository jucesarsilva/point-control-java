package com.pointcontrol.entities;

import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity()
@Table(name = "point")
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="codPoint")
    private Integer codPoint;
    
    @Column(name="codDay")
    @NotNull
    private Integer codDay;

    @Column(name="time")
    @NotNull
    private LocalTime time;

    public Point() {
    }

    public Integer getCodPoint() {
        return codPoint;
    }

    public void setCodPoint(Integer codPoint) {
        this.codPoint = codPoint;
    }

    public Integer getCodDay() {
        return codDay;
    }

    public void setCodDay(Integer codDay) {
        this.codDay = codDay;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Point{" + "codPoint=" + codPoint + ", codDay=" + codDay + ", time=" + time + '}';
    }
}
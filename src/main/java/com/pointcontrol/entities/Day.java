package com.pointcontrol.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity()
@Table(name = "day")
public class Day implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="codDay")
    private Integer codDay;
    
    @Column(name="codUser")
    @NotNull
    private Integer codUser;
    
    @Column(name="date")
    @NotNull
    private LocalDate date;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="codDay", nullable=true, insertable=true, updatable=true)
    private List<Point> points;

    public Day() {
    }

    public Integer getCodDay() {
        return codDay;
    }

    public void setCodDay(Integer codDay) {
        this.codDay = codDay;
    }

    public Integer getCodUser() {
        return codUser;
    }

    public void setCodUser(Integer codUser) {
        this.codUser = codUser;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Day{" + "codDay=" + codDay + ", codUser=" + codUser + ", date=" + date + ", points=" + points + '}';
    }
}
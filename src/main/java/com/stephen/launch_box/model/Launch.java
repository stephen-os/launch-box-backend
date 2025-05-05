package com.stephen.launch_box.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "launch")
public class Launch {
    @Id
    private Long id;

    @Column(name = "company")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "detail")
    private String detail;

    @Column(name = "rocket_status")
    private Boolean rocketStatus;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "mission_status")
    private Boolean missionStatus;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getRocketStatus() {
        return rocketStatus;
    }

    public void setRocketStatus(Boolean rocketStatus) {
        this.rocketStatus = rocketStatus;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Boolean getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(Boolean missionStatus) {
        this.missionStatus = missionStatus;
    }

    // Add a toString method for debugging
    @Override
    public String toString() {
        return "Launch{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", detail='" + detail + '\'' +
                ", rocketStatus=" + rocketStatus +
                ", cost=" + cost +
                ", missionStatus=" + missionStatus +
                '}';
    }
}
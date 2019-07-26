package com.oocl.packagebooking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Package {

    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    /**
     * 0：未取件 1：已预约 2：已取件
     */
    private Integer status;

    private String phone;

    private Date reserveTime;

    private Double weight;

    public Package() {
    }

    public Package(Long id, String name, Integer status, String phone, Date reserveTime, Double weight) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.phone = phone;
        this.reserveTime = reserveTime;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}

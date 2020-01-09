package com.example.springdemo.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "doctor_id", unique = true, nullable = false)
    private Integer doctorId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "specialization")
    private String specialization;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private Users user;

    public Doctor() {
    }

    public Doctor(Integer doctorId, String name, String specialization, Users user) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.user = user;
    }

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public Doctor(String name, String specialization, Users user) {
        this.name = name;
        this.specialization = specialization;
        this.user = user;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", user=" + user +
                '}';
    }
}

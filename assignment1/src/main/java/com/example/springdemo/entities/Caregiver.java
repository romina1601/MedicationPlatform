package com.example.springdemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "caregiver")
public class Caregiver {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "caregiver_id", unique = true, nullable = false)
    private Integer caregiverId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "birth_date", nullable = false, length = 45)
    private String birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private Users user;

    @JsonIgnore
    @OneToMany(mappedBy = "caregiver", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Patient> patients;

    public Caregiver() {
    }

    public Caregiver(Integer caregiverId, String name, String birthDate, String gender,
                     String address, Users user, List<Patient> patients) {
        this.caregiverId = caregiverId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.user = user;
        this.patients = patients;
    }

    public Caregiver(Integer caregiverId, String name, String birthDate, String gender,
                     String address, Users user) {
        this.caregiverId = caregiverId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.user = user;
    }

    public Caregiver(String name, String birthDate, String gender,
                     String address, Users user, List<Patient> patients) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.user = user;
        this.patients = patients;
    }

    public Integer getId() {
        return caregiverId;
    }

    public void setId(Integer id) {
        this.caregiverId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Caregiver{" +
                "id=" + caregiverId +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", patients=" + patients +
                '}';
    }
}

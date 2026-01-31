package com.hospital.hms_app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Patient {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String medicalCondition;
    
    // New fields
    private String phoneNumber;
    private String address;
    private String bloodType;
    private String medicalHistory;

    public Patient() {}

    public Patient(String name, String email, String medicalCondition, 
                   String phoneNumber, String address, String bloodType, String medicalHistory) {
        this.name = name;
        this.email = email;
        this.medicalCondition = medicalCondition;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.bloodType = bloodType;
        this.medicalHistory = medicalHistory;
    }

    // Getters Setters 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMedicalCondition() { return medicalCondition; }
    public void setMedicalCondition(String medicalCondition) { this.medicalCondition = medicalCondition; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }
}
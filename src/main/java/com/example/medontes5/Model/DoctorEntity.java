package com.example.medontes5.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DoctorEntity {
    private String doctorName;
    private String doctorSurname;

    // @Column(name="doctorPersonalNumber")
    @Column(nullable=true)
    private int doctorPersonalNumber;

    public DoctorEntity() {
    }

    public DoctorEntity(String doctorName, String doctorSurname , int doctorPersonalNumber) {
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.doctorPersonalNumber = doctorPersonalNumber;

    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public int getDoctorPersonalNumber() {
        return doctorPersonalNumber;
    }

    public void setDoctorPersonalNumber(int doctorPersonalNumber) {
        this.doctorPersonalNumber = doctorPersonalNumber;
    }

}

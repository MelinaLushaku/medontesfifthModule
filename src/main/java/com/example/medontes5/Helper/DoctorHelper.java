package com.example.medontes5.Helper;


import java.io.Serializable;

public class DoctorHelper implements Serializable {
    private String doctorName;
    private String doctorSurname;

    public DoctorHelper(){}

    public DoctorHelper(String doctorName, String doctorSurname) {
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
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
}

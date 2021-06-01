package com.example.medontes5.Model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int appointmentId;

    @Column
    private Date dateAndTime;

   public  Appointment(){}
    public Appointment(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}

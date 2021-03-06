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

    @Column
    private boolean freeAppoint;

    @Column(nullable=true)
    private float time;
    @Column
    private boolean canceledByPat;
    @Column
    private boolean canceledByDoc;

    @Embedded
    private PatientEntity patientEntity;

    @Embedded
    private DoctorEntity doctorEntity;



   public  Appointment(){}
    public Appointment(AppointmentBuilder appointmentBuilder) {
        this.dateAndTime = appointmentBuilder.dateAndTime;
        this.freeAppoint = appointmentBuilder.freeAppoint;
        this.doctorEntity = appointmentBuilder.doctorEntity;
        this.patientEntity = appointmentBuilder.patientEntity;
        this.time = appointmentBuilder.time;
        this.canceledByDoc = appointmentBuilder.canceledByDoc;
        this.canceledByPat = appointmentBuilder.canceledByPat;
    }

    public boolean isCanceledByPat() {
        return canceledByPat;
    }

    public void setCanceledByPat(boolean canceledByPat) {
        this.canceledByPat = canceledByPat;
    }

    public boolean isCanceledByDoc() {
        return canceledByDoc;
    }

    public void setCanceledByDoc(boolean canceledByDoc) {
        this.canceledByDoc = canceledByDoc;
    }

    public DoctorEntity getDoctorEntity() {
        return doctorEntity;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setDoctorEntity(DoctorEntity doctorEntity) {
        this.doctorEntity = doctorEntity;
    }

    public PatientEntity getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(PatientEntity patientEntity) {
        this.patientEntity = patientEntity;
    }

    public boolean isFreeAppoint() {
        return freeAppoint;
    }

    public void setFreeAppoint(boolean freeAppoint) {
        this.freeAppoint = freeAppoint;
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


      public  static  class  AppointmentBuilder{
          private Date dateAndTime;
          private boolean freeAppoint;
          private PatientEntity patientEntity;
          private DoctorEntity doctorEntity;
          private float time;
          private boolean canceledByPat;
          private boolean canceledByDoc;


          public AppointmentBuilder(Date dateAndTime , boolean freeAppoint , DoctorEntity doctorEntity , float time){
              this.dateAndTime = dateAndTime;
              this.freeAppoint = freeAppoint;
              this.doctorEntity = doctorEntity;
              this.time = time;
              this.canceledByDoc =false;
              this.canceledByPat = false;
          }

         public AppointmentBuilder setDateAndTime(Date dateAndTime){
              this.dateAndTime = dateAndTime;
              return this;
         }
          public AppointmentBuilder setFreeAppoint(Date dateAndTime){
              this.freeAppoint = freeAppoint;
              return this;
          }
          public AppointmentBuilder setDoctorEntity(Date dateAndTime){
              this.doctorEntity = doctorEntity;
              return this;
          }
          public AppointmentBuilder setPatientEntity(Date dateAndTime){
              this.patientEntity = patientEntity;
              return this;
          }
          public Appointment build(){
              return new Appointment(this);
          }

      }




}

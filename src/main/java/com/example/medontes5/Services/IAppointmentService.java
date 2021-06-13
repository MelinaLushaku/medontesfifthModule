package com.example.medontes5.Services;

import com.example.medontes5.Model.Appointment;
import com.example.medontes5.Model.DoctorEntity;
import com.example.medontes5.Model.PatientEntity;

import java.util.Date;

public interface IAppointmentService {

    void addNewFreeAppointments(Appointment a);
    DoctorEntity getDoctorByPrNumber(int nrPersonal);
    PatientEntity getPatientByPrNumber(int nrPersonal);
    void editAppointment(int doc , Date data , PatientEntity p);

}
package com.example.medontes5.Services;

import com.example.medontes5.Model.Appointment;
import com.example.medontes5.Model.DoctorEntity;
import com.example.medontes5.Model.PatientEntity;

import java.util.Date;
import java.util.List;

public interface IAppointmentService {

    void addNewFreeAppointments(Appointment a);
    DoctorEntity getDoctorByPrNumber(int nrPersonal);
    PatientEntity getPatientByPrNumber(int nrPersonal);
    void editAppointment(int doc , Date data , PatientEntity p);
    List<Appointment> getAppByDoc(int doc);
    List<Appointment> getAppByPat(int pat);
    void cancelAppointment(int doc , Date data,int pat);
    void deleteAppointment(int doc , Date date);
    List<Appointment> todayAppDoc(int doc);
    List<Appointment> todayAppPat(int pat);

}

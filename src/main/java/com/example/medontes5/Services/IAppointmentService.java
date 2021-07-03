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

    void editAppointment(int doc, Date data, PatientEntity p, float time);

    List<Appointment> getAppByDoc(int doc);

    List<Appointment> getAppByPat(int pat);

    void cancelAppointment(int doc, Date data, float time);

    void deleteAppointment(int doc, Date date, float time, int pat);

    List<Appointment> todayAppDoc(int doc);

    List<Appointment> todayAppPat(int pat);

    List<Appointment> byTime(int idD, float time);

    List<Appointment> listaTP(int doc, Date date, float time, int pat);

    List<Appointment> listTM(int doc, Date data, float time);

    int getAllApp();

    int nextDays();

    List<PatientEntity> lista(int docId, int patId);

    List<DoctorEntity> listaD(int docId, int patId);

    List<Appointment> getAppByUsers(int docId, int patId);



}



package com.example.medontes5.Services;

import com.example.medontes5.DAL.AppointmentRepository;
import com.example.medontes5.Model.Appointment;
import com.example.medontes5.Model.DoctorEntity;
import com.example.medontes5.Model.PatientEntity;
import com.example.medontes5.OutputAdapters.SystemManagementModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class AppointmentService implements IAppointmentService{
     @Autowired
    private AppointmentRepository appointmentRepository;
     @Autowired
     private SystemManagementModuleService systemManagementModuleService;


     @Override
    public void addNewFreeAppointments(Appointment a){
         TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
         this.appointmentRepository.save(a);
     }


     @Override
    public DoctorEntity getDoctorByPrNumber(int doctorPersonalNumber){
     return this.systemManagementModuleService.doctoriE(doctorPersonalNumber);
     }
    @Override
    public PatientEntity getPatientByPrNumber(int nrPersonal){
     return this.systemManagementModuleService.pacientiE(nrPersonal);
    }

    @Override
    public void editAppointment(int doc , Date data, PatientEntity p , float time){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
         Appointment a = this.appointmentRepository.findAppointmentByTime(doc , data);
         a.setFreeAppoint(false);
         a.setPatientEntity(p);
         a.setTime(time);
         this.appointmentRepository.save(a);


    }
    @Override
    public List<Appointment> getAppByDoc(int doc){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
         List <Appointment> krejLista = this.appointmentRepository.findAppointmentByDoc(doc);
         List<Appointment> returnLista = new ArrayList<>();
         for(int i = 0 ; i<krejLista.size() ; i++){
           //  LocalDateTime now = LocalDateTime.now();
             // sd = new SimpleDateFormat("yyyy-MM-dd");
             Date in = new Date();
             LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
             Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

             if(krejLista.get(i).getDateAndTime().compareTo(out) > 0){
                 returnLista.add(krejLista.get(i));
             }
         }
           return returnLista;
    }

    @Override
    public List<Appointment> getAppByPat(int pat){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        List <Appointment> krejLista = this.appointmentRepository.findAppointmentByPat(pat);
        List<Appointment> returnLista = new ArrayList<>();
        for(int i = 0 ; i<krejLista.size() ; i++){
            //LocalDateTime now = LocalDateTime.now();
            //sd = new SimpleDateFormat("yyyy-MM-dd");
            Date in = new Date();
            LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
            Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            if(krejLista.get(i).getDateAndTime().compareTo(out) > 0){
                returnLista.add(krejLista.get(i));
            }
        }
        return returnLista;
    }

    @Override
    public void cancelAppointment(int doc , Date data  , float time){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
         List<Appointment> a = this.appointmentRepository.cancelAppointmentByTime(doc,data,time);
         if(a.size() != 0) {
             //a.get(0).setFreeAppoint(false);
             Appointment b = a.get(0);
             b.setCanceledByDoc(true);
             this.appointmentRepository.save(b);
         }
    }

    @Override
    public void  deleteAppointment(int doc , Date date , int pat ,float time){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
         Appointment a = this.appointmentRepository.findAppointmentByTime(doc , date);
         a.setCanceledByPat(true);
         this.appointmentRepository.save(a);

    }

    @Override
    public List<Appointment> todayAppDoc(int doc){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
         List<Appointment> lista = this.appointmentRepository.findAppointmentByDoc(doc);
         List<Appointment> today = new ArrayList<>();
         for(int i = 0 ; i<lista.size() ; i++) {
             Date in = new Date();
             LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
             Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
             DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                 String outWithZeroTime = formatter.format(out);
                 String listWithZeroTime = formatter.format(lista.get(i).getDateAndTime());

             if(listWithZeroTime.equals(outWithZeroTime)){
                 today.add(lista.get(i));
             }
         }
            return today;
    }

    @Override
    public List<Appointment> todayAppPat(int pat){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        List<Appointment> lista = this.appointmentRepository.findAppointmentByPat(pat);
        List<Appointment> today = new ArrayList<>();
        for(int i = 0 ; i<lista.size() ; i++) {
            Date in = new Date();
            LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
            Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String outWithZeroTime = formatter.format(out);
            String listWithZeroTime = formatter.format(lista.get(i).getDateAndTime());
            if(listWithZeroTime.equals(outWithZeroTime)){
                today.add(lista.get(i));
            }
        }
        return today;
    }
    @Override
    public List<Appointment> byTime(int idD , float time){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
         List<Appointment> lista = this.appointmentRepository.cancelAppointmentByTim2e(idD , time);
         return lista;
    }
}

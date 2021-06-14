package com.example.medontes5.Services;

import com.example.medontes5.DAL.AppointmentRepository;
import com.example.medontes5.Model.Appointment;
import com.example.medontes5.Model.DoctorEntity;
import com.example.medontes5.Model.PatientEntity;
import com.example.medontes5.OutputAdapters.SystemManagementModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService{
     @Autowired
    private AppointmentRepository appointmentRepository;
     @Autowired
     private SystemManagementModuleService systemManagementModuleService;

     @Override
    public void addNewFreeAppointments(Appointment a){
         this.appointmentRepository.save(a);
     }


     @Override
    public DoctorEntity getDoctorByPrNumber(int nrPersonal){
     return this.systemManagementModuleService.doctoriE(nrPersonal);
     }
    @Override
    public PatientEntity getPatientByPrNumber(int nrPersonal){
     return this.systemManagementModuleService.pacientiE(nrPersonal);
    }

    @Override
    public void editAppointment(int doc , Date data, PatientEntity p){
         Appointment a = this.appointmentRepository.findAppointmentByTime(doc , data);
         a.setFreeAppoint(false);
         a.setPatientEntity(p);
         this.appointmentRepository.save(a);


    }
    @Override
    public List<Appointment> getAppByDoc(int doc){
         List <Appointment> krejLista = this.appointmentRepository.findAppointmentByDoc(doc);
         List<Appointment> returnLista = new ArrayList<>();
         for(int i = 0 ; i<krejLista.size() ; i++){
             LocalDateTime now = LocalDateTime.now();
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
        List <Appointment> krejLista = this.appointmentRepository.findAppointmentByPat(pat);
        List<Appointment> returnLista = new ArrayList<>();
        for(int i = 0 ; i<krejLista.size() ; i++){
            LocalDateTime now = LocalDateTime.now();
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

}

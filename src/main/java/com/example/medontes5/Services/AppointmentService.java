package com.example.medontes5.Services;

import com.example.medontes5.DAL.AppointmentRepository;
import com.example.medontes5.Model.Appointment;
import com.example.medontes5.Model.DoctorEntity;
import com.example.medontes5.Model.PatientEntity;
import com.example.medontes5.OutputAdapters.SystemManagementModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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


    }


}

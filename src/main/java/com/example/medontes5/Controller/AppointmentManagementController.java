package com.example.medontes5.Controller;

import com.example.medontes5.Helper.AppointmentHelper;
import com.example.medontes5.Helper.AppointmentResponse;
import com.example.medontes5.Model.Appointment;
import com.example.medontes5.Model.DoctorEntity;
import com.example.medontes5.Model.PatientEntity;
import com.example.medontes5.Services.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/appointmentManagement")
public class AppointmentManagementController {

    @Autowired
    private IAppointmentService iAppointmentService;

    @PostMapping("/addNewAppointmentDoc")
    public AppointmentResponse addAppointment(@RequestBody AppointmentHelper appointmentHelper){
        DoctorEntity ap = this.iAppointmentService.getDoctorByPrNumber(appointmentHelper.getDocPrNumber());
        boolean free = true;
        Appointment a = new Appointment.AppointmentBuilder(appointmentHelper.getData(),free , ap).build();
        this.iAppointmentService.addNewFreeAppointments(a);
        return  new AppointmentResponse.AppointmentResponseBuilder<>(401).setMesazhin("Appointment added!").setData(a).build();


    }

   @PostMapping("/addNewAppointmentPat/{patPrNumber}/{docNumber}/{dateAndTime}")
    public AppointmentResponse addAppointmentPat(@PathVariable int patPrNumber , @PathVariable int docNumber , @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateAndTime){
       PatientEntity pa = this.iAppointmentService.getPatientByPrNumber(patPrNumber);
       if(pa !=null) {
           this.iAppointmentService.editAppointment(docNumber, dateAndTime, pa);
           return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("Appointment added!").build();
       }
       return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("Nuk ekziston ky person").build();

    }
    @GetMapping("/getAppByDoc/{personalNumber}")
    public AppointmentResponse getAppByDoc(@PathVariable int personalNumber){
        List<Appointment> lista = this.iAppointmentService.getAppByDoc(personalNumber);
        if(lista.size() != 0){
            return  new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e sukseshme").setData(lista).build();
        }else {
            return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("This doctor doesn't have appointments!").build();
        }
    }
    @GetMapping("/getAppByPat/{personalNumber}")
    public AppointmentResponse getAppByPat(@PathVariable int personalNumber){
        List<Appointment> lista = this.iAppointmentService.getAppByPat(personalNumber);
        if(lista.size() != 0){
            return  new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e sukseshme").setData(lista).build();
        }else {
            return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("You don't have appointments!").build();
        }
    }

}

package com.example.medontes5.Controller;

import com.example.medontes5.Helper.AppointmentHelper;
import com.example.medontes5.Helper.AppointmentResponse;
import com.example.medontes5.Model.Appointment;
import com.example.medontes5.Model.DoctorEntity;
import com.example.medontes5.Model.PatientEntity;
import com.example.medontes5.Services.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    //error ne shtimin e dates
   @PostMapping("/addNewAppointmentPat/{patPrNumber}/{docNumber}/{dateAndTime}")
    public AppointmentResponse addAppointmentPat(@PathVariable int patPrNumber , @PathVariable int docNumber , @PathVariable Date dateAndTime){
       PatientEntity pa = this.iAppointmentService.getPatientByPrNumber(patPrNumber);
       this.iAppointmentService.editAppointment(docNumber , dateAndTime , pa);
       return  new AppointmentResponse.AppointmentResponseBuilder<>(401).setMesazhin("Appointment added!").build();


    }


}

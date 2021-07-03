package com.example.medontes5.Controller;

import com.example.medontes5.Helper.AppointmentHelper;
import com.example.medontes5.Helper.AppointmentResponse;
import com.example.medontes5.Helper.CancelAppointment;
import com.example.medontes5.Model.Appointment;
import com.example.medontes5.Model.DoctorEntity;
import com.example.medontes5.Model.PatientEntity;
import com.example.medontes5.Services.IAppointmentService;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.ws.rs.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/appointmentManagement")
public class AppointmentManagementController {

    @Autowired
    private IAppointmentService iAppointmentService;

    //done
    @PostMapping("/addNewAppointmentDoc/{oraTerminit}/{doctorPersonalNumber}")
    public AppointmentResponse addAppointment(@RequestBody AppointmentHelper appointmentHelper, @PathVariable float oraTerminit, @PathVariable int doctorPersonalNumber) {
        List<Appointment> listaaa = this.getApp(doctorPersonalNumber, appointmentHelper.getData(), oraTerminit);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen"));
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        if (listaaa.size() == 0) {
            if (appointmentHelper.getData().compareTo(out) > 0 || appointmentHelper.getData().compareTo(out) == 0) {
                DoctorEntity ap = this.iAppointmentService.getDoctorByPrNumber(doctorPersonalNumber);
                boolean free = true;
                Date dataDuhur = appointmentHelper.getData();
                dataDuhur.setHours(20);
                Appointment a = new Appointment.AppointmentBuilder(dataDuhur, free, ap, oraTerminit).build();
                this.iAppointmentService.addNewFreeAppointments(a);
                return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("Appointment added!").setData(a).build();
            } else {
                return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("Please add a Appointments for coming days! ").build();
            }
        }

        return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("You already added this appointment!").build();

    }
 //done
    @PostMapping("/addNewAppointmentPat/{patPrNumber}/{docNumber}/{dateAndTime}/{time}")
    public AppointmentResponse addAppointmentPat(@PathVariable int patPrNumber, @PathVariable int docNumber, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateAndTime, @PathVariable float time) {
        PatientEntity pa = this.iAppointmentService.getPatientByPrNumber(patPrNumber);
        // Date data = dateAndTime;
        dateAndTime.setHours(20);
        List<Appointment> lista = this.iAppointmentService.listTM(docNumber, dateAndTime, time);
        if (lista.size() != 0) {
            if (lista.get(0).isFreeAppoint() == true && lista.get(0).isCanceledByDoc() == false) {
                this.iAppointmentService.editAppointment(docNumber, dateAndTime, pa, time);
                return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("Appointment added!").build();
            }
            return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("This appointment is not free").build();
        }
        return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("There is no this kind of free appointment").build();

    }

    //done
    @GetMapping("/getAppByDoc/{personalNumber}")
    public AppointmentResponse getAppByDoc(@PathVariable int personalNumber) {
        List<Appointment> lista = this.iAppointmentService.getAppByDoc(personalNumber);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen"));
        if (lista.size() != 0) {
            return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e sukseshme").setData(lista).build();
        } else {
            return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("This doctor doesn't have appointments!").build();
        }
    }
    //done
    @GetMapping("/getAppByPat/{personalNumber}")
    public AppointmentResponse getAppByPat(@PathVariable int personalNumber) {
        List<Appointment> lista = this.iAppointmentService.getAppByPat(personalNumber);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen"));
        if (lista.size() != 0) {


            return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e sukseshme").setData(lista).build();
        } else {
            return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("You don't have appointments!").build();
        }
    }

    //done
    @PostMapping("/cancelAppointment/{oraTerminit}/{doctorPersonalNumber}")
    public AppointmentResponse cancelAppointment(@RequestBody CancelAppointment cancelAppointment, @PathVariable float oraTerminit, @PathVariable int doctorPersonalNumber) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen"));
        List<Appointment> lista = this.getApp(doctorPersonalNumber, cancelAppointment.getData(), oraTerminit);
        if (lista.size() != 0) {
            if (lista.get(0).isCanceledByDoc() == false && lista.get(0).isCanceledByPat() == false) {
                this.iAppointmentService.cancelAppointment(doctorPersonalNumber, cancelAppointment.getData(), oraTerminit);
                return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("AppointmentCanceled!").setData("Appointment at " + cancelAppointment.getData() + " was canceled!").build();
            } else {
                return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("This Appointment is already canceled!").build();
            }
        } else {
            return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("You don't have this kind of appointment on the list").build();
        }
    }

    //@GetMapping("/gettAP")
    public List<Appointment> getApp(int docId, Date data, float ora) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen"));
        List<Appointment> listt = this.iAppointmentService.byTime(docId, ora);
        List<Appointment> lista2 = new ArrayList<>();
        Date data1 = data;
        data1.setHours(20);
        data1.setSeconds(0);
        data1.setMinutes(0);
        if (listt.size() != 0) {
            for (int i = 0; i < listt.size(); i++) {
                Appointment a = listt.get(i);
                Date dataa = a.getDateAndTime();
                dataa.setHours(20);
                dataa.setSeconds(0);
                dataa.setMinutes(0);


                if (dataa.compareTo(data1) == 0) {
                    //if (a.isCanceledByPat() == false && a.isCanceledByPat() == false) {
                    lista2.add(a);

                    //}
                }
            }
        }
        return lista2;
    }

//done
    @PostMapping("/deleteAppointment/{docId}/{date}/{patId}/{time}")
    public AppointmentResponse deleteAppointment(@PathVariable int docId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @PathVariable int patId, @PathVariable float time) {
        date.setHours(20);
        List<Appointment> lista = this.iAppointmentService.listaTP(docId, date, time, patId);
        if (lista.size() != 0 && lista.get(0).isCanceledByDoc() == false && lista.get(0).isCanceledByPat() == false) {
            this.iAppointmentService.deleteAppointment(docId, date, time, patId);
            TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen"));
            DoctorEntity de = this.iAppointmentService.getDoctorByPrNumber(docId);
            return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("Appointment Canceled!").setData("Appointment by:" + de.getDoctorName() + "" + de.getDoctorSurname() + " at " + date + "was canceled!").build();
        }
        return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("You don't have this kind of appointment to cancel").build();
    }

    //done
    @GetMapping("/getTodaysAppDoc/{docId}")
    public AppointmentResponse getTodaysAppDoc(@PathVariable int docId) {
        List<Appointment> lista = this.iAppointmentService.todayAppDoc(docId);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen"));
        if (lista.size() != 0) {
            return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e Sukseshshme!").setData(lista).build();
        }
        return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("There are no appointments today!").build();

    }
    //done
    @GetMapping("/getTodaysAppPat/{patId}")
    public AppointmentResponse getTodaysAppPat(@PathVariable int patId) {
        List<Appointment> lista = this.iAppointmentService.todayAppPat(patId);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen"));
        if (lista.size() != 0) {
            return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e Sukseshshme!").setData(lista).build();
        }
        return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("There are no appointments today!").build();
    }


//done
    @GetMapping("/admin/totalAppointments")
    public AppointmentResponse getTotalApp(){
        int nr = this.iAppointmentService.getAllApp();
        if(nr>0){
            return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e sukksese!").setData(nr).build();
        }else {
            return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("No appointments!").build();
        }
    }
//done
    @GetMapping("/admin/getAppointmentsForNextDays")
   public AppointmentResponse getNextApp(){
        int nr = this.iAppointmentService.nextDays();
        if(nr > 0){
            return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e suksese").setData(nr).build();
        }
        return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("There are no upcoming appointments").build();
    }


    //lidhe front
  @PostMapping("listOfDocs/{patID}")
    public AppointmentResponse listofDoc( @PathVariable int patID){
        List<Appointment> lista = this.iAppointmentService.getAppByUsers( patID);
        if(lista.size() != 0){
            List<DoctorEntity> listt = this.iAppointmentService.listaD(patID);
            return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(listt).build();
        }
        return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("You don't have a list of personal doctor's!").build();
    }
   //almost done
    @GetMapping("listOfPats/{docID}")
    public AppointmentResponse listofPat(@PathVariable int docID ){
        List<Appointment> lista = this.iAppointmentService.getAppByUsers(docID );
        if(lista.size() != 0) {
            List<PatientEntity> listt = this.iAppointmentService.lista(docID);
            if (listt.size() != 0) {
                return new AppointmentResponse.AppointmentResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(listt).build();
            }
            return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("You don't have a list of personal doctor's!").build();

        }
        return new AppointmentResponse.AppointmentResponseBuilder<>(401).setErrorin("You don't have a list of personal doctor's!").build();
    }
}

package com.example.medontes5.DAL;

import com.example.medontes5.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("select c from Appointment c where c.doctorEntity.doctorPersonalNumber=?1 and c.dateAndTime=?2")
    Appointment findAppointmentByTime(int doc , Date data);

    @Query("select c from Appointment c where c.doctorEntity.doctorPersonalNumber=?1")
    List<Appointment> findAppointmentByDoc(int doc);

    @Query("select c from Appointment c where c.patientEntity.personalNumber=?1")
    List<Appointment> findAppointmentByPat(int pat);

    @Query("select c from Appointment c where c.doctorEntity.doctorPersonalNumber=?1 and c.dateAndTime=?2 and c.patientEntity.personalNumber=?3")
    Appointment cancelAppointmentByTime(int doc , Date data, int pat);

}

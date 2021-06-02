package com.example.medontes5.DAL;

import com.example.medontes5.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}

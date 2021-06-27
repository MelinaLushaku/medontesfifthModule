package com.example.medontes5.OutputAdapters;

import com.example.medontes5.Model.DoctorEntity;
import com.example.medontes5.Model.PatientEntity;

public interface ISystemManagementModuleService {
    PatientEntity pacientiE(int nrPersonal);
    DoctorEntity doctoriE(int doctorPersonalNumber);
}

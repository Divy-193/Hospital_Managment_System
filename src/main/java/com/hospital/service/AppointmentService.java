package com.hospital.service;

import com.hospital.entity.Appointment;
import com.hospital.entity.Doctor;
import com.hospital.entity.Patient;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private  final DoctorRepository doctorRepository;
    private  final PatientRepository patientRepository;


    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository)
    {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }


    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId){

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have been arranged");

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

       return appointmentRepository.save(appointment);
    }
    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);
        return appointment;
    }

}
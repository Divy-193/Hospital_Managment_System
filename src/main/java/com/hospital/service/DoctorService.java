package com.hospital.service;

import com.hospital.dto.DoctorResponseDto;
import com.hospital.dto.OnboardDoctorRequestDto;
import com.hospital.entity.Doctor;
import com.hospital.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnboardDoctorRequestDto onBoardDoctorRequestDto) {
        Doctor doctor = new Doctor();
        doctor.setName(onBoardDoctorRequestDto.getName());
        doctor.setSpecialization(onBoardDoctorRequestDto.getSpecialization());

        return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }
}
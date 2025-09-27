package com.hospital.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardDoctorRequestDto {
    private Long userId;
    private String specialization;
    private String name;
}
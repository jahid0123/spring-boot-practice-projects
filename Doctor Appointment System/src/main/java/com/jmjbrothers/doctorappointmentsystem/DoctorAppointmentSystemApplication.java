package com.jmjbrothers.doctorappointmentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.jmjbrothers.doctorappointmentsystem.model")
@EnableJpaRepositories(basePackages = "com.jmjbrothers.doctorappointmentsystem.repository")
public class DoctorAppointmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorAppointmentSystemApplication.class, args);
    }

}

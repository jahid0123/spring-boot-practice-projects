import { Component, OnInit } from '@angular/core';
import { DoctorRegisterRequestDto, DoctorResponseDto } from '../../../model/model.classes';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-patient-home',
  imports: [NgFor],
  templateUrl: './patient-home.component.html',
  styleUrl: './patient-home.component.css'
})
export class PatientHomeComponent implements OnInit {

  doctors: DoctorResponseDto[] = [];

  ngOnInit(): void {
    // Static sample data — replace with API call later
    this.doctors = [
      {
        name: 'Dr. Jahid Ahmed',
        specialization: 'Cardiologist',
        qualification: 'MBBS, MD',
        experience: 10,
        hospitalName: 'Green Life Hospital',
        phone: '017xxxxxxxx',
        image: 'assets/images/doctors/doc1.jpg'
      },
      {
        name: 'Dr. Sara Hossain',
        specialization: 'Dermatologist',
        qualification: 'MBBS, FCPS',
        experience: 6,
        hospitalName: 'City Medical',
        phone: '018xxxxxxxx',
        image: 'assets/images/doctors/doc2.jpg'
      }
    ];
  }

  bookAppointment(doctor: DoctorResponseDto): void {
  console.log('Booking appointment with:', doctor.name);
  // Redirect or open appointment modal
}

saveFavorite(doctor: DoctorResponseDto): void {
  console.log('Saved as favorite:', doctor.name);
  // Save to local storage or call backend API
}

} 
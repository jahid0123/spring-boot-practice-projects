import { Component, OnInit } from '@angular/core';
import {
  DoctorListDto,
  DoctorRegisterRequestDto,
  DoctorResponseDto,
} from '../../../model/model.classes';
import { NgFor, NgIf } from '@angular/common';
import { PatientHomeService } from './service/patient-home.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-home',
  imports: [NgFor],
  templateUrl: './patient-home.component.html',
  styleUrl: './patient-home.component.css',
})
export class PatientHomeComponent implements OnInit {
  doctors: DoctorListDto[] = [];

  constructor(
    private patientHomeService: PatientHomeService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.patientHomeService.getAllDoctors().subscribe({
      next: (data) => (this.doctors = data),
      error: (err) => console.error('Error fetching doctors:', err),
    });
  }

  bookAppointment(doctor: DoctorResponseDto): void {
    console.log('Booking appointment with:', doctor.name);
    this.router.navigateByUrl('/patient/appointment', {
      state: { doctor }, // pass the whole doctor object
    });
  }

  saveFavorite(doctor: DoctorResponseDto): void {
    const favoritesKey = 'favoriteDoctors';

    // Get existing favorites or initialize an empty array
    let favorites = JSON.parse(localStorage.getItem(favoritesKey) || '[]');

    // Check if the property is already saved
    const exists = favorites.some(
      (p: any) => p.name === doctor.name && p.phone === doctor.phone,
    );

    if (!exists) {
      favorites.push(doctor);
      localStorage.setItem(favoritesKey, JSON.stringify(favorites));
      alert('Doctor added to favorites!');
    } else {
      alert('Doctor already in favorites.');
    }
  }
}

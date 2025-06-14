import { Component, OnInit } from '@angular/core';
import { DoctorResponseDto } from '../../../model/model.classes';
import { Router } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-patient-favorite',
  imports: [NgFor, NgIf],
  templateUrl: './patient-favorite.component.html',
  styleUrl: './patient-favorite.component.css'
})
export class PatientFavoriteComponent implements OnInit {
  favoriteDoctors: DoctorResponseDto[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    const data = localStorage.getItem('favoriteDoctors');
    this.favoriteDoctors = data ? JSON.parse(data) : [];
  }

  removeDoctor(doctor: DoctorResponseDto): void {
    this.favoriteDoctors = this.favoriteDoctors.filter(
      (d) => d.phone !== doctor.phone || d.name !== doctor.name
    );
    localStorage.setItem('favoriteDoctors', JSON.stringify(this.favoriteDoctors));
  }

  bookAppointment(doctor: DoctorResponseDto): void {
    // Optionally pass doctor info via router state
    this.router.navigateByUrl('/patient/appointment', {
      state: { doctor }, // pass the whole doctor object
    });
  }
}

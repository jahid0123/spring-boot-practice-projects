import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AddService } from './service/add.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-property',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-property.component.html',
  styleUrl: './add-property.component.css'
})
export class AddPropertyComponent implements OnInit {
  propertyForm!: FormGroup;
  selectedFiles: File[] = [];
  previewUrls: string[] = [];
  types = [
    'COMMERCIAL',
    'RESIDENTIAL'
  ];

  constructor(
    private fb: FormBuilder,
    private postService: AddService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.propertyForm = this.fb.group({
      title: [''],
      type: [''],
      description: [''],
      address: [''],
      price: [''],
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      this.selectedFiles = Array.from(input.files);
      this.previewUrls = [];

      this.selectedFiles.forEach((file) => {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.previewUrls.push(e.target.result);
        };
        reader.readAsDataURL(file);
      });
    }
  }

  onSubmit(): void {
    const userID = Number(localStorage.getItem('id'));

    if (this.propertyForm.valid) {
      const {
        title,
        type,
        description,
        address,
        price,
      } = this.propertyForm.value;

      const propertyPostData = {
        userID,
        title,
        type,
        description,
        address,
        price,
      };

      this.postService
        .postPropertyWithImages(propertyPostData, this.selectedFiles)
        .subscribe({
          next: (res) => {
            alert("Successfully Posted..");
            this.propertyForm.reset();
            this.previewUrls = [];
            this.selectedFiles = [];
            this.router.navigateByUrl('/user-dashboard/home');
          },
          error: (err) => {
            console.error('Post error:', err);
            alert(err.error.message);
          },
        });
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AddService } from './service/add.service';
import { Router } from '@angular/router';
import { CommonModule, NgClass, NgFor } from '@angular/common';
import { GetPostedProperty } from '../../model class/user';

@Component({
  selector: 'app-add-property',
  imports: [ReactiveFormsModule, CommonModule, NgClass, FormsModule],
  templateUrl: './add-property.component.html',
  styleUrl: './add-property.component.css'
})
export class AddPropertyComponent implements OnInit {

  propertyForm!: FormGroup;
  selectedFiles: File[] = [];
  previewUrls: string[] = [];
  existingImageUrls: string[] = [];

  types = ['APARTMENT', 'HOUSE', 'COMMERCIAL', 'LAND'];
  bedroomOptions = [0, 1, 2, 3, 4, 5, 6];
  bathroomOptions = [0, 1, 2, 3, 4, 5];
  garageOptions = [0, 1, 2, 3];

  editData: GetPostedProperty | null = null;

  constructor(
    private fb: FormBuilder,
    private postService: AddService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.propertyForm = this.fb.group({
      title: ['', Validators.required],
      type: ['', Validators.required],
      bedroom: [0, Validators.required],
      bathroom: [0, Validators.required],
      garage: [0, Validators.required],
      size: [0, [Validators.required, Validators.min(1)]],
      yearBuilt: [0, [Validators.required, Validators.min(1900)]],
      description: ['', Validators.required],
      address: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
    });

    const state = history.state;
    if (state && state.property) {
      this.editData = state.property as GetPostedProperty;
      this.patchFormWithEditData(this.editData);
    }
  }

  patchFormWithEditData(data: GetPostedProperty): void {
    this.propertyForm.patchValue({
      title: data.title,
      type: data.type,
      bedroom: data.propertyBedroom,
      bathroom: data.propertyBathroom,
      garage: data.propertyGarage,
      size: data.propertySize,
      yearBuilt: data.propertyYearBuilt,
      description: data.description,
      address: data.address,
      price: data.propertyPrice,
    });

    this.existingImageUrls = data.imageUrls?.map(filename =>
      `http://localhost:8081/api/auth/images/${filename}`
    ) || [];
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      this.selectedFiles = Array.from(input.files);
      this.previewUrls = [];

      this.selectedFiles.forEach(file => {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.previewUrls.push(e.target.result);
        };
        reader.readAsDataURL(file);
      });
    }
  }

  removeSelectedImage(index: number): void {
    this.selectedFiles.splice(index, 1);
    this.previewUrls.splice(index, 1);
  }

  removeExistingImage(index: number): void {
    this.existingImageUrls.splice(index, 1);
    if (this.editData?.imageUrls) {
      this.editData.imageUrls.splice(index, 1);
    }
  }

  onSubmit(): void {
    if (this.propertyForm.invalid) return;

    const userID = Number(localStorage.getItem('id'));
    const formData = this.propertyForm.value;

    const propertyPostData: any = {
      userID,
      ...formData,
    };

    if (this.editData) {
      propertyPostData.postId = this.editData.id;
      propertyPostData.propertyID = this.editData.id;
      propertyPostData.imageNames = this.editData.imageUrls || [];

      this.postService.updatePropertyWithImages(propertyPostData, this.selectedFiles)
        .subscribe({
          next: () => {
            alert("Property updated successfully.");
            this.router.navigateByUrl('/seller-dashboard/seller-home');
          },
          error: (err) => {
            console.error('Update error:', err);
            alert(err.error.message || 'Failed to update property.');
          }
        });
    } else {
      this.postService.postPropertyWithImages(propertyPostData, this.selectedFiles)
        .subscribe({
          next: () => {
            alert("Property posted successfully.");
            this.propertyForm.reset();
            this.previewUrls = [];
            this.selectedFiles = [];
            this.router.navigateByUrl('/seller-dashboard/seller-home');
          },
          error: (err) => {
            console.error('Post error:', err);
            alert(err.error.message || 'Failed to post property.');
          }
        });
    }
  }
}
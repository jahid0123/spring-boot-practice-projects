import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-manage-bed',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './manage-bed.component.html',
  styleUrl: './manage-bed.component.css'
})
export class ManageBedComponent implements OnInit {
  bedForm!: FormGroup;
  beds: any[] = [];
  isEditMode = false;
  editingBedId: number | null = null;

  constructor(private fb: FormBuilder, private adminService: AdminService) {}

  ngOnInit(): void {
    this.bedForm = this.fb.group({
      ward: ['', Validators.required],
      bedNumber: ['', Validators.required],
      feePerDay: [null, [Validators.required, Validators.min(0)]],
      occupied: [false],
    });

    this.getAllBeds();
  }

  getAllBeds(): void {
    this.adminService.getAllBed().subscribe({
      next: (res) => (this.beds = res),
      error: (err) => console.error('Error fetching beds:', err),
    });
  }

  onSubmit(): void {
    if (this.bedForm.invalid) {
      this.bedForm.markAllAsTouched();
      return;
    }

    const payload = {
      ...this.bedForm.value,
      id: this.editingBedId,
    };

    if (this.isEditMode) {
      this.adminService.editBed(payload).subscribe({
        next: () => {
          this.resetForm();
          this.getAllBeds();
        },
        error: (err) => console.error('Error updating bed:', err),
      });
    } else {
      this.adminService.addBed(payload).subscribe({
        next: () => {
          this.resetForm();
          this.getAllBeds();
        },
        error: (err) => console.error('Error adding bed:', err),
      });
    }
  }

  onEdit(bed: any): void {
    this.bedForm.patchValue({
      ward: bed.ward,
      bedNumber: bed.bedNumber,
      feePerDay: bed.feePerDay,
      occupied: bed.occupied,
    });
    this.editingBedId = bed.id;
    this.isEditMode = true;
  }

  onDelete(id: number): void {
    if (confirm('Are you sure you want to delete this bed?')) {
      this.adminService.deleteBed(id).subscribe({
        next: () => this.getAllBeds(),
        error: (err) => console.error('Error deleting bed:', err),
      });
    }
  }

  onOccupiedChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.bedForm.patchValue({ isOccupied: input.checked });
  }

  resetForm(): void {
    this.bedForm.reset({ isOccupied: false });
    this.isEditMode = false;
    this.editingBedId = null;
  }
}
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, signal } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import {
  AngularGridInstance,
  AngularSlickgridModule,
  Column,
  GridOption,
} from 'angular-slickgrid';

@Component({
  selector: 'app-root',
  imports: [ReactiveFormsModule, CommonModule, AngularSlickgridModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  employeeFrom!: FormGroup;
  columnDefinitions = signal<Column[]>([]);
  gridOptions = signal<GridOption>({});
  employeeList = signal<any[]>([]);
  showGrid = signal<boolean>(false);

  angularGrid!: AngularGridInstance;

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.employeeFrom = this.fb.group({
      name: ['', Validators.required],
      department: ['', Validators.required],
      designation: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      address: ['', Validators.required],
    });

    this.columnDefinitions.set([
      { id: 'id', name: 'ID', field: 'id' },
      { id: 'name', name: 'Name', field: 'name' },
      { id: 'email', name: 'Email', field: 'email' },
      { id: 'designation', name: 'Designation', field: 'designation' },
      { id: 'department', name: 'Department', field: 'department' },
      { id: 'phone', name: 'Phone', field: 'phone' },
      { id: 'address', name: 'Address', field: 'address' },
    ]);

    this.gridOptions.set({
      enableAutoResize: true,
      enableSorting: true,
      autoFitColumnsOnFirstLoad: true,
    });
  }

  get f() {
    return this.employeeFrom.controls;
  }

  handleGridCreated(event: any) {
  this.angularGrid = event.detail;
}


  onSubmit() {
    if (this.employeeFrom.valid) {
      const formData = this.employeeFrom.value;

      this.http
        .post<any[]>('http://localhost:8080/api/employee/save', formData)
        .subscribe({
          next: (res) => {
            this.employeeList.set(res), this.showGrid.set(true);
          },
          error: (err) => console.error(err),
        });
    } else {
      this.employeeFrom.markAllAsTouched();
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { AdminHomeService } from './service/admin-home.service';
import { OrderResponse } from '../../../model/class';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-admin-home',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent implements OnInit {
  orders: any[] = [];
  selectedOrder: any = null;
  isLoading = true;
  error: string | null = null;

  constructor(private orderService: AdminHomeService) {}

  ngOnInit(): void {
    this.fetchOrders();
  }

  fetchOrders(): void {
    this.isLoading = true;
    this.orderService.getAllOrders().subscribe({
      next: (data) => {
        this.orders = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.error = 'Failed to load orders';
        this.isLoading = false;
        console.error(err);
      }
    });
  }

  viewOrder(order: any): void {
    this.selectedOrder = order;

    const modalElement = document.getElementById('orderModal');
    if (modalElement) {
      const modal = new Modal(modalElement);
      modal.show();
    }
  }

  closeModal(): void {
    const modalElement = document.getElementById('orderModal');
    if (modalElement) {
      const modal = Modal.getInstance(modalElement);
      if (modal) {
        modal.hide();
      }
    }
    this.selectedOrder = null;
  }

  markAsShipped(order: any): void {
    // Optional: call backend API to update status
    order.status = 'Shipped'; // local update for UI
    console.log('Order marked as shipped:', order);
  }
}

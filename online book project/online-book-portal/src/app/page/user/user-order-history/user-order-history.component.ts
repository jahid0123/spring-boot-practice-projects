import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../../../model/class';
import { StorageServiceService } from '../../../core/service/storage-service.service';
import { UserService } from '../user-dashboard/service/user.service';
import { Modal } from 'bootstrap';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-book-details',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './user-order-history.component.html',
  styleUrl: './user-order-history.component.css'
})
export class UserOrderHistoryComponent implements OnInit {
  orders: any[] = [];
  selectedOrder: any = null;
  isLoading = true;
  error: string | null = null;

  constructor(private orderService: UserService) {}

  ngOnInit(): void {
    this.fetchOrders();
  }

  fetchOrders(): void {
    this.isLoading = true;
    this.orderService.getMyAllOrder().subscribe({
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
      modal?.hide();
    }
    this.selectedOrder = null;
  }

  deleteOrder(orderId: number): void {
    const confirmed = confirm(`Are you sure you want to delete Order #${orderId}?`);
    if (!confirmed) return;

    this.orderService.deleteMyOrder(orderId).subscribe({
      next: () => {
        this.orders = this.orders.filter(order => order.orderId !== orderId);
        alert(`Order #${orderId} deleted successfully.`);
      },
      error: (err) => {
        console.error('Failed to delete order:', err);
        alert('Failed to delete order.');
      }
    });
  }

  canDelete(order: any): boolean {
    return order.orderStatus === 'INPROGRESS';
  }
}
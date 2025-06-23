import { Component, OnInit } from '@angular/core';
import { AdminHomeService } from '../admin-home/service/admin-home.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-order-history',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './order-history.component.html',
  styleUrl: './order-history.component.css'
})
export class OrderHistoryComponent implements OnInit {
  orders: any[] = [];
  filteredOrders: any[] = [];
  selectedOrder: any = null;
  isLoading = true;
  error: string | null = null;
  selectedStatus: string = 'ALL';

  constructor(private orderService: AdminHomeService) {}

  ngOnInit(): void {
    this.fetchOrders();
  }

  fetchOrders(): void {
    this.isLoading = true;
    this.orderService.getAllOrders().subscribe({
      next: (data) => {
        this.orders = data;
        this.filterByStatus(this.selectedStatus);
        this.isLoading = false;
      },
      error: (err) => {
        this.error = 'Failed to load orders';
        this.isLoading = false;
        console.error(err);
      }
    });
  }

  filterByStatus(status: string): void {
    this.selectedStatus = status;
    if (status === 'ALL') {
      this.filteredOrders = this.orders;
    } else {
      this.filteredOrders = this.orders.filter(order => order.orderStatus === status);
    }
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

  markAsShipped(order: any): void {
    // Update status locally
    order.status = 'DELIVERED';
    console.log('Order marked as delivered:', order);
  }

  deleteOrder(order: any): void {
    const confirmed = confirm(`Are you sure you want to delete Order #${order.orderId}?`);
    if (!confirmed) return;

    this.orderService.deleteOrder(order.orderId).subscribe({
      next: () => {
        this.orders = this.orders.filter(o => o.orderId !== order.orderId);
        this.filterByStatus(this.selectedStatus);
        console.log(`Order #${order.orderId} deleted successfully.`);
      },
      error: (err) => {
        console.error('Failed to delete order:', err);
        alert('Failed to delete order. Please try again.');
      }
    });
  }
}
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

  markAsDelivered(orderId: number): void {
    this.orderService.orderDelivered(orderId).subscribe({
      next: () => {
        this.updateOrderStatus(orderId, 'DELIVERED');
        alert(`Order #${orderId} marked as Delivered.`);
      },
      error: (err) => {
        console.error('Failed to mark as delivered:', err);
        alert('Failed to mark order as delivered.');
      }
    });
  }

  markAsCompleted(orderId: number): void {
    this.orderService.orderCompleted(orderId).subscribe({
      next: () => {
        this.updateOrderStatus(orderId, 'COMPLETED');
        alert(`Order #${orderId} marked as Completed.`);
      },
      error: (err) => {
        console.error('Failed to mark as completed:', err);
        alert('Failed to mark order as completed.');
      }
    });
  }

  markAsCancelled(orderId: number): void {
    this.orderService.orderCancelled(orderId).subscribe({
      next: () => {
        this.updateOrderStatus(orderId, 'CANCELLED');
        alert(`Order #${orderId} marked as Cancelled.`);
      },
      error: (err) => {
        console.error('Failed to cancel order:', err);
        alert('Failed to cancel order.');
      }
    });
  }

  deleteOrder(orderId: number): void {
    const confirmed = confirm(`Are you sure you want to delete Order #${orderId}?`);
    if (!confirmed) return;

    this.orderService.deleteOrder(orderId).subscribe({
      next: () => {
        this.orders = this.orders.filter(order => order.orderId !== orderId);
        this.filterByStatus(this.selectedStatus);
        alert(`Order #${orderId} deleted successfully.`);
      },
      error: (err) => {
        console.error('Failed to delete order:', err);
        alert('Failed to delete order.');
      }
    });
  }

  private updateOrderStatus(orderId: number, newStatus: string): void {
    const order = this.orders.find(o => o.orderId === orderId);
    if (order) {
      order.orderStatus = newStatus;
      this.filterByStatus(this.selectedStatus);
    }
  }
}
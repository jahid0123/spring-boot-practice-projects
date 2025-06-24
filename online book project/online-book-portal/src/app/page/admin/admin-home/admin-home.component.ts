import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AdminHomeService } from './service/admin-home.service';
import { UserService } from '../../user/user-dashboard/service/user.service';

@Component({
  selector: 'app-admin-home',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent implements OnInit {
  orders: any[] = [];

  ordersInProgressCount = 0;
  ordersInProgressTotal = 0;

  ordersDeliveredCount = 0;
  ordersDeliveredTotal = 0;

  ordersCancelledCount = 0;
  ordersCancelledTotal = 0;

  ordersCompletedCount = 0;
  ordersCompletedTotal = 0;

  totalUsersCount = 0;

  constructor(private orderService: AdminHomeService, private userService: UserService) {}

  ngOnInit(): void {
    this.fetchOrders();
    this.fetchUserCount();
  }

  fetchOrders(): void {
    this.orderService.getAllOrders().subscribe({
      next: (orders) => {
        this.orders = orders;
        this.calculateOrderStats();
      },
      error: (err) => {
        console.error('Error fetching orders:', err);
      },
    });
  }

  fetchUserCount(): void {
    // Assuming getUser() returns array of users, not count directly
    this.userService.getUser().subscribe({
      next: (users) => {
        this.totalUsersCount = Array.isArray(users) ? users.length : 0;
      },
      error: (err) => {
        console.error('Error fetching user count:', err);
      },
    });
  }

  calculateOrderStats(): void {
    this.ordersInProgressCount = 0;
    this.ordersInProgressTotal = 0;
    this.ordersDeliveredCount = 0;
    this.ordersDeliveredTotal = 0;
    this.ordersCancelledCount = 0;
    this.ordersCancelledTotal = 0;
    this.ordersCompletedCount = 0;
    this.ordersCompletedTotal = 0;

    this.orders.forEach((order) => {
      const status = order.orderStatus.toUpperCase();
      const price = Number(order.orderPrice) || 0;

      switch (status) {
        case 'INPROGRESS':
        case 'IN_PROGRESS':
          this.ordersInProgressCount++;
          this.ordersInProgressTotal += price;
          break;
        case 'DELIVERED':
          this.ordersDeliveredCount++;
          this.ordersDeliveredTotal += price;
          break;
        case 'CANCELLED':
          this.ordersCancelledCount++;
          this.ordersCancelledTotal += price;
          break;
        case 'COMPLETED':
          this.ordersCompletedCount++;
          this.ordersCompletedTotal += price;
          break;
      }
    });

    this.ordersInProgressTotal = this.round2(this.ordersInProgressTotal);
    this.ordersDeliveredTotal = this.round2(this.ordersDeliveredTotal);
    this.ordersCancelledTotal = this.round2(this.ordersCancelledTotal);
    this.ordersCompletedTotal = this.round2(this.ordersCompletedTotal);
  }

  private round2(value: number): number {
    return Math.round(value * 100) / 100;
  }
}
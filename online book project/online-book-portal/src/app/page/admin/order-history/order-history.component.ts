import { Component, OnInit } from '@angular/core';
import { AdminHomeService } from '../admin-home/service/admin-home.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-order-history',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './order-history.component.html',
  styleUrl: './order-history.component.css'
})
export class OrderHistoryComponent implements OnInit {
  orders: any[] = [];
  isLoading = true;
  error: string | null = null;

  constructor(private orderService: AdminHomeService) {}

  ngOnInit(): void {
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
    console.log("Viewing order:", order);
  }

  markAsShipped(order: any): void {
    order.status = 'Shipped';
    console.log("Order marked as shipped:", order);
  }
}


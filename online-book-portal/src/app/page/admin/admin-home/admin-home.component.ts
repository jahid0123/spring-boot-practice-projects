import { Component, OnInit } from '@angular/core';
import { AdminHomeService } from './service/admin-home.service';
import { OrderResponse } from '../../../model/class';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-admin-home',
  imports: [NgFor, NgIf],
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent implements OnInit {
  orders: OrderResponse[] = [];

  constructor(private orderService: AdminHomeService) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders() {
    this.orderService.getAllOrders().subscribe({
      next: (data) => (this.orders = data),
      error: (err) => console.error('Failed to load orders:', err),
    });
  }
}

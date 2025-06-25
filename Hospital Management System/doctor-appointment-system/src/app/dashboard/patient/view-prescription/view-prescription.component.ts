import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';

import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

import { PrescriptionResponseDto } from '../../../model/model.classes';

@Component({
  selector: 'app-view-prescription',
  standalone: true,
  imports: [NgFor, CommonModule, ReactiveFormsModule],
  templateUrl: './view-prescription.component.html',
  styleUrl: './view-prescription.component.css',
})
export class ViewPrescriptionComponent {
  prescription!: PrescriptionResponseDto;

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras?.state as {
      prescription: PrescriptionResponseDto;
    };
    if (state && state.prescription) {
      this.prescription = state.prescription;
    }
  }

  printPrescription() {
    const printContents = document.getElementById(
      'prescription-container'
    )?.innerHTML;
    const originalContents = document.body.innerHTML;

    if (printContents) {
      document.body.innerHTML = printContents;
      window.print();
      document.body.innerHTML = originalContents;
      window.location.reload();
    }
  }

  downloadPdf() {
    const content = document.getElementById('prescription-container');
    if (!content) return;

    const options = {
      scale: 2,
      scrollY: 0,
      useCORS: true,
    } as any;

    html2canvas(content, options).then((canvas) => {
      const imgData = canvas.toDataURL('image/png');

      const pdf = new jsPDF('p', 'mm', 'a4');
      const pdfWidth = pdf.internal.pageSize.getWidth();
      const pdfHeight = pdf.internal.pageSize.getHeight();

      const imgWidth = pdfWidth;
      const imgHeight = (canvas.height * imgWidth) / canvas.width;

      let heightLeft = imgHeight;
      let position = 0;

      // Add first page
      pdf.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
      heightLeft -= pdfHeight;

      // Add additional pages if needed
      while (heightLeft > 0) {
        pdf.addPage();
        position = heightLeft - imgHeight;
        pdf.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
        heightLeft -= pdfHeight;
      }

      // Save the file
      pdf.save('prescription.pdf');
    });
  }
}

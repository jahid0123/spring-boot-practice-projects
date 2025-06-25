package com.jmjbrothers.doctorsappointmentsystem.service;

import com.jmjbrothers.doctorsappointmentsystem.model.Admission;
import com.jmjbrothers.doctorsappointmentsystem.model.Bill;
import com.jmjbrothers.doctorsappointmentsystem.repository.AdmissionRepository;
import com.jmjbrothers.doctorsappointmentsystem.repository.BillRepository;
import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final AdmissionRepository admissionRepository;

    public BillService(BillRepository billRepository, AdmissionRepository admissionRepository) {
        this.billRepository = billRepository;
        this.admissionRepository = admissionRepository;
    }

    public Bill generateBill(Long admissionId) {
        Admission admission = admissionRepository.findById(admissionId).orElseThrow();

        LocalDate admitDate = admission.getAdmissionDate();
        LocalDate dischargeDate = admission.getDischargeDate() != null
                ? admission.getDischargeDate()
                : LocalDate.now();

        long days = ChronoUnit.DAYS.between(admitDate, dischargeDate);
        if (days == 0) days = 1;

        double roomCharge = days * admission.getBed().getFeePerDay();
        double doctorFee = 1000.0; // Example fixed fee
        double medicineCost = 1500.0; // Example (or fetch from prescriptions)

        double total = roomCharge + doctorFee + medicineCost;

        Bill bill = new Bill();
        bill.setAdmission(admission);
        bill.setRoomCharge(roomCharge);
        bill.setDoctorFee(doctorFee);
        bill.setMedicineCost(medicineCost);
        bill.setTotal(total);

        return billRepository.save(bill);
    }


    public byte[] generateReceiptPdf(Long admissionId) {
        Bill bill = billRepository.findByAdmissionId(admissionId);
        if (bill == null) {
            throw new IllegalArgumentException("Bill not found for admission ID: " + admissionId);
        }

        try {
            // Load Jasper template from resources
            InputStream reportStream = getClass().getResourceAsStream("/reports/receipt_template.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Parameters to fill in the report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("roomCharge", bill.getRoomCharge());
            parameters.put("doctorFee", bill.getDoctorFee());
            parameters.put("medicineCost", bill.getMedicineCost());
            parameters.put("total", bill.getTotal());

            // Fill the report with parameters and empty data source
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            // Export to PDF bytes
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }

    @Transactional
    public Bill createBill(Long admissionId, Double doctorFee, Double medicineCost) {
        Admission admission = admissionRepository.findById(admissionId)
                .orElseThrow(() -> new RuntimeException("Admission not found"));

        // Calculate room charge based on stay duration and bed fee
        LocalDate admissionDate = admission.getAdmissionDate();
        LocalDate dischargeDate = admission.getDischargeDate() != null ? admission.getDischargeDate() : LocalDate.now();

        long daysStayed = ChronoUnit.DAYS.between(admissionDate, dischargeDate);
        if (daysStayed == 0) daysStayed = 1;  // Minimum one day charge

        double roomCharge = daysStayed * admission.getBed().getFeePerDay();

        double total = roomCharge + doctorFee + medicineCost;

        Bill bill = new Bill();
        bill.setAdmission(admission);
        bill.setRoomCharge(roomCharge);
        bill.setDoctorFee(doctorFee);
        bill.setMedicineCost(medicineCost);
        bill.setTotal(total);

        admission.setTotalAmount(total);
        admissionRepository.save(admission);

        return billRepository.save(bill);
    }

    public Bill getBillByAdmissionId(Long admissionId) {
        Bill bill = billRepository.findByAdmissionId(admissionId);
        if (bill == null) {
            throw new IllegalArgumentException("Bill not found for admission ID: " + admissionId);
        }
        return bill;
    }

}

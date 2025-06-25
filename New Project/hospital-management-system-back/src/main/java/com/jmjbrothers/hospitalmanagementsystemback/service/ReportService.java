package com.jmjbrothers.hospitalmanagementsystemback.service;

import com.jmjbrothers.hospitalmanagementsystemback.model.Bill;
import com.jmjbrothers.hospitalmanagementsystemback.repository.BillRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final BillRepository billRepository;
    private JasperReport billTemplate;

    @PostConstruct
    public void loadTemplate() throws Exception {
        try (InputStream in = new ClassPathResource("reports/bill_template.jrxml").getInputStream()) {
            billTemplate = JasperCompileManager.compileReport(in);
        }
    }

    public byte[] generateBillPdf(Long billId) throws Exception {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        Map<String, Object> params = new HashMap<>();
        params.put("patientName", bill.getAdmission().getPatient().getName());
        params.put("admissionDate", bill.getAdmission().getAdmissionDate().toString());
        params.put("dischargeDate", bill.getAdmission().getDischargeDate().toString());
        params.put("roomCharge", bill.getRoomCharge());
        params.put("doctorFee", bill.getDoctorFee());
        params.put("medicineCost", bill.getMedicineCost());
        params.put("total", bill.getTotal());

        JasperPrint jasperPrint = JasperFillManager.fillReport(billTemplate, params, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
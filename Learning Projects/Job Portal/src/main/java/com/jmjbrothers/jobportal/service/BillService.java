package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.model.Bill;
import com.jmjbrothers.jobportal.repository.BillRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Transactional
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }


    @Transactional
    public List<Bill> getMyAllBills(Long companyId) {
        List<Bill> bills = billRepository.findAllByCompany_Id(companyId);
        return bills;
    }
}

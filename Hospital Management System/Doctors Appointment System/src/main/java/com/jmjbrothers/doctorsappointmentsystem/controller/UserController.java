package com.jmjbrothers.doctorsappointmentsystem.controller;

import com.jmjbrothers.doctorsappointmentsystem.common.DashboardStatsDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.*;
import com.jmjbrothers.doctorsappointmentsystem.model.*;
import com.jmjbrothers.doctorsappointmentsystem.repository.BillRepository;
import com.jmjbrothers.doctorsappointmentsystem.service.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    private final DoctorService doctorService;
    private final UserService userService;
    private final PatientService patientService;

    private final AppointmentService appointmentService;
    private final BedService bedService;
    private final AdmissionService admissionService;
    private final DashboardService dashboardService;
    private final BillService billService;
    private final BillRepository billRepository;

    public UserController(DoctorService doctorService, UserService userService, PatientService patientService, AppointmentService appointmentService, BedService bedService, AdmissionService admissionService, DashboardService dashboardService, BillService billService, BillRepository billRepository) {
        this.doctorService = doctorService;
        this.userService = userService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.bedService = bedService;
        this.admissionService = admissionService;
        this.dashboardService = dashboardService;
        this.billService = billService;
        this.billRepository = billRepository;
    }

    @GetMapping("/get/all/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get/all/app/list")
    public ResponseEntity<?> getAllAppointmentByDoctorId() {
        List<AppointmentResponseDto> appointmentList = appointmentService.getAllAppointment();
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }





    //Doctor Registration by Admin
    @PostMapping("/doctor/register")
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorRegisterRequestDto request) {
        Doctor doctor = doctorService.registerNewDoctor(request);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @GetMapping("/get/all/doctors")
    public ResponseEntity<?> getAllDoctors() {
        List<DoctorDto> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PutMapping("/edit/doctor")
    public ResponseEntity<?> editDoctor(@RequestBody DoctorDto editDoctorDto) {
        Doctor doctor = doctorService.editDoctor(editDoctorDto);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @DeleteMapping("/delete/doctor")
    public ResponseEntity<?> deleteDoctor(@RequestParam Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Doctor Registration by Admin
    @PostMapping("/patient/register")
    public ResponseEntity<?> registerPatient(@RequestBody PatientRegisterRequestDto id) {
        Patient patient = patientService.registerPatient(id);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @GetMapping("/get/all/patient")
    public ResponseEntity<?> getAllPatient() {
        List<PatientResponseDto> patientList = patientService.getAllPatients();
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @PutMapping("/edit/patient")
    public ResponseEntity<?> editPatient(@RequestBody EditPatientDto editPatientDto) {
        Patient patient = patientService.editPatient(editPatientDto);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @DeleteMapping("/delete/patient")
    public ResponseEntity<?> deletePatient(@RequestParam Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping("/add/bed")
    public ResponseEntity<?> addBed(@RequestBody AddBedDto id) {
        Bed bed = bedService.addBed(id);
        return new ResponseEntity<>(bed, HttpStatus.CREATED);
    }

    @GetMapping("/get/all/beds")
    public ResponseEntity<?> getAllBeds() {
        List<Bed> beds = bedService.getAllBeds();
        return new ResponseEntity<>(beds, HttpStatus.OK);
    }

    @PutMapping("/edit/bed")
    public ResponseEntity<?> editBed(@RequestBody EditBedDto editBedDto) {
        Bed beds = bedService.editBed(editBedDto);
        return new ResponseEntity<>(beds, HttpStatus.OK);
    }

    @DeleteMapping("/delete/bed")
    public ResponseEntity<?> deleteBed(@RequestParam Long id) {
        bedService.deleteBed(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admission")
    public ResponseEntity<?> admitPatient(@RequestBody AddAdmissionDto addAdmissionDto) {
        Admission admission = admissionService.admitPatient(addAdmissionDto);
        return new ResponseEntity<>(admission, HttpStatus.OK);
    }

    @PutMapping("/advance")
    public ResponseEntity<Admission> addAdvance(@RequestParam Long id, @RequestParam double amount) {
        return ResponseEntity.ok(admissionService.addAdvance(id, amount));
    }

    @PutMapping("/discharge")
    public ResponseEntity<Admission> discharge(@RequestParam Long id) {
        return ResponseEntity.ok(admissionService.dischargePatient(id));
    }

    @GetMapping("/dashboard/stats")
    public ResponseEntity<DashboardStatsDto> getStats() {
        DashboardStatsDto stats = dashboardService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/admitted-patients")
    public ResponseEntity<List<AdmittedPatientDto>> getAllAdmittedPatients() {
        List<AdmittedPatientDto> patients = admissionService.getAllAdmittedPatients();
        return ResponseEntity.ok(patients);
    }

    @PostMapping("/bill/create")
    public ResponseEntity<Bill> createBill(@RequestParam Long admissionId,
                                           @RequestParam Double doctorFee,
                                           @RequestParam Double medicineCost) {
        Bill createdBill = billService.createBill(admissionId, doctorFee, medicineCost);
        return ResponseEntity.ok(createdBill);
    }

    // 2. Auto generate bill based on logic (e.g. duration * daily rate)
    @PostMapping("/bill/generate/{admissionId}")
    public ResponseEntity<Bill> generateBill(@PathVariable Long admissionId) {
        Bill bill = billService.generateBill(admissionId);
        return ResponseEntity.ok(bill);
    }

    // 3. Get bill by admission ID
    @GetMapping("/bill/get")
    public ResponseEntity<Bill> getBill(@RequestParam Long admissionId) {
        Bill bill = billService.getBillByAdmissionId(admissionId);
        return ResponseEntity.ok(bill);
    }

    // 4. Download PDF Receipt
    @GetMapping("/download/receipt")
    public ResponseEntity<InputStreamResource> downloadReceipt(@RequestParam Long id) {
        byte[] pdfBytes = billService.generateReceiptPdf(id);
        ByteArrayInputStream bis = new ByteArrayInputStream(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=receipt_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping("/discharged")
    public List<PatientDto> getDischargedPatients() {
        return patientService.getDischargedPatients();
    }


}

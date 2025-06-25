package com.jmjbrothers.doctorsappointmentsystem.service;

import com.jmjbrothers.doctorsappointmentsystem.dto.AddBedDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.EditBedDto;
import com.jmjbrothers.doctorsappointmentsystem.model.Bed;
import com.jmjbrothers.doctorsappointmentsystem.repository.BedRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {

    private final BedRepository bedRepository;

    public BedService(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    @Transactional
    public Bed addBed(AddBedDto id) {

        Bed bed = new Bed();
        bed.setBedNumber(id.getBedNumber());
        bed.setWard(id.getWard());
        bed.setFeePerDay(id.getFeePerDay());
        bed.setOccupied(false);

        return bedRepository.save(bed);

    }

    @Transactional
    public List<Bed> getAllBeds() {
        return bedRepository.findAll();
    }

    @Transactional
    public Bed editBed(EditBedDto editBedDto) {
        Bed bed = bedRepository.findById(editBedDto.getId()).orElseThrow(
                () -> new RuntimeException("Bed with id " + editBedDto.getId() + " not found")
        );

        bed.setWard(editBedDto.getWard());
        bed.setBedNumber(editBedDto.getBedNumber());
        bed.setOccupied(editBedDto.isOccupied());
        System.out.println("EditBedDto received: " + editBedDto);
        bed.setFeePerDay(editBedDto.getFeePerDay()); // ✅ Set this
       Bed editBed = bedRepository.save(bed);
        System.out.println("EditBedDto received: " + editBed.isOccupied());
        return editBed;
    }

    @Transactional
    public void deleteBed(Long id) {
        bedRepository.deleteById(id);
    }
}

package com.jmjbrothers.dreamtravelsolution.service;

import com.jmjbrothers.dreamtravelsolution.model.BookingPackage;
import com.jmjbrothers.dreamtravelsolution.model.TravelPackage;
import com.jmjbrothers.dreamtravelsolution.repository.TravelPackageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelPackageService {

    private final TravelPackageRepository travelPackageRepository;


    public TravelPackageService(TravelPackageRepository travelPackageRepository) {
        this.travelPackageRepository = travelPackageRepository;
    }


    //This method owned by admin
    @Transactional
    public TravelPackage createTravelPackage(TravelPackage travelPackage) {

        TravelPackage createPackage = travelPackageRepository.save(travelPackage);

        return createPackage;
    }

    //This method owned by admin
    @Transactional
    public TravelPackage updateTravelPackage(TravelPackage travelPackage) {
        TravelPackage travelPackage1 = travelPackageRepository.findById(travelPackage.getId()).orElse(null);

        if (travelPackage1 != null){

            travelPackage1.setPackageName(travelPackage.getPackageName());
            travelPackage1.setPackageDescription(travelPackage.getPackageDescription());
            travelPackage1.setPackagePrice(travelPackage.getPackagePrice());
            travelPackage1.setPackageImage(travelPackage.getPackageImage());

            return travelPackageRepository.save(travelPackage1);
        }

        return null;
    }

    //this methods owned by both customer and admin
    @Transactional
    public List<TravelPackage> getAllTravelPackage() {

        List<TravelPackage> allPackage = travelPackageRepository.findAll();

        return allPackage;
    }

    //this methods owned by both customer and admin
    @Transactional
    public String deleteTravelPackage(Long id) {

        travelPackageRepository.deleteById(id);
        if (travelPackageRepository.existsById(id)){

            return "Package deletion failed!";
        }

        return "Package delete successfully";
    }


}

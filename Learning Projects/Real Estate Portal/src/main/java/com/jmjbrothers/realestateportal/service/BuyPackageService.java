package com.jmjbrothers.realestateportal.service;


import com.jmjbrothers.realestateportal.dto.BuyPackageDto;
import com.jmjbrothers.realestateportal.dto.PurchaseHistoryDto;
import com.jmjbrothers.realestateportal.model.BuyPackage;
import com.jmjbrothers.realestateportal.model.PostPackage;
import com.jmjbrothers.realestateportal.model.User;
import com.jmjbrothers.realestateportal.repository.BuyPackageRepository;
import com.jmjbrothers.realestateportal.repository.PostPackageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BuyPackageService {

    private final BuyPackageRepository buyPackageRepository;
    private final PostPackageRepository postPackageRepository;
    private final UserInfoDetailsService userInfoDetailsService;



    @Transactional
    public BuyPackage buyPackage(BuyPackageDto buyPackageDto) {
        Long packageId = buyPackageDto.getPostPackageId();
        Long userId = buyPackageDto.getUserId();
        PostPackage postPackage = postPackageRepository.findById(packageId).orElse(null);
        User user = userInfoDetailsService.userFindById(userId);

        if (postPackage == null && user == null)
            throw new RuntimeException("User or package not found!");

        BuyPackage buyPackage = new BuyPackage();
        buyPackage.setPostPackage(postPackage);

        //get the user credit balance from user
        Integer postRemain =user.getPostBalance();
        //get the package credit amount from credit package
        Integer postAmount = postPackage.getNumberOfPost();

        //set the user credit balance after the package purchase and update credit balance
        user.setPostBalance(postRemain+postAmount);

        buyPackage.setUser(user);


        return buyPackageRepository.save(buyPackage);

    }

    @Transactional
    public List<PurchaseHistoryDto> allPropertyUnlockById(Long id) {
        List<BuyPackage> myPurchaseHistory = buyPackageRepository.findAllByUserId(id);

        List<PurchaseHistoryDto> historyDtos = myPurchaseHistory.stream().map(this::responseHistoryDto).collect(Collectors.toList());
        return historyDtos;
    }

    @Transactional
    private PurchaseHistoryDto responseHistoryDto(BuyPackage buyPackage) {

        PurchaseHistoryDto dto = new PurchaseHistoryDto();
        dto.setPackageName(buyPackage.getPostPackage().getName());
        dto.setPostNumber(buyPackage.getPostPackage().getNumberOfPost());
        dto.setPackagePrice(buyPackage.getPostPackage().getPackagePrice());
        dto.setDatePurchased(buyPackage.getDatePurchased());

        return dto;
    }


    @Transactional
    public List<PurchaseHistoryDto> allPropertyUnlock() {
        List<BuyPackage> myPurchaseHistory = buyPackageRepository.findAll();

        List<PurchaseHistoryDto> historyDtos = myPurchaseHistory.stream().map(this::responseHistoryDto).collect(Collectors.toList());
        return historyDtos;

    }
}

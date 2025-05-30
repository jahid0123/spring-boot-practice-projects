package com.jmjbrothers.spring.securtiy.authentication.service;

import com.jmjbrothers.spring.securtiy.authentication.dto.GetPostedProperty;
import com.jmjbrothers.spring.securtiy.authentication.dto.PropertyPostDto;
import com.jmjbrothers.spring.securtiy.authentication.model.Property;
import com.jmjbrothers.spring.securtiy.authentication.model.PropertyPost;
import com.jmjbrothers.spring.securtiy.authentication.model.User;
import com.jmjbrothers.spring.securtiy.authentication.repository.PropertyPostRepository;
import com.jmjbrothers.spring.securtiy.authentication.repository.PropertyRepository;
import com.jmjbrothers.spring.securtiy.authentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyPostService {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyPostRepository propertyPostRepository;

    @Value("${file.upload-dir:upload/images}")
    private String uploadDir;

    @Transactional
    public PropertyPost postProperty(PropertyPostDto propertyPostDto, MultipartFile[] images) throws IOException {
        User user = userRepository.findById(propertyPostDto.getUserID()).orElseThrow(
                ()-> new RuntimeException("User not found"));
        System.out.println(user.toString());

        if (user.getBalanceCredits()<10){
            System.out.println("No enough credit");
            throw new RuntimeException("Insufficient credits");
        }


        List<String> imagePaths = new ArrayList<>();

        // Save each image
        if (images != null && images.length > 0) {
            for (MultipartFile file : images) {
                String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

                Path filePath = Paths.get(uploadDir + File.separator + filename);

                // Ensure upload directory exists
                Files.createDirectories(filePath.getParent());
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                imagePaths.add(filename);
            }
        }


        //Create new property
        Property property = new Property();
        property.setUser(user);
        property.setCategory(propertyPostDto.getCategory());
        property.setTitle(propertyPostDto.getTitle());
        property.setDescription(propertyPostDto.getDescription());
        property.setAddress(propertyPostDto.getAddress());
        property.setRentAmount(propertyPostDto.getRentAmount());
        property.setDivision(propertyPostDto.getDivision());
        property.setDistrict(propertyPostDto.getDistrict());
        property.setThana(propertyPostDto.getThana());
        property.setSection(propertyPostDto.getSection());
        property.setRoadNumber(propertyPostDto.getRoadNumber());
        property.setHouseNumber(propertyPostDto.getHouseNumber());

        // Save image filenames to entity
        property.setImagePaths(imagePaths); // assumes a List<String> field in your entity

        //deduct credit
        user.setBalanceCredits(user.getBalanceCredits()-10);
        userRepository.save(user);

        //Save property
        propertyRepository.save(property);

        //Create new property post
        PropertyPost propertyPost = new PropertyPost();
        propertyPost.setUser(user);
        propertyPost.setProperty(property);
        propertyPost.setContactPerson(propertyPostDto.getContactPerson());
        propertyPost.setContactNumber(propertyPostDto.getContactNumber());
        propertyPost.setAvailableFrom(propertyPostDto.getAvailableFrom());

        return propertyPostRepository.save(propertyPost);
    }


    @Transactional
    public List<GetPostedProperty> getAllPostedProperty() {

        List<PropertyPost> allPostedProperty = propertyPostRepository.findAll();

        //this is the simple way but Difficult to understand
        List<GetPostedProperty> getPostedPropertyList
                = allPostedProperty.stream().map(this::getPostedPropertyMapped).collect(Collectors.toList());

        return getPostedPropertyList;


/*        //the easy way to mapped list PropertyPost List to GetPostedProperty List
        List<GetPostedProperty> getPostedPropertyList = new ArrayList<>();
        for (PropertyPost property : allPostedProperty) {
            GetPostedProperty dto = getPostedPropertyMapped(property);  // convert it
            getPostedPropertyList.add(dto);                         // add to new list
        }

        return getPostedPropertyList;*/
    }


    //Mapped method PropertyPost class to GetPostedProperty
    private GetPostedProperty getPostedPropertyMapped(PropertyPost property){
        GetPostedProperty getPostedProperty = new GetPostedProperty();

        getPostedProperty.setId(property.getId());
        getPostedProperty.setCategory(property.getProperty().getCategory());
        getPostedProperty.setTitle(property.getProperty().getTitle());
        getPostedProperty.setDescription(property.getProperty().getDescription());
        getPostedProperty.setIsAvailable(property.getProperty().getIsAvailable());
        getPostedProperty.setRentAmount(property.getProperty().getRentAmount());
        getPostedProperty.setDatePosted(property.getDatePosted());
        getPostedProperty.setAvailableFrom(property.getAvailableFrom());
        getPostedProperty.setDivision(property.getProperty().getDivision());
        getPostedProperty.setDistrict(property.getProperty().getDistrict());
        getPostedProperty.setThana(property.getProperty().getThana());
        getPostedProperty.setSection(property.getProperty().getSection());

        return getPostedProperty;
    }
}

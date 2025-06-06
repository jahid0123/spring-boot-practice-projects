package com.jmjbrothers.realestateportal.service;


import com.jmjbrothers.realestateportal.dto.*;
import com.jmjbrothers.realestateportal.model.Property;
import com.jmjbrothers.realestateportal.model.PropertyPost;
import com.jmjbrothers.realestateportal.model.User;
import com.jmjbrothers.realestateportal.repository.PropertyPostRepository;
import com.jmjbrothers.realestateportal.repository.PropertyRepository;
import com.jmjbrothers.realestateportal.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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


    @Transactional
    public PropertyPost postProperty(PropertyPostDto propertyPostDto, MultipartFile[] images) throws IOException {
        User user = userRepository.findById(propertyPostDto.getUserID()).orElseThrow(
                () -> new RuntimeException("User not found"));
        System.out.println(user.toString());

        if (user.getPostBalance() < 1) {
            System.out.println("No enough remaining post");
            throw new RuntimeException("Insufficient remaining post");
        }

        //Create new property
        Property property = new Property();
        property.setUser(user);
        property.setType(propertyPostDto.getType());
        property.setTitle(propertyPostDto.getTitle());
        property.setDescription(propertyPostDto.getDescription());
        property.setAddress(propertyPostDto.getAddress());
        property.setPropertyPrice(propertyPostDto.getPrice());

        List<String> imagePaths = new ArrayList<>();

        if (images != null && images.length > 0) {
            Path uploadDir = Paths.get("uploads");

            // Ensure upload directory exists only once
            Files.createDirectories(uploadDir);

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = uploadDir.resolve(filename);

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add(filename);
            }
        }

        property.setImageListPaths(imagePaths);

        propertyRepository.save(property);

        //deduct credit
        user.setPostBalance(user.getPostBalance() - 1);
        userRepository.save(user);

        //Create new property post
        PropertyPost propertyPost = new PropertyPost();
        propertyPost.setUser(user);
        propertyPost.setProperty(property);

        return propertyPostRepository.save(propertyPost);
    }


    @Transactional
    public List<GetPostedProperty> getAllPostedProperty() {

        List<PropertyPost> allPostedProperty = propertyPostRepository.findAll();

        //this is the simple way but Difficult to understand
        return allPostedProperty.stream().map(this::getPostedPropertyMapped).collect(Collectors.toList());


/*        //the easy way to mapped list PropertyPost List to GetPostedProperty List
        List<GetPostedProperty> getPostedPropertyList = new ArrayList<>();
        for (PropertyPost property : allPostedProperty) {
            GetPostedProperty dto = getPostedPropertyMapped(property);  // convert it
            getPostedPropertyList.add(dto);                         // add to new list
        }

        return getPostedPropertyList;*/
    }


    //Mapped method PropertyPost class to GetPostedProperty
    private GetPostedProperty getPostedPropertyMapped(PropertyPost propertyPost) {
        GetPostedProperty getPostedProperty = new GetPostedProperty();

        getPostedProperty.setId(propertyPost.getId());
        getPostedProperty.setType(propertyPost.getProperty().getType());
        getPostedProperty.setTitle(propertyPost.getProperty().getTitle());
        getPostedProperty.setDescription(propertyPost.getProperty().getDescription());
        getPostedProperty.setIsAvailable(propertyPost.getProperty().getIsAvailable());
        getPostedProperty.setPropertyPrice(propertyPost.getProperty().getPropertyPrice());
        getPostedProperty.setDatePosted(propertyPost.getDatePosted());
        getPostedProperty.setAddress(propertyPost.getProperty().getAddress());
        getPostedProperty.setImageUrls(propertyPost.getProperty().getImageListPaths());

        return getPostedProperty;
    }

    @Transactional
    public List<PropertyPost> allPropertyPostedByMe(Long id) {

        return propertyPostRepository.findAllByUserId(id);
    }


    @Transactional
    public String deleteMyPostedProperties(Long id) {
        PropertyPost post = propertyPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // First delete the post that holds the foreign key
        propertyPostRepository.deleteById(id);

        // Then delete the property if needed (and if not used elsewhere)
        propertyRepository.deleteById(post.getProperty().getId());

        return "Post deleted successfully!";
    }


    public PropertyPost updateMyPostedProperties(UpdateMyPostedPropertyDto propertyDto) {
        PropertyPost propertyPost = propertyPostRepository.findById(propertyDto.getPostId()).orElseThrow(
                () -> new RuntimeException("Property not found with id " + propertyDto.getPostId()));

        Property findProperty = propertyRepository.findById(propertyPost.getProperty().getId()).orElse(null);
        findProperty.setType(propertyDto.getType());
        findProperty.setTitle(propertyDto.getTitle());
        findProperty.setDescription(propertyDto.getDescription());
        findProperty.setPropertyPrice(propertyDto.getPropertyPrice());
        findProperty.setAddress(propertyDto.getAddress());

        propertyRepository.save(findProperty);

        return propertyPostRepository.save(propertyPost);
    }


    @Transactional
    public List<PostedPropertyResponseDto> allPostedProperty() {
        List<PropertyPost> propertyPosts = propertyPostRepository.findAll();
        return propertyPosts.stream().map(this::postedPropertyResponseDto).collect(Collectors.toList());
    }

    private PostedPropertyResponseDto postedPropertyResponseDto(PropertyPost propertyPost) {
        PostedPropertyResponseDto myPost = new PostedPropertyResponseDto();
        myPost.setPostId(propertyPost.getId());
        myPost.setUserId(propertyPost.getUser().getId());
        myPost.setType(propertyPost.getProperty().getType());
        myPost.setTitle(propertyPost.getProperty().getTitle());
        myPost.setDescription(propertyPost.getProperty().getDescription());
        myPost.setIsAvailable(propertyPost.getProperty().getIsAvailable());
        myPost.setPropertyPrice(propertyPost.getProperty().getPropertyPrice());
        myPost.setDatePosted(propertyPost.getProperty().getDatePosted());
        myPost.setAddress(propertyPost.getProperty().getAddress());

        return myPost;
    }

    @Transactional
    public PropertyPost updatePostedProperty(PropertyPostUpdateDto post) {

        PropertyPost propertyPost = propertyPostRepository.findById(post.getPostId()).orElse(null);
        Property property = propertyPost.getProperty();
        property.setIsAvailable(post.getIsAvailable());
        propertyPost.setProperty(property);
        return propertyPostRepository.save(propertyPost);

    }
}


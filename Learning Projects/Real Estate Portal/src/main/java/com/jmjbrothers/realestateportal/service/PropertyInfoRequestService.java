package com.jmjbrothers.realestateportal.service;


import com.jmjbrothers.realestateportal.dto.PropertyInfoRequestDto;
import com.jmjbrothers.realestateportal.model.PropertyInfoRequest;
import com.jmjbrothers.realestateportal.model.PropertyPost;
import com.jmjbrothers.realestateportal.model.User;
import com.jmjbrothers.realestateportal.repository.PropertyInfoRequestRepository;
import com.jmjbrothers.realestateportal.repository.PropertyPostRepository;
import com.jmjbrothers.realestateportal.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyInfoRequestService {

    private final PropertyInfoRequestRepository propertyInfoRequestRepository;
    private final UserRepository userRepository;
    private final PropertyPostRepository propertyPostRepository;


    @Transactional
    public PropertyInfoRequest propertyInfoRequest(PropertyInfoRequestDto propertyInfoRequestDto) {
        Long userId = propertyInfoRequestDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        System.out.println(user.toString());

        Long propertyPostId = propertyInfoRequestDto.getPropertyPostId();
        PropertyPost propertyPost = propertyPostRepository.findById(propertyPostId).orElseThrow(
                () -> new RuntimeException("PropertyPost not found"));
        System.out.println(propertyPost.toString());


        PropertyInfoRequest propertyInfoRequest = new PropertyInfoRequest();
        propertyInfoRequest.setUser(user);
        propertyInfoRequest.setPropertyPost(propertyPost);

        return propertyInfoRequestRepository.save(propertyInfoRequest);
    }

    @Transactional
    public List<PropertyInfoRequest> allPropertyInfoRequestById(Long id) {
        List<PropertyInfoRequest> allPropertyUnlock = propertyInfoRequestRepository.findAllByUserId(id);

        return allPropertyUnlock;
    }


}



package com.jmjbrothers.realestateportal.service;

import com.jmjbrothers.realestateportal.dto.PropertyDto;
import com.jmjbrothers.realestateportal.model.Property;
import com.jmjbrothers.realestateportal.model.User;
import com.jmjbrothers.realestateportal.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
    private final UserInfoDetailsService userDetailsService;

    public PropertyService(PropertyRepository propertyRepository, UserInfoDetailsService userDetailsService){
        this.propertyRepository = propertyRepository;
        this.userDetailsService = userDetailsService;
    }


    public Property createProperty(PropertyDto propertyDto) {

        Long userId = propertyDto.getUserId();
        User user = userDetailsService.userFindById(userId);

        if (user == null) {

            throw new IllegalArgumentException("User not found");
        }

        Property property = new Property();
        property.setUser(user);
        property.setType(propertyDto.getType());
        property.setAddress(propertyDto.getAddress());
        property.setDescription(propertyDto.getDescription());
        property.setTitle(propertyDto.getTitle());
        property.setPropertyPrice(propertyDto.getPropertyPrice());
        return propertyRepository.save(property);

    }
}

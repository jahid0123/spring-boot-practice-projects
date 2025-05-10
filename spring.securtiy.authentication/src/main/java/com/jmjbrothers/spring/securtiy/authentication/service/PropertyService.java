package com.jmjbrothers.spring.securtiy.authentication.service;

<<<<<<< HEAD
import com.jmjbrothers.spring.securtiy.authentication.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;


=======
import com.jmjbrothers.spring.securtiy.authentication.dto.PropertyDto;
import com.jmjbrothers.spring.securtiy.authentication.model.Property;
import com.jmjbrothers.spring.securtiy.authentication.model.User;
import com.jmjbrothers.spring.securtiy.authentication.repository.PropertyRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
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
        property.setCategory(propertyDto.getCategory());
        property.setAddress(propertyDto.getAddress());
        property.setDescription(propertyDto.getDescription());
        property.setTitle(propertyDto.getTitle());
        property.setRentAmount(propertyDto.getRentAmount());
        return propertyRepository.save(property);

    }
>>>>>>> 681f6ebccd4978394829766acdd7b7ec0de65f08
}

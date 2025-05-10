package com.jmjbrothers.spring.securtiy.authentication.dto;

<<<<<<< HEAD
public class PropertyDto {
=======
import com.jmjbrothers.spring.securtiy.authentication.constants.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertyDto {

    private long userId;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    private String address;
    private BigDecimal rentAmount;
>>>>>>> 681f6ebccd4978394829766acdd7b7ec0de65f08
}

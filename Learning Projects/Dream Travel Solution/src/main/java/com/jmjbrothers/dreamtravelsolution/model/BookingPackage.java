package com.jmjbrothers.dreamtravelsolution.model;


import com.jmjbrothers.dreamtravelsolution.constants.Status;
import com.jmjbrothers.dreamtravelsolution.constants.Transportation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "hemel_booking_package")
public class BookingPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // Mapped to User's `id` by default
    private User user;

    @ManyToOne
    @JoinColumn(name = "package_id") // Mapped to TravelPackage's `id` by default
    private TravelPackage travelPackage;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private Status status = Status.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportation", nullable = false)
    private Transportation transport;

    @Column(name = "booking_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}

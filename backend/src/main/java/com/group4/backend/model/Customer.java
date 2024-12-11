package com.group4.backend.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    @NotBlank(message = "Customer name is mandatory")
    private String customerName;

    @Column(name = "email")
    @Email(message = "Email is mandatory")
    private String email;

    @Column(name = "phone_number")
    @Size(min = 8, max = 8, message = "Contact number must be exactly 8 characters")
    private String phoneNumber;

    @Column(name = "home_address")
    @NotBlank(message = "Home address is mandatory")
    private String homeAddress;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "membership_type")
    @Enumerated(EnumType.STRING) // Persist the enum as its name (e.g., "GOLD", "SILVER")
    private Membership membershipType;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}

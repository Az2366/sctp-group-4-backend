package com.group4.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @Column(name = "email", unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{8}", message = "Contact number must be exactly 8 digits")
    private String phoneNumber;

    @Column(name = "home_address")
    @NotBlank(message = "Home address is mandatory")
    private String homeAddress;

    @Column(name = "is_active")
    @JsonProperty("isActive") // Explicitly name the JSON property, defaults to .active if this is not set.
    private boolean isActive;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "membership_type")
    @NotNull(message = "Membership type is mandatory")
    @Enumerated(EnumType.STRING)
    private Membership membershipType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;
}

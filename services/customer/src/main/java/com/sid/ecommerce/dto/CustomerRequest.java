package com.sid.ecommerce.dto;

import com.sid.ecommerce.documents.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "customer firstname is required")
        String firstName,

        @NotNull(message = "customer lastname is required")
        String lastName,
        @NotNull(message = "customer email is required")
        @Email(message = "customer email is not valid")
        String email,

       Address address
) {
}

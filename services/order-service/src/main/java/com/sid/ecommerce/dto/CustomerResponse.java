package com.sid.ecommerce.dto;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}

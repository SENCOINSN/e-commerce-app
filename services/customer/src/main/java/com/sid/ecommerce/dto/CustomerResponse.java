package com.sid.ecommerce.dto;

import com.sid.ecommerce.documents.Address;


public record CustomerResponse(
        String id,

        String firstName,


        String lastName,

        String email,

        Address address
) {
}

package com.sid.ecommerce.advices;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}

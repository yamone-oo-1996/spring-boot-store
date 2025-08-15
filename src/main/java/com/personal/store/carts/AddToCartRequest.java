package com.personal.store.carts;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddToCartRequest {
    @NotNull(message = "Product Id is required.")
    private Long productId;
}

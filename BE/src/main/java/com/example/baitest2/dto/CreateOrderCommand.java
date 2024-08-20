package com.example.baitest2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateOrderCommand(
        @JsonProperty("total_quantity") Integer totalQuantity,
        @JsonProperty("total_amount") Long totalAmount,
        Boolean status
) {
}

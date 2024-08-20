package com.example.baitest2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderResponse {

    private Long id;

    @JsonProperty("total_quantity")
    private Integer totalQuantity;

    @JsonProperty("total_amount")
    private Long totalAmount;

    private Boolean bit;


}

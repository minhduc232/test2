package com.example.baitest2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record ErrorResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss") Date timestamp,
        Integer status,
        String message
) {}

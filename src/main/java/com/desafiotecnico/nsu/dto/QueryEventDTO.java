package com.desafiotecnico.nsu.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueryEventDTO {
    private String queryType;
    private String queryParameter;
    private LocalDateTime timestamp;

    public QueryEventDTO(String queryType, String queryParameter) {
        this.queryType = queryType;
        this.queryParameter = queryParameter;
        this.timestamp = LocalDateTime.now();
    }
}
package org.example.position.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PositionDto {
    private Long id;
    private String grade;
}
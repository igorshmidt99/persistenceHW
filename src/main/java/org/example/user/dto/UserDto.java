package org.example.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.position.dto.PositionDto;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private PositionDto position;
}
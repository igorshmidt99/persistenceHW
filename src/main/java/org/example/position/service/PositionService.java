package org.example.position.service;

import org.example.position.dto.PositionDto;
import org.example.position.dto.PositionRequestDto;

public interface PositionService {
    PositionDto getById(Long id);
    PositionDto getByGrade(PositionRequestDto dto);
    PositionDto getByGrade(String grade);
    PositionDto createPosition(PositionRequestDto dto);
    PositionDto update(Long id, PositionRequestDto dto);
    void deleteByGrade(PositionRequestDto dto);
    void deleteById(Long id);
    long getPositionIdByGrade(String grade);
}
package org.example.position;

public interface PositionService {
    PositionDto getById(Long id);
    PositionDto getByGrade(PositionRequestDto dto);
    PositionDto createPosition(PositionRequestDto dto);
    PositionDto update(Long id, PositionRequestDto dto);
    void deleteByGrade(PositionRequestDto dto);
    void deleteById(Long id);
}
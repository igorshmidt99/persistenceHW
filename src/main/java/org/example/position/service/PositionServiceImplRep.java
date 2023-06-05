package org.example.position.service;

import lombok.RequiredArgsConstructor;
import org.example.position.PositionMapper;
import org.example.position.dto.PositionDto;
import org.example.position.dto.PositionRequestDto;
import org.example.position.model.Position;
import org.example.position.repository.PositionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionServiceImplRep implements PositionService {

    private final PositionRepository positionRepository;

    @Override
    public PositionDto getById(Long id) {
        Position position = positionRepository.getById(id);
        return PositionMapper.toDto(position);
    }

    @Override
    public PositionDto getByGrade(PositionRequestDto dto) {
        String grade = dto.getGrade();
        Position position = positionRepository.getByGrade(grade);
        return PositionMapper.toDto(position);
    }

    @Override
    public PositionDto getByGrade(String grade) {
        Position position = positionRepository.getByGrade(grade);
        return PositionMapper.toDto(position);
    }

    @Override
    public PositionDto createPosition(PositionRequestDto dto) {
        String grade = dto.getGrade();
        Position position = positionRepository.createPosition(grade);
        return PositionMapper.toDto(position);
    }

    @Override
    public PositionDto update(Long id, PositionRequestDto dto) {
        String grade = dto.getGrade();
        Position position = positionRepository.update(id, grade);
        return PositionMapper.toDto(position);
    }

    @Override
    public void deleteByGrade(PositionRequestDto dto) {
        String grade = dto.getGrade();
        positionRepository.deleteByGrade(grade);
    }

    @Override
    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }

    @Override
    public long getPositionIdByGrade(String grade) {
        return positionRepository.getPositionId(grade);
    }
}

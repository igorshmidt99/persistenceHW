package org.example.position.service;

import lombok.RequiredArgsConstructor;
import org.example.position.PositionMapper;
import org.example.position.dao.PositionDao;
import org.example.position.dto.PositionDto;
import org.example.position.dto.PositionRequestDto;
import org.example.position.model.Position;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionDao positionDao;

    @Override
    public PositionDto getById(Long id) {
        Position position = positionDao.getById(id);
        return PositionMapper.toDto(position);
    }

    @Override
    public PositionDto getByGrade(PositionRequestDto dto) {
        String grade = dto.getGrade();
        Position position = positionDao.getByGrade(grade);
        return PositionMapper.toDto(position);
    }

    @Override
    public PositionDto getByGrade(String grade) {
        Position position = positionDao.getByGrade(grade);
        return PositionMapper.toDto(position);
    }

    @Override
    public PositionDto createPosition(PositionRequestDto dto) {
        String grade = dto.getGrade();
        Position position = positionDao.createPosition(grade);
        return PositionMapper.toDto(position);
    }

    @Override
    public PositionDto update(Long id, PositionRequestDto dto) {
        String grade = dto.getGrade();
        Position position = positionDao.update(id, grade);
        return PositionMapper.toDto(position);
    }

    @Override
    public void deleteByGrade(PositionRequestDto dto) {
        String grade = dto.getGrade();
        positionDao.deleteByGrade(grade);
    }

    @Override
    public void deleteById(Long id) {
        positionDao.deleteById(id);
    }

    @Override
    public long getPositionIdByGrade(String grade) {
        return positionDao.getPositionId(grade);
    }
}
package org.example.position.repository;

import org.example.position.model.Position;

public interface PositionRepository {
    Position getById(Long id);
    Position getByGrade(String grade);
    Position createPosition(String grade);
    Position update(Long id, String grade);
    void deleteByGrade(String grade);
    void deleteById(Long id);
    long getPositionId(String grade);
}

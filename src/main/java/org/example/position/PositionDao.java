package org.example.position;

public interface PositionDao {
    Position getById(Long id);
    Position getByGrade(String grade);
    Position createPosition(String grade);
    Position update(Long id, String grade);
    void deleteByGrade(String grade);
    void deleteById(Long id);
}
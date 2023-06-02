package org.example.position;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    public PositionDto createPosition(@RequestBody PositionRequestDto dto) {
        return positionService.createPosition(dto);
    }

    @GetMapping
    public PositionDto getByGrade(@RequestBody PositionRequestDto dto) {
        return positionService.getByGrade(dto);
    }

    @GetMapping("/{id}")
    public PositionDto getById(@PathVariable Long id) {
        return positionService.getById(id);
    }

    @PatchMapping("/{id}")
    public PositionDto update(@PathVariable Long id, @RequestBody PositionRequestDto dto) {
        return positionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        positionService.deleteById(id);
    }

    @DeleteMapping
    public void deleteByGrade(@RequestBody PositionRequestDto dto) {
        positionService.deleteByGrade(dto);
    }

}
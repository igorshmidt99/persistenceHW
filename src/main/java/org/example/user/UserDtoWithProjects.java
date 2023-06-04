package org.example.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.position.PositionDto;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoWithProjects {
    private Long id;
    private String name;
    private PositionDto position;
    private Set<String> projects;
}

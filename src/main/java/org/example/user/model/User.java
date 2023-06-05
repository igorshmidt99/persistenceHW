package org.example.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.position.model.Position;
import org.example.project.model.Project;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 256, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "position")
    private Position position;

    @ManyToMany
    @JoinTable(name = "user_projects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new HashSet<>();
}
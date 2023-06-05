package org.example.project.repository;

import lombok.RequiredArgsConstructor;
import org.example.project.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Project addProject(String name) {
        try (Session session = sessionFactory.openSession()) {
            Project project = Project.builder().name(name).build();
            Transaction transaction = session.beginTransaction();
            session.persist(project);
            transaction.commit();
            return project;
        }
    }

    @Override
    public Project getProjectById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Project project = session.get(Project.class, id);
            transaction.commit();
            return project;
        }
    }

    @Override
    public Project update(String name, Long id) {
        try (Session session = sessionFactory.openSession()) {
            Project project = Project.builder().id(id).name(name).build();
            Transaction transaction = session.beginTransaction();
            project = session.merge(project);
            transaction.commit();
            return project;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Project project = Project.builder().id(id).build();
            Transaction transaction = session.beginTransaction();
            session.remove(project);
            transaction.commit();
        }
    }

    @Override
    public Set<Project> getUserProjectsById(Set<Long> projectIds) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Set<Project> userProjects = session
                    .createNativeQuery("SELECT name FROM projects WHERE id = ?", Project.class)
                    .getResultStream()
                    .collect(Collectors.toSet());
            transaction.commit();
            return userProjects;
        }

    }
}

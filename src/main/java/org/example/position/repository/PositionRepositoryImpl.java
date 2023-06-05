package org.example.position.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.example.position.model.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Position getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Position position = session.find(Position.class, id);
            transaction.commit();
            return position;
        }
    }

    @Override
    public Position getByGrade(String grade) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Position position = getByGradeWithQuery(grade, session);
            transaction.commit();
            return position;
        }
    }

    @Override
    public Position createPosition(String grade) {
        try (Session session = sessionFactory.openSession()) {
            Position position = Position.builder().grade(grade).build();
            Transaction transaction = session.beginTransaction();
            session.persist(position);
            transaction.commit();
            return position;
        }
    }

    @Override
    public Position update(Long id, String grade) {
        try (Session session = sessionFactory.openSession()) {
            Position position = Position.builder().id(id).grade(grade).build();
            Transaction transaction = session.beginTransaction();
            position = session.merge(position);
            transaction.commit();
            return position;
        }
    }

    @Override
    public void deleteByGrade(String grade) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaDelete<Position> criteriaDelete = getCriteriaDeleteByGrade(session, grade);
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Position position = Position.builder().id(id).build();
            Transaction transaction = session.beginTransaction();
            session.remove(position);
            transaction.commit();
        }
    }

    @Override
    public long getPositionId(String grade) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Long id = session
                    .createNamedQuery("SELECT id FROM positions WHERE grade = :grade", Long.class)
                    .getSingleResult();
            transaction.commit();
            return id;
        }
    }

    private Position getByGradeWithQuery(String grade, Session session) {
        Query<Position> query = session.createQuery("from Position where grade = :grade", Position.class);
        query.setParameter("grade", grade);
        return query.getSingleResult();
    }

    private CriteriaDelete<Position> getCriteriaDeleteByGrade(Session session, String grade) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<Position> cd = cb.createCriteriaDelete(Position.class);
        Root<Position> root = cd.from(Position.class);
        cd.where(cb.equal(root.get("grade"), grade));
        return cd;
    }

}
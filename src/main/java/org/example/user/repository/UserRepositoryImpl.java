package org.example.user.repository;

import lombok.RequiredArgsConstructor;
import org.example.position.model.Position;
import org.example.user.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public User getUserById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            transaction.commit();
            return user;
        }
    }

    @Override
    public User addUser(String name, Position position) {
        try (Session session = sessionFactory.openSession()) {
            User user = User.builder().name(name).position(position).build();
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        }
    }

    @Override
    public void deleteUser(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = User.builder().id(id).build();
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
    }

    @Override
    public User updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        }
    }

}

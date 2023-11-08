package ru.itmentor.spring.boot_security.demo.DAO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.*;
import java.util.List;


@Repository
public class DaoUserImp implements DaoUser {

    @PersistenceContext
    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public DaoUserImp(EntityManagerFactory entityManagerFactory, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override

    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Transactional
    @Override

    public void saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        entityManager.persist(user);

    }

    @Transactional
    @Override

    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Transactional
    @Override

    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override

    public void deleteUser(Long id) {
        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

}



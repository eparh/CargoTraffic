package service;

import models.User;
import play.Logger;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Anton Chernov on 12/29/2015.
 */
public class UserService {
    private static final Logger.ALogger LOGGER = Logger.of(UserService.class);

    public static List<User> getUserList() throws ServiceException {
        LOGGER.debug("Get user list");
        try {
            return JPA.withTransaction(() -> {
                        EntityManager em = JPA.em();
                        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
                        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
                        Root<User> from = criteriaQuery.from(User.class);

                        CriteriaQuery<User> select = criteriaQuery.select(from);
                        TypedQuery<User> q = em.createQuery(select);

                        return q.getResultList();

                    }
            );
        } catch (Throwable throwable) {
            LOGGER.error("Get user list = {}", throwable);
            throw new ServiceException(throwable.getMessage(), throwable);
        }
    }

    public static User find(long id) throws ServiceException {
        LOGGER.debug("Get user with id = {}", id);
        try {
            return JPA.withTransaction(() -> {
                        EntityManager em = JPA.em();
                        return em.find(User.class, id);
                    }
            );
        } catch (Throwable throwable) {
            LOGGER.error("Find user id = {}, error = {}", id, throwable);
            throw new ServiceException(throwable.getMessage(), throwable);
        }
    }


    public static User findByName(String name) throws ServiceException {
        LOGGER.debug("Get user with name = {}", name);
        try {
            return JPA.withTransaction(() -> {
                        EntityManager em = JPA.em();
                        CriteriaBuilder builder = em.getCriteriaBuilder();
                        CriteriaQuery<User> query = builder.createQuery(User.class);
                        Root<User> u = query.from(User.class);
                        query.select(u).where(builder.equal(u.get("username"), name));
                        TypedQuery<User> q = em.createQuery(query);
                        return q.getSingleResult();
                    }
            );
        } catch (Throwable throwable) {
            LOGGER.error("Find user name = {}, error = {}", name, throwable);
            throw new ServiceException(throwable.getMessage(), throwable);
        }
    }
}

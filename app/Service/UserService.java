package Service;

import models.User;
import org.apache.commons.collections4.CollectionUtils;
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

    public static List<User> getUserList() {
        EntityManager em = JPA.em();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);

        CriteriaQuery<User> select = criteriaQuery.select(from);
        TypedQuery<User> q = em.createQuery(select);

        return q.getResultList();
    }

    public static User findByName(String name) {
        EntityManager em = JPA.em();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> u = query.from(User.class);
        query.select(u).where(builder.equal(u.get("username"), name));

        TypedQuery<User> q = em.createQuery(query);
        List<User> userList = q.getResultList();
        if (CollectionUtils.isNotEmpty(userList)) return userList.get(0);
        return null;
    }
}

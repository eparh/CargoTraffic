package service;

import models.Company;
import play.Logger;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CompanyService {
    private static final Logger.ALogger LOGGER = Logger.of(CompanyService.class);

<<<<<<< HEAD
    public static List<Company> getCompanies(long id, int count, boolean ascOrder) throws ServiceException {
        LOGGER.debug("Get company list: {}, {}, {}", id, count, ascOrder);
        try {
            return JPA.withTransaction(() -> {
                        EntityManager em = JPA.em();
                        StringBuilder stringBuilder = new StringBuilder("SELECT c FROM Company c WHERE ");
                        if (ascOrder) {
                            stringBuilder.append("c.id >= ? AND c.deleted = ? ORDER BY c.id ASC");
                        } else {
                            stringBuilder.append("c.id < ? AND c.deleted = ? ORDER BY c.id DESC");
                        }
                        Query query = em.createQuery(stringBuilder.toString());
                        query.setParameter(1, id);
                        query.setParameter(2, false);
                        query.setMaxResults(count + 1);
                        List<Company> companies = query.getResultList();
                        if (!ascOrder)
                            Collections.reverse(companies);
                        return companies;
=======
    public static List<Company> getList() throws ServiceException {
        LOGGER.debug("Get company list");
        try {
            return JPA.withTransaction(() -> {
                        EntityManager em = JPA.em();
                        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
                        CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
                        Root<Company> from = criteriaQuery.from(Company.class);

                        CriteriaQuery<Company> select = criteriaQuery.select(from);
                        TypedQuery<Company> q = em.createQuery(select);
                        return q.getResultList();
>>>>>>> 3a2e9c4a9efb150bce27f904db4016cb21293275
                    }
            );
        } catch (Throwable throwable) {
            LOGGER.error("Get list error = {}", throwable);
            throw new ServiceException(throwable.getMessage(), throwable);
        }
    }
}

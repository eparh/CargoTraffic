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

/**
 * Created by Anton Chernov on 12/30/2015.
 */
public class CompanyService {
    private static final Logger.ALogger LOGGER = Logger.of(CompanyService.class);

    public static List<Company> getCompanies(long id, int count, boolean ascOrder) throws Throwable {
        LOGGER.debug("Get company list: {}, {}, {}", id, count, ascOrder);
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
                }
        );
    }
}

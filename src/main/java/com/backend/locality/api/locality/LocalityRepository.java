package com.backend.locality.api.locality;

import com.backend.locality.api.issues.IssuesModel;
import com.backend.locality.api.locality.interfaces.ILocality;
import com.backend.locality.api.locality.interfaces.IndexLocalityRequest;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@AllArgsConstructor
public class LocalityRepository implements ILocality {
    private final EntityManager entityManager;

    @Override
    public List<LocalityModel> findAll(IndexLocalityRequest request) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<LocalityModel> criteriaQuery = criteriaBuilder.createQuery(LocalityModel.class);
        Root<LocalityModel> localityRoot = criteriaQuery.from(LocalityModel.class);

        criteriaQuery.select(localityRoot);

        if (request.getSearchText() != null) {
            criteriaQuery.where(criteriaBuilder.like(localityRoot.get("title"), "%" + request.getSearchText() + "%"));
        }

        Query qr = session.createQuery(criteriaQuery);
        List<LocalityModel> result = qr.getResultList();

//        TypedQuery<LocalityModel> findAllQuery = session.createQuery(
//                "Select l from LocalityModel l",
//                LocalityModel.class
//        );

        return result;
    }

    @Override
    public LocalityModel findLocalityById(Long localityId) {
        Session session = entityManager.unwrap(Session.class);

        TypedQuery<LocalityModel> findLocalityByIdQuery = session.createQuery(
                "Select l from LocalityModel l where l.id = :localityId",
                LocalityModel.class
        );
        findLocalityByIdQuery.setParameter("localityId", localityId);

        return findLocalityByIdQuery.getSingleResult();
    }

    @Override
    public LocalityModel saveLocality(LocalityCreationDTO locality) {
        Session session = entityManager.unwrap(Session.class);
        LocalityModel l = new LocalityModel(locality.getTitle(), locality.getDescription(), locality.getCity(), locality.getStreet());
        Long localityId = (Long) session.save(l);

        LocalityModel savedLocality = findLocalityById(localityId);

        // Or is it going to have id?
        return savedLocality;
    }
}

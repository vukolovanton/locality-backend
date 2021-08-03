package com.backend.locality.api.locality;

import com.backend.locality.api.locality.interfaces.ILocalityRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
public class LocalityRepository implements ILocalityRepository {
    private final EntityManager entityManager;

    @Override
    public List<LocalityModel> findAll() {
        Session session = entityManager.unwrap(Session.class);
        TypedQuery<LocalityModel> findAllQuery = session.createQuery(
                "Select l from LocalityModel l",
                LocalityModel.class
        );

        return findAllQuery.getResultList();
    }

    @Override
    public LocalityModel findLocalityById(int localityId) {
        Session session = entityManager.unwrap(Session.class);

        TypedQuery<LocalityModel> findLocalityByIdQuery = session.createQuery(
                "Select l from LocalityModel l where l.id = :localityId",
                LocalityModel.class
        );
        findLocalityByIdQuery.setParameter("localityId", localityId);

        return findLocalityByIdQuery.getSingleResult();
    }

    @Override
    public LocalityModel saveLocality(LocalityModel locality) {
        Session session = entityManager.unwrap(Session.class);
        session.save(locality);

        // Or is it going to have id?
        return locality;
    }
}

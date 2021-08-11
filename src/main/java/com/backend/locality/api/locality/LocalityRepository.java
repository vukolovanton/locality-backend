package com.backend.locality.api.locality;

import com.backend.locality.api.locality.interfaces.ILocality;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
public class LocalityRepository implements ILocality {
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

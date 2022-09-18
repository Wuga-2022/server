package com.server.grad.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MissionsRepository {
    private final EntityManager em;

    public void save(Missions image) {em.persist(image);}

    public void delete(String path) {
        Missions image = em.createQuery("select i from Images i where i.filePath = :path", Missions.class)
                .setParameter("path", path)
                .getSingleResult();
        em.remove(image);
    }
}

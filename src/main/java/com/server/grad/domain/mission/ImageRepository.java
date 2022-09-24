package com.server.grad.domain.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ImageRepository {

    private final EntityManager em;

    public void save(Images image) {em.persist(image);}

    public void delete(String path) {
        Images image = em.createQuery("select i from Images i where i.filePath = :path", Images.class)
                .setParameter("path", path)
                .getSingleResult();
        em.remove(image);
    }
}

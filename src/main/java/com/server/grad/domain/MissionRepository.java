package com.server.grad.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;

public interface MissionRepository extends JpaRepository<Mission, Long> {

}

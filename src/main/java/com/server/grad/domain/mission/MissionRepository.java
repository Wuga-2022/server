package com.server.grad.domain.mission;

import com.server.grad.dto.comments.CommentsResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("select a from Comments a where a.mission_id.id = :m_id and a.user_id.id IN :users_id")
    List<CommentsResponseDto> findCommentsByUserId(@Param("m_id") Long m_id, @Param("users_id") List<Long> users_id);
}


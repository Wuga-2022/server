package com.server.grad.domain;

import com.server.grad.dto.comment.CommentsResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("select a from Comments a where a.mission_id.id = :m_id and a.user_id.id = :u_id")
    CommentsResponseDto findCommentsByWriter(@Param("m_id") Long m_id, @Param("u_id") Long u_id);

    @Query("select a from Comments a where a.mission_id.id = :m_id and a.user_id.id = :u_id")
    Comments findCommentsByWriterEntity(@Param("m_id") Long m_id, @Param("u_id") Long u_id);
}
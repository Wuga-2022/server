package com.server.grad.domain.comments;

import com.server.grad.dto.comments.CommentsResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("select c from Comments c where c.mission_id.id = :m_id and c.user_id.id = :u_id")
    CommentsResponseDto findCommentsByWriter(@Param("m_id") Long m_id, @Param("u_id") Long u_id);

    @Query("select c from Comments c where c.mission_id.id = :m_id and c.user_id.id = :u_id")
    Comments findCommentsByWriterEntity(@Param("m_id") Long m_id, @Param("u_id") Long u_id);
}
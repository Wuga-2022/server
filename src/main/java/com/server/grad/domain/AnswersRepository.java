package com.server.grad.domain;

import com.server.grad.dto.answers.AnswersResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
    @Query("select a from Answers a where a.question_id.id = :q_id and a.user_id.id = :u_id")
    AnswersResponseDto findAnswersByWriter(@Param("q_id") Long q_id, @Param("u_id") Long u_id);

    @Query("select a from Answers a where a.question_id.id = :q_id and a.user_id.id = :u_id")
    Answers findAnswersByWriterEntity(@Param("q_id") Long q_id, @Param("u_id") Long u_id);
}

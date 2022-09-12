package com.server.grad.domain;

import com.server.grad.dto.answers.AnswersResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select a from Answers a where a.question_id.id = :q_id and a.user_id.id IN :users_id")
    List<AnswersResponseDto> findAnswersByUserId(@Param("q_id") Long q_id, @Param("users_id") List<Long> users_id);
}

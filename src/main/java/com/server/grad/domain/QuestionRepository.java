package com.server.grad.domain;

import com.server.grad.dto.answers.AnswersResponseDto;
import com.server.grad.dto.question.QuestionResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select a from Answers a where a.question_id.id = :q_id and a.user_id.id IN :users_id")
    List<AnswersResponseDto> findAnswersByUserId(@Param("q_id") Long q_id, @Param("users_id") List<Long> users_id);

    @Query("select q from Question q where q.date = CURRENT_DATE")
    Optional<Question> findByDate();
}

package com.server.grad.domain.question;

import com.server.grad.dto.answers.AnswersResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select a from Answers a where a.question_id.id = :q_id and a.user_id.id IN :users_id")
    List<AnswersResponseDto> findAnswersByUserId(@Param("q_id") Long q_id, @Param("users_id") List<Long> users_id);

    @Query("select q from Question q where q.date = :today_date")
    Optional<Question> findByDate(@Param("today_date")LocalDate today_date);
}

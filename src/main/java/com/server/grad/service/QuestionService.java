package com.server.grad.service;

import com.server.grad.domain.question.Question;
import com.server.grad.domain.question.QuestionRepository;
import com.server.grad.dto.question.QuestionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionResponseDto findById(Long id){
        Question entity = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 문제 없음(생성 필요)"));

        return new QuestionResponseDto(entity);
    }

    public QuestionResponseDto findByDate(LocalDate today_date){
        Question entity = questionRepository.findByDate(today_date)
                .orElseThrow(() -> new IllegalArgumentException("해당 문제 없음(생성 필요)"));

        return new QuestionResponseDto(entity);
    }

    @Transactional
    public Long updateCompletion(Long q_id){
        Question question = questionRepository.findById(q_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 문제 없음(생성 필요)"));

        question.updateCompletion(true);

        return question.getId();
    }
}

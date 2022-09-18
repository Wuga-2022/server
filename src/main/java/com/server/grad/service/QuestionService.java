package com.server.grad.service;

import com.server.grad.domain.Question;
import com.server.grad.domain.QuestionRepository;
import com.server.grad.dto.question.QuestionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionResponseDto findById(Long id){
        Question entity = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 문제 없음(생성 필요)"));

        return new QuestionResponseDto(entity);
    }

}

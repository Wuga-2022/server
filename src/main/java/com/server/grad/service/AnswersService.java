package com.server.grad.service;

import com.server.grad.domain.AnswersRepository;
import com.server.grad.domain.Question;
import com.server.grad.domain.QuestionRepository;
import com.server.grad.web.dto.answers.AnswersResponseDto;
import com.server.grad.web.dto.answers.AnswersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswersService {

//    private final QuestionRepository questionRepository;
//    private final AnswersRepository answersRepository;
//
//    @Transactional
//    public AnswersResponseDto save(Long id, AnswersSaveRequestDto requestDto){
//        Question question = questionRepository.findById(id)
//                .orElseThrow(()-> new IllegalArgumentException("문제가 존재하지 않아 대답할 수 없습니다." + id));
//
//        requestDto.setQuestion_id(question);
//
//        return new AnswersResponseDto(answersRepository.save(requestDto.toEntity()));
//    }
}

package com.server.grad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

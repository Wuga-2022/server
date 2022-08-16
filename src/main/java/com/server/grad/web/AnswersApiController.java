package com.server.grad.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Answers Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AnswersApiController {

//    private final AnswersService answersService;
//
//    @PostMapping("/question/answers/{id}")
//    public AnswersResponseDto saveAnswer(@PathVariable Long id, @RequestBody AnswersSaveRequestDto requestDto){
//        AnswersResponseDto result = answersService.save(id, requestDto);
//        return result;
//    }
}

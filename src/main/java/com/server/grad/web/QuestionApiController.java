package com.server.grad.web;

import com.server.grad.service.QuestionService;
import com.server.grad.dto.QuestionResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Question Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionApiController {

    private final QuestionService questionService;

    @GetMapping("/question/{id}")
    @ApiOperation(value = "질문 반환")
    public QuestionResponseDto findById(@PathVariable Long id){
        return questionService.findById(id);
    }

//    @GetMapping("/question/answer/{id}")
//    public List<AnswersResponseDto> read(@PathVariable Long id){
//        QuestionResponseDto dto = questionService.findById(id);
//        return dto.getAnswers();
//    }
}

package com.server.grad.web;

import com.server.grad.dto.answers.AnswersResponseDto;
import com.server.grad.service.QuestionService;
import com.server.grad.dto.QuestionResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="Question Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionApiController {

    private final QuestionService questionService;

    @GetMapping("/question/{id}")
    @ApiOperation(value = "질문 반환", notes = "질문 id에 맞게 반환")
    public QuestionResponseDto findById(@PathVariable Long id){
        return questionService.findById(id);
    }

    @GetMapping("/question/answers/{id}")
    @ApiOperation(value = "모든 답변 반환", notes = "질문 id에 맞는 모든 답변 반환")
    public List<AnswersResponseDto> read(@PathVariable Long id){
        QuestionResponseDto dto = questionService.findById(id);
        return dto.getAnswers();
    }
}

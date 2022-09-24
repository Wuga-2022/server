package com.server.grad.web;

import com.server.grad.dto.answers.AnswersResponseDto;
import com.server.grad.service.AnswersService;
import com.server.grad.service.QuestionService;
import com.server.grad.dto.question.QuestionResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Api(value="Question Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionApiController {

    private final QuestionService questionService;
    private final AnswersService answersService;

    @GetMapping("/question/{date}")
    @ApiOperation(value = "질문 반환", notes = "질문 날짜에 맞게 반환")
    public QuestionResponseDto findByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return questionService.findByDate(date);
    }

    @GetMapping("/question/answers/{q_id}/{u_id}")
    @ApiOperation(value = "모든 답변 반환", notes = "질문 id에 맞는 모든 답변 반환")
    public List<AnswersResponseDto> read(@PathVariable Long q_id, @PathVariable Long u_id){
        return answersService.findUsersIdAnswer(q_id, u_id);
    }

    @PutMapping("/question/complete/{q_id}")
    public Long update(@PathVariable Long q_id){
        return questionService.updateCompletion(q_id);
    }
}

package com.server.grad.web;

import com.server.grad.dto.answers.AnswersEmojiUpdateReqDto;
import com.server.grad.dto.answers.AnswersResponseDto;
import com.server.grad.dto.answers.AnswersSaveRequestDto;
import com.server.grad.dto.answers.AnswersUpdateRequestDto;
import com.server.grad.service.AnswersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="Answers Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AnswersApiController {

    private final AnswersService answersService;

    @PostMapping("/answer/{q_id}/{u_id}")
    @ApiOperation(value = "답변 등록 --> Notion 확인", notes = "질문 id에 따른 답변 등록")
    public AnswersResponseDto saveAnswer(@PathVariable Long q_id,@PathVariable Long u_id, @RequestBody AnswersSaveRequestDto requestDto){
        AnswersResponseDto result = answersService.save(q_id,u_id,requestDto);
        return result;
    }

    @GetMapping("answer/{q_id}/{u_id}")
    @ApiOperation(value = "답변 반환", notes = "질문 id에 맞는 유저의 답변 반환")
    public AnswersResponseDto findAnswerByUser(@PathVariable Long q_id, @PathVariable Long u_id){
        return answersService.findAnswerByUser(q_id, u_id);
    }

    @PutMapping("answer/{q_id}/{u_id}")
    @ApiOperation(value = "답변 수정  --> Notion 확인", notes = "질문 id에 맞는 유저의 답변 수정")
    public Long update(@PathVariable Long q_id, @PathVariable Long u_id, @RequestBody AnswersUpdateRequestDto requestDto){
        return answersService.update(q_id, u_id, requestDto);
    }

    @PutMapping("/answer/emoji/{q_id}")
    @ApiOperation(value = "답변에 대한 이모지 수정", notes = "질문 id에 맞는 유저의 답변의 이모지 수정")
    public AnswersResponseDto updateEmoji(@PathVariable Long q_id, @RequestBody AnswersEmojiUpdateReqDto requestDto){
        return answersService.updateEmoji(q_id, requestDto);
    }
}

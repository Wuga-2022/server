package com.server.grad.web;

import com.server.grad.config.auth.LoginUser;
import com.server.grad.config.auth.dto.SessionUser;
import com.server.grad.domain.QuestionRepository;
import com.server.grad.domain.User;
import com.server.grad.dto.answers.AnswersResponseDto;
import com.server.grad.dto.user.UserResponseDto;
import com.server.grad.service.QuestionService;
import com.server.grad.dto.QuestionResponseDto;
import com.server.grad.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(value="Question Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionApiController {

    private final QuestionService questionService;
    private final UserService userService;

    private final QuestionRepository questionRepository;

    @GetMapping("/question/{q_id}")
    @ApiOperation(value = "질문 반환", notes = "질문 id에 맞게 반환")
    public QuestionResponseDto findById(@PathVariable Long q_id){
        return questionService.findById(q_id);
    }

    @GetMapping("/question/answers/{q_id}")
    @ApiOperation(value = "모든 답변 반환", notes = "질문 id에 맞는 모든 답변 반환")
    public List<AnswersResponseDto> read(@PathVariable Long q_id, @LoginUser SessionUser user){
        QuestionResponseDto dto = questionService.findById(q_id);
        List<AnswersResponseDto> answers = dto.getAnswers();

        //userId --> User.getFamily --> Family.getUsers  ==> Users List  -(stream)-> id
        //user_id = users.stream().map(e -> e.getId()).collect(Collectors.toList());
        //Answer  SQL In 절  select * from Answer a where a.q_id = 'q_id' and a.u_id IN 'users_id';

        UserResponseDto userDto = userService.findById(user.getId());
        List<User> users = userDto.getFamily_id().getUsers();
        List<Long> users_id = users.stream().map(User::getId).collect(Collectors.toList());

        return questionRepository.findAnswerByUserId(q_id, users_id);
    }
}

package com.server.grad.service;

import com.server.grad.domain.*;
import com.server.grad.dto.answers.AnswersEmojiUpdateReqDto;
import com.server.grad.dto.answers.AnswersResponseDto;
import com.server.grad.dto.answers.AnswersSaveRequestDto;
import com.server.grad.dto.answers.AnswersUpdateRequestDto;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswersService {

    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;
    private final UserRepository userRepository;

    @Transactional
    public AnswersResponseDto save(Long q_id, Long u_id, AnswersSaveRequestDto requestDto){
        Question question = questionRepository.findById(q_id)
                .orElseThrow(()-> new IllegalArgumentException("문제가 존재하지 않아 대답할 수 없습니다." + q_id));

        User user = userRepository.findById(u_id)
                .orElseThrow(()-> new IllegalArgumentException("유저가 존재하지 않아 대답할 수 없습니다." + u_id));

        requestDto.setQuestion_id(question);
        requestDto.setUser_id(user);

        return new AnswersResponseDto(answersRepository.save(requestDto.toEntity()));
    }

    public List<AnswersResponseDto> findUsersIdAnswer(Long q_id, Long u_id){
        //userId --> User.getFamily --> Family.getUsers  ==> Users List  -(stream)-> id
        //user_id = users.stream().map(e -> e.getId()).collect(Collectors.toList());
        //Answer  SQL In 절  select * from Answer a where a.q_id = 'q_id' and a.u_id IN 'users_id';

        User entity = userRepository.findById(u_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다." + u_id));

        List<User> users = entity.getFamily_id().getUsers();
        List<Long> users_id = users.stream().map(User::getId).collect(Collectors.toList());

        return questionRepository.findAnswersByUserId(q_id, users_id);
    }

    public AnswersResponseDto findAnswerByUser(Long q_id, Long u_id){
        return answersRepository.findAnswersByWriter(q_id, u_id);
    }

    @Transactional
    public Long update(Long q_id, Long u_id, AnswersUpdateRequestDto requestDto){
        Answers answers = answersRepository.findAnswersByWriterEntity(q_id, u_id);

        answers.update(requestDto.getAnswer(), requestDto.getDate());

        return answers.getId();
    }

    @Transactional
    public AnswersResponseDto updateEmoji(Long q_id, AnswersEmojiUpdateReqDto requestDto){
        User user = userRepository.findByName(requestDto.getUser_name())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다."));

        Answers answers = answersRepository.findAnswersByWriterEntity(q_id, user.getId());
        answers.updateEmoji(requestDto.getEmoji());

        return new AnswersResponseDto(answers);
    }
}

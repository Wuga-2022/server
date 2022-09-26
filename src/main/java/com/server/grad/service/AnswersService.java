package com.server.grad.service;

import com.server.grad.domain.answers.Answers;
import com.server.grad.domain.answers.AnswersRepository;
import com.server.grad.domain.question.Question;
import com.server.grad.domain.question.QuestionRepository;
import com.server.grad.domain.user.User;
import com.server.grad.domain.user.UserRepository;
import com.server.grad.dto.answers.AnswersEmojiUpdateReqDto;
import com.server.grad.dto.answers.AnswersResponseDto;
import com.server.grad.dto.answers.AnswersSaveRequestDto;
import com.server.grad.dto.answers.AnswersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        Answers answers = Answers.builder()
                .emj_good(requestDto.getEmj_good())
                .emj_heart(requestDto.getEmj_heart())
                .emj_smile(requestDto.getEmj_smile())
                .answer(requestDto.getAnswer())
                .question_id(question)
                .user_id(user)
                .date(LocalDate.now())
                .build();

        return new AnswersResponseDto(answersRepository.save(answers));
    }

    public List<AnswersResponseDto> findUsersIdAnswer(Long q_id, Long u_id){
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
    public AnswersResponseDto updateEmoji(Long q_id, Long u_id, String emoji, int calc){
        Answers answers = answersRepository.findAnswersByWriterEntity(q_id, u_id);
        int num = calc == 0 ? -1 : 1;

        switch (emoji){
            case "good":
                answers.updateEmoji(answers.getEmj_good() + num, answers.getEmj_heart(), answers.getEmj_smile());
                break;
            case "heart":
                answers.updateEmoji(answers.getEmj_good(), answers.getEmj_heart() + num, answers.getEmj_smile());
                break;
            case "smile":
                answers.updateEmoji(answers.getEmj_good(), answers.getEmj_heart(), answers.getEmj_smile() + num);
                break;
        }

        return new AnswersResponseDto(answers);
    }
}

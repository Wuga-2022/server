package com.server.grad.service;

import com.server.grad.domain.*;
import com.server.grad.dto.comments.CommentsResponseDto;
import com.server.grad.dto.comments.CommentsSaveRequestDto;
import com.server.grad.dto.comments.CommentsUpdateRequestDto;

import com.server.grad.domain.CommentsRepository;
import com.server.grad.domain.Mission;
import com.server.grad.domain.MissionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final MissionRepository missionRepository;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentsResponseDto save(Long m_id, Long u_id, CommentsSaveRequestDto requestDto){
        Mission mission = missionRepository.findById(m_id)
                .orElseThrow(()-> new IllegalArgumentException("미션이 존재하지 않아 댓글을 작성할 수 없습니다." + m_id));

        User user = userRepository.findById(u_id)
                .orElseThrow(()-> new IllegalArgumentException("유저가 존재하지 않아 대답할 수 없습니다." + u_id));

        requestDto.setMission_id(mission);
        requestDto.setUser_id(user);

        return new CommentsResponseDto(commentsRepository.save(requestDto.toEntity()));
    }
    public List<CommentsResponseDto> findUsersIdAnswer(Long m_id, Long u_id){

        User entity = userRepository.findById(u_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다." + u_id));

        List<User> users = entity.getFamily_id().getUsers();
        List<Long> users_id = users.stream().map(User::getId).collect(Collectors.toList());

        return missionRepository.findCommentsByUserId(m_id, users_id);
    }

    public CommentsResponseDto findCommentByUser(Long m_id, Long u_id){
        return commentsRepository.findCommentsByWriter(m_id, u_id);
    }

    @Transactional
    public Long update(Long m_id, Long u_id, CommentsUpdateRequestDto requestDto){
        Comments comments = commentsRepository.findCommentsByWriterEntity(m_id, u_id);

        comments.update(requestDto.getComment(), requestDto.getDate());

        return comments.getId();
    }
}

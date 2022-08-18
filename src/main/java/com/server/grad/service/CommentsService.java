package com.server.grad.service;

import com.server.grad.domain.CommentsRepository;
import com.server.grad.domain.Mission;
import com.server.grad.domain.MissionRepository;
import com.server.grad.dto.CommentsResponseDto;
import com.server.grad.dto.CommentsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final MissionRepository missionRepository;
    private final CommentsRepository commentsRepository;

    @Transactional
    public CommentsResponseDto save(Long id, CommentsSaveRequestDto requestDto){
        Mission mission = missionRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("미션이 존재하지 않아 댓글을 작성할 수 없습니다." + id));

        requestDto.setMission_id(mission);

        return new CommentsResponseDto(commentsRepository.save(requestDto.toEntity()));

    }
}

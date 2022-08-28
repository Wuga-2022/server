package com.server.grad.service;

import com.server.grad.domain.CommentsRepository;
import com.server.grad.domain.Mission;
import com.server.grad.domain.MissionRepository;
import com.server.grad.dto.mission.MissionResponseDto;
import com.server.grad.dto.mission.MissionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    private final  S3Service service;

    @Transactional
    public Long save(MissionSaveRequestDto requestDto){
        return missionRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<Mission> getAll(){
        return missionRepository.findAll();
    }

    public MissionResponseDto findById(Long id) {
        Mission entity = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음(생성 필요)"));

        return new MissionResponseDto(entity);
    }
}

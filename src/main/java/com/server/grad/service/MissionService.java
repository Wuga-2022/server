package com.server.grad.service;

import com.server.grad.domain.Mission;
import com.server.grad.domain.MissionRepository;
import com.server.grad.web.dto.MissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionResponseDto findById(Long id) {
        Mission entity = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음(생성 필요)"));

        return new MissionResponseDto(entity);
    }
}

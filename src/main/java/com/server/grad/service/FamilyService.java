package com.server.grad.service;

import com.server.grad.domain.Family;
import com.server.grad.domain.FamilyRepository;
import com.server.grad.domain.User;
import com.server.grad.dto.FamilyResponseDto;
import com.server.grad.dto.FamilyUpdateRequestDto;
import com.server.grad.dto.user.UserResponseDto;
import com.server.grad.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;

    @Transactional
    public Long update(Long id, FamilyUpdateRequestDto requestDto){
        Family family = familyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 가족 정보가 없습니다." + id));

        family.update(requestDto.getPoints());

        return id;
    }

    public FamilyResponseDto findById(Long id){
        Family entity = familyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 가족 정보 없음 = " + id));

        return new FamilyResponseDto(entity);
    }
}

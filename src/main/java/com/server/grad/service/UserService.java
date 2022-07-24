package com.server.grad.service;

import com.server.grad.domain.User;
import com.server.grad.domain.UserRepository;
import com.server.grad.dto.UserResponseDto;
import com.server.grad.dto.UserSaveRequestDto;
import com.server.grad.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save (UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보 없음 = " + id));

        user.update(requestDto.getName(), requestDto.getEmail(), requestDto.getRole(), requestDto.getFamily_code());

        return id;
   }

   @Transactional
    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보 없음 = " + id));

        userRepository.delete(user);
   }

    public UserResponseDto findById(Long id){
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보 없음 = " + id));

        return new UserResponseDto(entity);
    }

}
package com.server.grad.service;

import com.server.grad.domain.User;
import com.server.grad.domain.UserRepository;
import com.server.grad.dto.user.UserResponseDto;
import com.server.grad.dto.user.UserSaveRequestDto;
import com.server.grad.dto.user.UserUpdateFamilyDto;
import com.server.grad.dto.user.UserUpdateRequestDto;
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
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다." + id));

        user.update(requestDto.getName(), requestDto.getEmail(), requestDto.getMember());

        return id;
   }

   @Transactional
   public Long updateFamily(String email, UserUpdateFamilyDto requestDto){
       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보 없음 = " + email));

       user.updateFamily(requestDto.getFamily_id());

       return user.getId();
   }

    public UserResponseDto findById(Long id){
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다." + id));

        return new UserResponseDto(entity);
    }

}

package com.server.grad.service;

import com.server.grad.domain.user.User;
import com.server.grad.domain.user.UserRepository;
import com.server.grad.dto.user.*;
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
    public Long update(Long u_id, UserUpdateRequestDto requestDto){
        User user = userRepository.findById(u_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다." + u_id));

        user.update(requestDto.getName(), requestDto.getMember(), requestDto.getProfile_img());
        return user.getId();
   }

   public UserLoginResponseDto login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다." + email));

        if(user.getPassword().equals(password)){
            return new UserLoginResponseDto(user);
        }
       return null;
   }

   @Transactional
   public Long updateFamily(Long u_id, UserUpdateFamilyDto requestDto){
       User user = userRepository.findById(u_id)
               .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보 없음 = " + u_id));

       user.updateFamily(requestDto.getFamily_id());

       return user.getId();
   }

    public UserResponseDto findById(Long id){
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다." + id));

        return new UserResponseDto(entity);
    }

}

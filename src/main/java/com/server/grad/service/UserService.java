package com.server.grad.service;

import com.server.grad.domain.QuestionRepository;
import com.server.grad.domain.User;
import com.server.grad.domain.UserRepository;
import com.server.grad.dto.answers.AnswersResponseDto;
import com.server.grad.dto.user.UserResponseDto;
import com.server.grad.dto.user.UserSaveRequestDto;
import com.server.grad.dto.user.UserUpdateFamilyDto;
import com.server.grad.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save (UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(String email, UserUpdateRequestDto requestDto){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다." + email));

        user.update(requestDto.getName(), requestDto.getMember());

        return user.getId();
   }

   public Long login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다." + email));

        if(user.getPassword().equals(password)){
            return user.getId();
        } else{
            return 0L;
        }
   }

    //Session User 때문에 email -> u_id 변경함
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

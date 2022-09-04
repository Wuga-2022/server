package com.server.grad.service;

import com.server.grad.domain.Family;
import com.server.grad.domain.FamilyRepository;
import com.server.grad.domain.User;
import com.server.grad.domain.UserRepository;

import com.server.grad.dto.FamilyResponseDto;

import com.server.grad.dto.family.FamilySaveRequestDto;
import com.server.grad.dto.user.UserUpdateFamilyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final UserService userService;

//    public FamilyResponseDto findById(Long id) {
//        Family entity = familyRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 가족 정보 없음 = " + id));
//
//        return new FamilyResponseDto(entity);
//    }

    @Transactional
    public Long updateUserFamCode(String email, String familycode){
        UserUpdateFamilyDto updateFamilyDto = new UserUpdateFamilyDto(familyRepository.findByFamilycode(familycode)
                .orElseThrow(() -> new IllegalArgumentException("해당 가족이 존재하지 않습니다." + familycode))
        );

        return userService.updateFamily(email, updateFamilyDto);
    }

    @Transactional
    public String createCode(String email) {
        Random random = new Random();
        String generatedString;
        do {
            generatedString = random.ints(48, 123)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(6)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

        } while(familyRepository.findByFamilycode(generatedString).isPresent());

        // user db에 family id 넣기
        FamilySaveRequestDto requestDto = new FamilySaveRequestDto(generatedString);

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("유저가 존재하지 않습니다." + email));

        user.setFamily_id(familyRepository.save(requestDto.toEntity()));
        userRepository.save(user);

        return generatedString;

    }
}

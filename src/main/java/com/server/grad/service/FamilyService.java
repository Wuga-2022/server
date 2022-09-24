package com.server.grad.service;

import com.server.grad.domain.family.FamilyRepository;

import com.server.grad.domain.user.User;
import com.server.grad.domain.user.UserRepository;

import com.server.grad.dto.family.FamilySaveRequestDto;
import com.server.grad.dto.user.UserNameResponseDto;
import com.server.grad.dto.user.UserUpdateFamilyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public Long updateUserFamCode(Long u_id, String familycode){
        UserUpdateFamilyDto updateFamilyDto = new UserUpdateFamilyDto(familyRepository.findByFamilycode(familycode)
                .orElseThrow(() -> new IllegalArgumentException("해당 가족이 존재하지 않습니다." + familycode))
        );

        return userService.updateFamily(u_id, updateFamilyDto);
    }

    @Transactional
    public String createCode(Long u_id) {
        Random random = new Random();
        String generatedString;
        do {
            generatedString = random.ints(48, 123)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(6)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

        } while(familyRepository.findByFamilycode(generatedString).isPresent());

        FamilySaveRequestDto requestDto = new FamilySaveRequestDto(generatedString);

        User user = userRepository.findById(u_id)
                .orElseThrow(()-> new IllegalArgumentException("유저가 존재하지 않습니다." + u_id));

        user.setFamily_id(familyRepository.save(requestDto.toEntity()));
        userRepository.save(user);

        return generatedString;
    }

    public List<UserNameResponseDto> getFamilyMembers(Long u_id){
        User user = userRepository.findById(u_id)
                .orElseThrow(()-> new IllegalArgumentException("유저가 존재하지 않습니다." + u_id));

        return user.getFamily_id().getUsers().stream().map(UserNameResponseDto::new).collect(Collectors.toList());
    }
}

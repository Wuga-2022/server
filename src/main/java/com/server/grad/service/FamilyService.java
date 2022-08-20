package com.server.grad.service;

import com.server.grad.domain.Family;
import com.server.grad.domain.FamilyRepository;
import com.server.grad.dto.family.FamilySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;

    @Transactional
    public Long save (FamilySaveRequestDto requestDto){
        return familyRepository.save(requestDto.toEntity()).getId();
    }

    public String createCode() {
        Random random = new Random();
        String generatedString;
        do {
            generatedString = random.ints(48, 123)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(6)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

        } while(familyRepository.findByFamilycode(generatedString).isPresent());

        return generatedString;
    }
}

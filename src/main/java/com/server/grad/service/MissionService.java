package com.server.grad.service;

import com.server.grad.domain.*;
import com.server.grad.dto.CommentsResponseDto;
import com.server.grad.dto.mission.MissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;

    private final  S3Service s3Service;

    private final ImageRepository imageRepository;

    public MissionResponseDto upload(Map<Object, String> missionInfo, List<String> files) {
        String mission = missionInfo.get("mission");
        LocalDate date = LocalDate.parse(missionInfo.get("date"));
        int similarity = Integer.parseInt(missionInfo.get("similarity"));
        Boolean success = Boolean.parseBoolean(missionInfo.get("success"));
        List<Comments> comments = null;
        Mission mission1 = Mission.createMission(mission, date, similarity, success, comments);

        if (!files.isEmpty()) {
            List<Image> images = new ArrayList<>();
            for (String file : files) {
                Image imageFile = Image.builder()
                        .mission(mission1)
                        .filePath(file)
                        .build();
                imageRepository.save(imageFile);
                images.add(imageFile);
            }
            mission1.setImages(images);
        }

        missionRepository.save(mission1);

        MissionResponseDto singlemission = MissionResponseDto.builder()
                .id(mission1.getId())
                .date(mission1.getDate())
                .mission(mission1.getMission())
                .similarity(mission1.getSimilarity())
                .success(mission1.getSuccess())
                .comments(mission1.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList()))
                .build();

        List<Image> images = mission1.getImages();
        List<String> imageurls = new ArrayList<>();
        for (Image img : images) {
            imageurls.add("https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + img.getFilePath());
        }
        singlemission.setImages(imageurls);

        return singlemission;
    }

    public void deleteMission(Long id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음"));
        List<Image> images = mission.getImages();
        for (Image image : images) {
            s3Service.deleteFile(image.getFilePath());
        }
        missionRepository.delete(mission);
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

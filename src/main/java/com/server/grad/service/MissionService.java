package com.server.grad.service;

import com.server.grad.domain.*;
import com.server.grad.dto.comments.CommentsResponseDto;
import com.server.grad.dto.mission.MissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final MissionsRepository missionsRepository;

    public MissionResponseDto create(Map<Object, String> missionInfo, List<String> files) {
        LocalDate date = LocalDate.parse(missionInfo.get("date"));
        int similarity = Integer.parseInt(missionInfo.get("similarity"));
        Boolean success = Boolean.parseBoolean(missionInfo.get("success"));
        List<Comments> comments = null;
        Mission mission1 = Mission.createMission(date, similarity, success, comments);

        if (!files.isEmpty()) {
            List<Missions> images = new ArrayList<>();
            for (String file : files) {
                Missions imageFile = Missions.builder()
                        .mission(mission1)
                        .filePath(file)
                        .build();
                missionsRepository.save(imageFile);
                images.add(imageFile);
            }
            mission1.setMission(images);
        }

        missionRepository.save(mission1);

        MissionResponseDto singlemission = MissionResponseDto.builder()
                .id(mission1.getId())
                .date(mission1.getDate())
                .similarity(mission1.getSimilarity())
                .success(mission1.getSuccess())
                //.comments(mission1.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList()))
                .build();

        List<Missions> images = mission1.getMission();
        List<String> imageurls = new ArrayList<>();
        for (Missions img : images) {
            imageurls.add("https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + img.getFilePath());
        }
        singlemission.setImages(imageurls);

        return singlemission;
    }

    public MissionResponseDto upload(Long id, Map<Object, String> missionInfo, List<String> files) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음"));

        LocalDate date = LocalDate.parse(missionInfo.get("date"));
        int similarity = Integer.parseInt(missionInfo.get("similarity"));
        Boolean success = Boolean.parseBoolean(missionInfo.get("success"));
        List<Comments> comments = null;

        mission.update(date, similarity, success, comments);

        if (!files.isEmpty()) {
            List<Images> images = new ArrayList<>();
            for (String file : files) {
                Images imageFile = Images.builder()
                        .mission(mission)
                        .filePath(file)
                        .build();
                imageRepository.save(imageFile);
                images.add(imageFile);
            }
            mission.setImages(images);
        }

        missionRepository.save(mission);

        MissionResponseDto singlemission = MissionResponseDto.builder()
                .id(mission.getId())
                .date(mission.getDate())
                .similarity(mission.getSimilarity())
                .success(mission.getSuccess())
                //.comments(mission1.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList()))
                .build();

        List<Images> images = mission.getImages();
        List<String> imageurls = new ArrayList<>();
        for (Images img : images) {
            imageurls.add("https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + img.getFilePath());
        }
        singlemission.setImages(imageurls);

        return singlemission;
    }

    public void deleteMission(Long id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음"));
        List<Images> images = mission.getImages();
        for (Images image : images) {
            s3Service.deleteFile(image.getFilePath());
        }
        missionRepository.delete(mission);
    }

    @Transactional
    public List<MissionResponseDto> getAll(){
        List<Mission> am = missionRepository.findAll();
        List<MissionResponseDto> mr = new ArrayList<>();
        for (Mission mission : am){
            MissionResponseDto dto = MissionResponseDto.builder()
                    .id(mission.getId())
                    .mission(mission.getMission().stream().map(String::valueOf).collect(Collectors.toList()))
                    .images(mission.getImages().stream().map(String::valueOf).collect(Collectors.toList()))
                    .date(mission.getDate())
                    .similarity(mission.getSimilarity())
                    .success(mission.getSuccess())
                    .comments(mission.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList()))
                    .build();
            mr.add(dto);
        }
        return mr;
    }

    public MissionResponseDto findById(Long id) {
        Mission entity = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음(생성 필요)"));

        return new MissionResponseDto(entity);
    }

}

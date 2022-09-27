package com.server.grad.service;

import com.server.grad.domain.comments.Comments;
import com.server.grad.domain.mission.*;
import com.server.grad.dto.mission.MissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            mission1.setMissions(images.get(0));
        }

        missionRepository.save(mission1);

        MissionResponseDto singlemission = MissionResponseDto.builder()
                .id(mission1.getId())
                .date(mission1.getDate())
                .similarity(mission1.getSimilarity())
                .success(mission1.getSuccess())
                .build();

        Missions img = mission1.getMissions();
        List<String> imageurls = new ArrayList<>();
        imageurls.add("https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + img.getFilePath());
        singlemission.setMission(imageurls.get(0));

        return singlemission;
    }

    public MissionResponseDto upload(Long id, Map<Object, String> missionInfo, List<String> files) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음"));

        LocalDate date = LocalDate.parse(missionInfo.get("date"));
        int similarity = Integer.parseInt(missionInfo.get("similarity"));
        Boolean success = Boolean.parseBoolean(missionInfo.get("success"));

        if (mission.getImages() != null) {
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
                //mission.setImages(images.get(0));
                mission.update(date, similarity, success, images.get(0));
            }
            missionRepository.save(mission);

            MissionResponseDto singlemission = MissionResponseDto.builder()
                    .id(mission.getId())
                    .date(mission.getDate())
                    .similarity(mission.getSimilarity())
                    .success(mission.getSuccess())
                    .build();


            Images img = mission.getImages();
            List<String> imageurls = new ArrayList<>();
            imageurls.add("https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + img.getFilePath());
            singlemission.setImage(imageurls.get(0));

            return singlemission;

        } else {
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
                mission.setImages(images.get(0));
            }

            missionRepository.save(mission);

            MissionResponseDto singlemission = MissionResponseDto.builder()
                    .id(mission.getId())
                    .date(mission.getDate())
                    .similarity(mission.getSimilarity())
                    .success(mission.getSuccess())
                    .build();

            Images img = mission.getImages();
            List<String> imageurls = new ArrayList<>();
            imageurls.add("https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + img.getFilePath());
            singlemission.setImage(imageurls.get(0));

            return singlemission;
        }
    }


    public void deleteMission(Long id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음"));
        Images images = mission.getImages();
        s3Service.deleteFile(images.getFilePath());
        missionRepository.delete(mission);
    }

    @Transactional
    public List<MissionResponseDto> getAll(){
        List<Mission> am = missionRepository.findAll();
        List<MissionResponseDto> mr = new ArrayList<>();
        for (Mission mission : am){
            if(mission.getImages()!=null){
                MissionResponseDto dto = MissionResponseDto.builder()
                        .id(mission.getId())
                        .mission("https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + mission.getMissions().getFilePath())
                        .image("https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + mission.getImages().getFilePath())
                        .date(mission.getDate())
                        .similarity(mission.getSimilarity())
                        .success(mission.getSuccess())
                        .build();
                mr.add(dto);
            }
        }
        return mr;
    }

    public MissionResponseDto findById(Long id) {
        Mission entity = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음(생성 필요)"));

        return new MissionResponseDto(entity);

    }

}
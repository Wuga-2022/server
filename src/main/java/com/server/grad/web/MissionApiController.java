package com.server.grad.web;

import com.server.grad.domain.Mission;
import com.server.grad.dto.CommentsResponseDto;
import com.server.grad.dto.mission.MissionSaveRequestDto;
import com.server.grad.service.MissionService;
import com.server.grad.dto.mission.MissionResponseDto;
import com.server.grad.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(value="Mission Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MissionApiController {

    private final MissionService missionService;
    private final S3Service s3Service;

    @GetMapping("/missions")
    @ApiOperation(value = "모든 미션 반환")
    public List<Mission> getAll(){
        return missionService.getAll();
    }

    @GetMapping("/mission/{id}")
    @ApiOperation(value = "미션 정보 반환", notes = "미션 id에 맞게 반환")
    public MissionResponseDto findById(@PathVariable Long id){
        return missionService.findById(id);
    }

    @PostMapping("mission/{id}")
    @ApiOperation(value = "미션 등록")
    public Long save(@RequestBody MissionSaveRequestDto requestDto){
        return missionService.save(requestDto);
    }

    @PostMapping("/file/upload")
    @ApiOperation(value = "S3에 파일 업로드")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file){
        return new ResponseEntity<>(s3Service.uploadFile(file), HttpStatus.ACCEPTED);
    }

    @GetMapping("/file/download/{fileName}")
    @ApiOperation(value = "S3의 파일 다운로드")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
        byte[] data = s3Service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return  ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/file/delete/{fileName}")
    @ApiOperation(value = "S3의 파일 삭제")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return new ResponseEntity<>(s3Service.deleteFile(fileName), HttpStatus.ACCEPTED);
    }

    @GetMapping("/mission/comments/{id}")
    @ApiOperation(value = "모든 댓글 반환", notes = "미션 id에 맞는 모든 댓글 반환")
    public List<CommentsResponseDto> read(@PathVariable Long id){
        MissionResponseDto dto = missionService.findById(id);
        return dto.getComments();
    }
}

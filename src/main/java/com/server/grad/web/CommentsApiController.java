package com.server.grad.web;

import com.server.grad.dto.comments.CommentsResponseDto;
import com.server.grad.dto.comments.CommentsSaveRequestDto;
import com.server.grad.service.CommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Comments Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentsApiController {

    private final CommentsService commentsService;

    @PostMapping("/mission/comments/{id}")
    @ApiOperation(value = "댓글 등록", notes = "미션 id에 따른 댓글 등록")
    public CommentsResponseDto saveComment(@PathVariable Long id, @RequestBody CommentsSaveRequestDto requestDto){
        CommentsResponseDto result = commentsService.save(id, requestDto);
        return  result;
    }
}

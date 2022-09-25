package com.server.grad.web;

import com.server.grad.dto.comments.CommentsEmojiUpdateReqDto;
import com.server.grad.dto.comments.CommentsResponseDto;
import com.server.grad.dto.comments.CommentsSaveRequestDto;
import com.server.grad.dto.comments.CommentsUpdateRequestDto;

import com.server.grad.service.CommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(value="Comments Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentsApiController {

    private final CommentsService commentsService;

    @PostMapping("/comment/{m_id}/{u_id}")
    @ApiOperation(value = "댓글 등록", notes = "미션 id에 따른 댓글 등록")
    public CommentsResponseDto saveComment(@PathVariable Long m_id, @PathVariable Long u_id, @RequestBody CommentsSaveRequestDto requestDto){
        return commentsService.save(m_id, u_id, requestDto);
    }

    @GetMapping("comment/{m_id}/{u_id}")
    @ApiOperation(value = "댓글 반환", notes = "미션 id에 맞는 유저의 댓글 반환")
    public CommentsResponseDto findCommentByUser(@PathVariable Long m_id, @PathVariable Long u_id){
        return commentsService.findCommentByUser(m_id, u_id);
    }

    @PutMapping("comment/{m_id}/{u_id}")
    @ApiOperation(value = "댓글 수정", notes = "미션 id에 맞는 유저의 댓글 수정")
    public Long update(@PathVariable Long m_id, @PathVariable Long u_id, @RequestBody CommentsUpdateRequestDto requestDto){
        return commentsService.update(m_id, u_id, requestDto);
    }

    @PutMapping("/comment/emoji/{m_id}/{u_id}")
    @ApiOperation(value = "답변에 대한 이모지 수정", notes = "질문 id에 맞는 유저의 답변의 이모지 수정")
    public CommentsResponseDto updateEmoji(@PathVariable Long q_id, @PathVariable Long u_id, @RequestParam String emoji, @RequestParam int calc){
        return commentsService.updateEmoji(q_id,u_id, emoji, calc);
    }

}

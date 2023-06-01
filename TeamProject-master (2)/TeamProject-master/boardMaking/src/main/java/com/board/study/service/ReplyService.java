package com.board.study.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.study.dto.board.ReplyRequestDto;
import com.board.study.entity.board.Reply;
import com.board.study.entity.board.ReplyRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ReplyService {
	private final ReplyRepository replyRepository;
	
	@Transactional
	public Long save(ReplyRequestDto replySaveDto) {
		return replyRepository.save(replySaveDto.toEntity()).getBno();
	}
	public List<Reply> findById(Long id) {
		return replyRepository.findReplyById(id);
	}
	public int InsertReply(ReplyRequestDto replyRequestDto) {
		return replyRepository.replyWrite(replyRequestDto);
	}
}

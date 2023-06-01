package com.board.study.dto.board;

import java.time.LocalDateTime;

import com.board.study.entity.board.Reply;

import lombok.Getter;

@Getter
public class ReplyResponseDto {
	private int rno;
	private Long bno;
	private String writer;
	private String rcontent;
	private LocalDateTime registerTime;
	
	public ReplyResponseDto(Reply entity) {
		this.rno = entity.getRno();
		this.bno = entity.getBno();
		this.writer = entity.getWriter();
		this.rcontent = entity.getRcontent();
		this.registerTime = entity.getRegisterTime();
	}
}

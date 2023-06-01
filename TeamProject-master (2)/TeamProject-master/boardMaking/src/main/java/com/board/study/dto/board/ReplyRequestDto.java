package com.board.study.dto.board;

import com.board.study.entity.board.Reply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyRequestDto {
	private Long bno;
	private String writer;
	private String rcontent;
	
	public Reply toEntity() {
		return Reply.builder()
				.bno(bno)
				.rcontent(rcontent)
				.writer(writer)
				.build();
	}
}

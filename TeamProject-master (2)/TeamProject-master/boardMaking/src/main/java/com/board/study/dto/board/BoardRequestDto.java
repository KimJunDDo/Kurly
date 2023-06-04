package com.board.study.dto.board;

import com.board.study.entity.board.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//게시판 요청 데이터 담당
//게시물 등록, 게시물 수정, 게시물 상세 조회에 필요한 필드 정의
@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {
	private Long id;
	private String title;	
	private String content;
	private String registerId;
	
	public Board toEntity() {
		return Board.builder()
				.title(title)
				.content(content)
				.registerId(registerId)
				.build();
	}
}

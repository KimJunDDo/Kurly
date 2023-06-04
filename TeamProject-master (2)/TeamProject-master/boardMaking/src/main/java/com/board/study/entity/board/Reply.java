package com.board.study.entity.board;

import com.board.study.entity.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//reply 테이블의 @Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "reply")
public class Reply extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rno;
	private Long bno;
	private String writer;
	private String rcontent;
	
	@Builder
	public Reply(int rno, Long bno, String writer, String rcontent) {
		this.rno = rno;
		this.bno = bno;
		this.writer = writer;
		this.rcontent = rcontent;
	}
	
	
}

package com.board.study.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

//반복되는 날짜 데이터의 공통 처리 담당
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
	
	@CreatedDate
	private LocalDateTime registerTime;
	
	@LastModifiedDate
	private LocalDateTime updateTime;
}

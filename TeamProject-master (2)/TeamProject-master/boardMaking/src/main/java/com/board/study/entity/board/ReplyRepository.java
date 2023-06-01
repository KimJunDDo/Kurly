package com.board.study.entity.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.board.study.dto.board.ReplyRequestDto;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	static final String FIND_REPLY = "SELECT * FROM reply WHERE bno = :id ORDER BY register_time DESC";
	static final String INSERT_REPLY = "INSERT INTO reply(bno,writer,rcontent,register_time) VALUE(:#{#replyRequestDto.bno}, :#{#replyRequestDto.writer}, :#{#replyRequestDto.rcontent}, SYSDATE())";
	static final String DELETE_REPLY_ALL = "DELETE FROM reply WHERE bno = :id";

	@Transactional
	@Modifying
	@Query(value = FIND_REPLY, nativeQuery = true)
	public List<Reply> findReplyById(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value = INSERT_REPLY, nativeQuery = true)
	public int replyWrite(@Param("replyRequestDto") ReplyRequestDto replyRequestDto);

	@Transactional
	@Modifying
	@Query(value = DELETE_REPLY_ALL, nativeQuery = true)
	public int deleteAllReply(@Param("id") Long id);
}

package com.board.study.entity.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.board.study.dto.board.BoardRequestDto;

//JpaRepository 구현체
//Entity에 의해 생성된 DB에 접근하는 메서드를 사용하기 위한 인터페이스 CRUD
public interface BoardRepository extends JpaRepository<Board, Long> {
	static final String UPDATE_BOARD = "UPDATE Board " + "SET CONTENT = :#{#boardRequestDto.content}, "
			+ "UPDATE_TIME = NOW() " + "WHERE ID = :#{#boardRequestDto.id}";
	static final String UPDATE_BOARD_READ_CNT_INC = "UPDATE Board " + "SET READ_CNT = READ_CNT + 1 " + "WHERE ID = :id";

	static final String DELETE_BOARD = "DELETE FROM Board " + "WHERE ID IN (:deleteList)";

	@Transactional
	@Modifying
	@Query(value = UPDATE_BOARD, nativeQuery = true)
	public int updateBoard(@Param("boardRequestDto") BoardRequestDto boardRequestDto);

	@Transactional
	@Modifying
	@Query(value = UPDATE_BOARD_READ_CNT_INC, nativeQuery = true)
	public int updateBoardReadCntInc(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value = DELETE_BOARD, nativeQuery = true)
	public int deleteBoard(@Param("deleteList") Long[] deleteList);
}

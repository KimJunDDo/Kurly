package com.board.study.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.study.dto.board.BoardRequestDto;
import com.board.study.dto.board.ReplyRequestDto;
import com.board.study.entity.board.Reply;
import com.board.study.service.BoardService;
import com.board.study.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;
	private ReplyService replyService;

	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!");
		return "/board/hello";
	}

	@GetMapping("/company")
	public String company() {
		return "/board/company";
	}

	@GetMapping("/map")
	public String map() {
		return "/board/map";
	}

	@GetMapping("/hosikdang")
	public String hosikdang() {
		return "/board/hosikdang";
	}

	@GetMapping("/knu")
	public String redirectKnu() {
		return "redirect:https://web.kangnam.ac.kr/";
	}
	@GetMapping("/naver")
	public String redirectNaver() {
		return "redirect:https://www.naver.com";
	}
	@GetMapping("/facebook")
	public String redirectFacebook() {
		return "redirect:https://www.facebook.com";
	}
	@GetMapping("/google")
	public String redirectGoogle() {
		System.out.println("dd");
		return "redirect:https://www.google.co.kr";
	}
	@GetMapping("/kakao")
	public String redirectKakao() {
		return "redirect:https://www.daum.net";
	}

	@GetMapping("/board/list") // GET 방식 사용 정보 가져와서 조회하기 위함
	public String getBoardListPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "5") Integer size) throws Exception {
		try {
			model.addAttribute("resultMap", boardService.findAll(page, size));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/board/list";
	}

	@GetMapping("/board/write") // GET 방식 사용 정보 가져와서 조회하기 위함
	public String getBoardWritePage(Model model, BoardRequestDto boardRequestDto) {
		return "/board/write";
	}

	@GetMapping("/board/view") // GET 방식 사용 정보 가져와서 조회하기 위함
	public String getBoardViewPage(Model model, BoardRequestDto boardRequestDto) throws Exception {

		try {
			if (boardRequestDto.getId() != null) {
				model.addAttribute("info", boardService.findById(boardRequestDto.getId()));
				List<Reply> replyList = replyService.findById(boardRequestDto.getId()); // 해당 ID 댓글 리스트 조회
				model.addAttribute("replyList", replyList); // model을 통해 속성 대입
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/view";
	}

	@PostMapping("/board/view/action")
	public String boardViewAction(Model model, BoardRequestDto boardRequestDto, ReplyRequestDto replyRequestDto)
			throws Exception {
		try {
			replyRequestDto.setBno(boardRequestDto.getId()); // boardRequestDto를 통해 id 가져와 replyReqeustDto bno 설정
			replyService.InsertReply(replyRequestDto); // replyRequestDto 인자로 받아서 InsertReply메소드
			int result = boardService.updateBoard(boardRequestDto);
			if (result < 1) {
				throw new Exception("#Exception boardViewAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/board/view?id=" + boardRequestDto.getId();
	}

	@PostMapping("/board/write/action") // POST 방식 데이터를 서버 제출하여 추가 및 수정 하기 위함
	public String boardWriteAction(Model model, BoardRequestDto boardRequestDto) throws Exception {
		try {
			Long result = boardService.save(boardRequestDto);
			if (result < 0) {
				throw new Exception("#Exception boardWriteAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/board/list";
	}

	@PostMapping("/board/view/delete")
	public String boardViewDeleteAction(Model model, @RequestParam() Long id) throws Exception {
		try {
			boardService.deleteById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/board/list";
	}

	@PostMapping("/board/delete")
	public String boardDeleteAction(Model model, @RequestParam() Long[] deleteId) throws Exception {
		try {
			boardService.deleteAll(deleteId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/board/list";
	}

}
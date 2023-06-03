package com.board.study.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.study.service.KakaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class KakaoController {
	private final KakaoService kakaoService = new KakaoService();
	@GetMapping("/auth/kakao/callback")
	public String kakaocallback(HttpSession session, @RequestParam String code) throws IOException {
		String access_Token = kakaoService.getKaKaoAccessToken(code);
		String name = kakaoService.createKakaoUser(access_Token);
		session.setAttribute("kakaoName", name);
		return "board/loginResult";
	}
}

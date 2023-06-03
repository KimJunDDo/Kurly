package com.board.study.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.study.service.GoogleService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GoogleController {

	GoogleService loginService;

	public GoogleController(GoogleService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/login/oauth2/code/{registrationId}")
	public String googleLogin(HttpSession session, @RequestParam String code, @PathVariable String registrationId) {
		String name = loginService.socialLogin(code, registrationId);
		session.setAttribute("googleName", name);
		return "/board/loginResult";
	}
}

package com.board.study.web;

import com.board.study.service.NaverLoginService;
import com.board.study.vo.NaverLoginProfile;
import com.board.study.vo.NaverLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Controller
public class NaverLogin {

    @Autowired
    private NaverLoginService service;

    @GetMapping("/naverlogin")
    public String index(){
        return "/board/naverlogin";
    }

    @GetMapping("/NaverLoginCallback")
    public @ResponseBody String NaverLoginCallback(@RequestParam Map<String, String> resValue){

        // code 를 받아오면 code 를 사용하여 access_token를 발급받는다.
        final NaverLoginVo naverLoginVo = service.requestNaverLoginAcceccToken(resValue, "authorization_code");

        // access_token를 사용하여 사용자의 고유 id값을 가져온다.
        final NaverLoginProfile naverLoginProfile = service.requestNaverLoginProfile(naverLoginVo);

        return naverLoginProfile.toString();
    }
}
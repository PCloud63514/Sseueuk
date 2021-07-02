package com.pcloud.sseueuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/login")
@Controller
public class LoginController {
    @GetMapping
    public String login(Model model) {
        Map<String, String> oauth2Urls = new HashMap<>();
        oauth2Urls.put("kakao", "kakaoLoginUrl");

        model.addAttribute("urls", oauth2Urls);
        return "page/oauth-login";
    }
}

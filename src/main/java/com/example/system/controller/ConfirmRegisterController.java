package com.example.system.controller;

import com.example.system.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/confirm-register")
@RequiredArgsConstructor
public class ConfirmRegisterController {
    private final AuthenticationService authenticationService;
    @GetMapping
    public ModelAndView confirm(@RequestParam("token") String token, Model model) {
        boolean confirm = authenticationService.confirmToken(token);
        if (confirm) {
            model.addAttribute("message", "Active account successfully");
        } else {
            model.addAttribute("message", "Link expired");
        }
        return new ModelAndView("confirmation");
    }
}

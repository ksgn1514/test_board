package com.study.board.controller;

import javax.servlet.http.HttpSession;

import com.study.board.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.study.board.service.MemberService;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String login(){

        return "login";
    }

    @GetMapping("/login")
    public String openLogin(){

        return "login";
    }

    @PostMapping("/loginCheck")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model){
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());

            return "redirect:/board/list";
        } else {
            // login 실패
            model.addAttribute("loginError", "유효하지 않은 이메일 또는 비밀번호입니다.");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String openSignup(){

        return "signup";
    }
     @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
       // System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;

    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        // System.out.println("MemberController.save");
        // System.out.println("memberDTO = " + memberDTO);

        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

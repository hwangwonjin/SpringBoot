package kr.co.farmstory.controller;

import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.service.UserService;
import kr.co.farmstory.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService service;

    // 로그인
    @GetMapping("user/login")
    public String login(){
        return "user/login";
    }

    // 약관
    @GetMapping("user/terms")
    public String terms(Model model){
        TermsVO vo =service.selectTerms();
        model.addAttribute(vo);
        return "user/terms";
    }

    // 회원가입
    @GetMapping("user/register")
    public String register(){
        return "user/register";
    }

    // 회원가입
    @PostMapping("user/register")
    public String register(UserVO vo, HttpServletRequest request){

        vo.setRegip(request.getRemoteAddr());
        int result = service.insertUser(vo);

        log.info("ControllerResult :" +result);

        return "redirect:/user/login?success="+result;
    }

    // 아이디 중복확인
    @ResponseBody
    @GetMapping("user/checkUid")
    public Map<String, Integer> checkUid(@RequestParam("uid") String uid){
        log.info("uid : "+uid);
        int result = service.countByUid(uid);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    // 별명 중복확인
    @ResponseBody
    @GetMapping("user/checkNick")
    public Map<String, Integer> checkNick(@RequestParam("nick") String nick){
        int result = service.countByNick(nick);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }
}

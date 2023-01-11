package kr.co.ch07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ch07.service.User2Service;
import kr.co.ch07.vo.User2VO;

@Controller
@RequestMapping("/user2")
public class User2Controller {

	@Autowired
	private User2Service service;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<User2VO> users = service.selectUser2s();
		model.addAttribute("users", users);
		return "/user2/list";
	}
	
	
	
	
}

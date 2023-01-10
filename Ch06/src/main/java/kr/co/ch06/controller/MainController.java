package kr.co.ch06.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.ch06.vo.UserVo;

@Controller
public class MainController {

	@GetMapping(value = {"/", "/index"})
	public String index(Model model) {
		
		String tit1 = "스트링부트";
		String tit2 = "타임리프";
		
		UserVo user1 = new UserVo();
		user1.setUid("a101");
		user1.setName("홍길동");
		user1.setHp("010-1324-1001");
		user1.setAge(21);
		
		UserVo user2 =null;
		
		List<UserVo> users = new ArrayList<>();
		users.add(new UserVo("a101","김유신","010-1234-1001",21));
		users.add(new UserVo("a102","김춘추","010-1234-1002",22));
		users.add(new UserVo("a103","장보고","010-1234-1003",23));
		users.add(new UserVo("a104","강감찬","010-1234-1004",24));
		users.add(new UserVo("a105","이순신","010-1234-1005",25));
		
		model.addAttribute("tit1",tit1);
		model.addAttribute("tit2",tit2);
		model.addAttribute("user1",user1);
		model.addAttribute("user2",user2);
		model.addAttribute("users",users);
		
		return "/index";
	}
	@GetMapping(value = {"/hello"})
	public String hello() {
		return "/hello";
	}
	
	@GetMapping(value = {"/wlecome"})
	public String welcome() {
		return "/welcome";
	}
	
	@GetMapping(value = {"/greeting"})
	public String greeting() {
		return "/greeting";
	}
	
	@GetMapping("/include")
	public String include() {
		return "/include";
	}
	
	@GetMapping("/layout")
	public String layout() {
		return "/layout";
	}
}

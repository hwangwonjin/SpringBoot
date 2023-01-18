package kr.co.sboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sboard.dao.UserDAO;
import kr.co.sboard.repository.UserRepo;
import kr.co.sboard.service.UserService;
import kr.co.sboard.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SboardApplicationTests {


	void contextLoads() {
	}

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepo repo;
	
	@Test
	public void insertTest() {
		
		log.info("1");
		
		UserVO vo = UserVO.builder()
					.uid("test101")
					.pass("1234")
					.name("홍홍홍")
					.nick("홍당")
					.email("hdoejd@naver.com")
					.hp("010-9384-3821")
					.regip("0:0:0:0:0:0:0:1")
					.build();
		
		log.info("vo : " +vo);
		
		int result = dao.insertUser(vo);
		
		log.info("result : " + result);
	}
	

	public void countUid() {
		
		int result = repo.countByUid("hdigimon1");
		
		log.info("result : " + result);
	}
}

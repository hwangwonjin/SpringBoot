package kr.co.ch10.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.ch10.vo.User1VO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class User1DAOTest {

	@Autowired
	private User1DAO dao;
	
	@Test
	public void insert() {
		User1VO user = new User1VO();
		user.setUid("a101");
		user.setName("홍홍사");
		user.setHp("010-3424-3435");
		user.setAge(34);
	
		dao.insertUser1(user);
	}

	
	public void select() {
		User1VO user = dao.selectUser1("a102");
		
		log.info(user.toString());
	}


}

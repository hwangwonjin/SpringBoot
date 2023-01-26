package kr.co.farmstory.service;

import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import kr.co.farmstory.dao.UserDAO;
import kr.co.farmstory.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDAO dao;

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    public int insertUser(UserVO vo){
        vo.setPass(encoder.encode(vo.getPass1()));

        log.info("vo : " +vo);

        return dao.insertUser(vo);
    }
    public UserVO selectUser(){
        return null;
    }
    public List<UserVO> selectUsers(){
        return null;
    }
    public TermsVO selectTerms(){
        return dao.selectTerms();
    }
    public int updateUser(UserVO vo){
        return dao.updateUser(vo);
    }
    public int deleteUser(String uid){
        return dao.deleteUser(uid);
    }
    public int countByUid(String uid){
        return repo.countByUid(uid);
    }
    public int countByNick(String nick){
        return repo.countByNick(nick);
    }
}

package kr.co.farmstory.service;

import kr.co.farmstory.dao.ArticleDAO;
import kr.co.farmstory.repository.ArticleRepo;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO dao;

    @Autowired
    private ArticleRepo repo;

    public int insertArticle(ArticleVO vo) {
        //글 등록
        int result = dao.insertArticle(vo);

       /*
       // 파일 업로드
        FileVO fvo = FileUpload(vo);

        if(fvo != null){
            // 파일 등록
            dao.insertFile(fvo);
        }
        */
        return result;
    }
}
/*
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public FileVO FileUpload(ArticleVO vo) {
        MultipartFile file = vo.getFname();
        FileVO fvo = null;

        if(!file.isEmpty()){
            // 시스템경로
            String path = new File(uploadPath).getAbsolutePath();

            // 새 파일명 생성
            String oName = file.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String nName = UUID.randomUUID().toString()+ext;

            // 파일 저장
            try {
                file.transferTo(new File(path, nName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            fvo = FileVO.builder()
                    .parent(vo.getNo())
                    .oriName(oName)
                    .newName(nName)
                    .build();

        }
        return fvo;
    }
}
*/
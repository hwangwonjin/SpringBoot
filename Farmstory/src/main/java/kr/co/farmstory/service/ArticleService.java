package kr.co.farmstory.service;

import kr.co.farmstory.dao.ArticleDAO;
import kr.co.farmstory.repository.ArticleRepo;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO dao;

    @Autowired
    private ArticleRepo repo;

    // 글 작성
    @Transactional
    public int insertArticle(ArticleVO vo) {
        //글 등록
        int result = dao.insertArticle(vo);


       // 파일 업로드
        FileVO fvo = FileUpload(vo);

        if(fvo != null){
            // 파일 등록
            dao.insertFile(fvo);
        }

        return result;
    }

    // 글 목록
    public List<ArticleVO> selectArticles(int start, String cate){
        return dao.selectArticles(start, cate);
    }

    // 작성 글
    public ArticleVO selectArticle(int no){

        return dao.selectArticle(no);
    }

    // 글 수정
    public int updateArticle(ArticleVO vo){

        int result = dao.updateArticle(vo);


        return result;
    }

    // 글 삭제
    public int deleteArticle(int no){
        return dao.deleteArticle(no);
    }


    // 파일 업로드
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

    // 페이지 시작 값
    public int getLimitStart(int currentPage){
        return (currentPage - 1) * 10;
    }

    // 현재 페이지
    public int getCurrentPage(String pg){
        int currentPage = 1;

        if(pg != null){
            currentPage = Integer.parseInt(pg);
        }
        return currentPage;
    }

    // 전체 게시물 갯수
     public long getTotalCount(String cate){
        return dao.selectCountTotal(cate);
     }

     // 마지막 페이지 번호
    public int getLastPageNum(long total){
        int lastPage = 0;

        if(total % 10 == 0){
            lastPage = (int) (total/10);
        }else {
            lastPage = (int) (total/10) + 1;
        }
        return lastPage;
    }
    // 페이지 시작 번호
     public int getPageStartNum(long total, int start){
        return (int) (total - start);
     }

     // 페이지 그룹
    public int[] getPageGroup(int currentPage, int lastPage){
        int groupCurrent = (int) Math.ceil(currentPage / 10.0);
        int groupStart = (groupCurrent - 1) * 10 + 1;
        int groupEnd = groupCurrent * 10;

        if(groupEnd > lastPage){
            groupEnd = lastPage;
        }

        int[] groups = {groupStart, groupEnd};

        return groups;
    }

}

package kr.co.farmstory.dao;

import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleDAO {

    public int insertArticle(ArticleVO vo);
    public int insertFile(FileVO vo);
    public FileVO selectFile(int fno);
    public ArticleVO selectArticle();
    public List<ArticleVO> selectArticles();
    public int updateArticle(ArticleVO vo);


}

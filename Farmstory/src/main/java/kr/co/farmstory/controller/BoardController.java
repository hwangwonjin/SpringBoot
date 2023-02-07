package kr.co.farmstory.controller;

import kr.co.farmstory.dao.ArticleDAO;
import kr.co.farmstory.entity.UserEntity;
import kr.co.farmstory.repository.ArticleRepo;
import kr.co.farmstory.security.MyUserDetails;
import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private ArticleService service;

    @Autowired
    private ArticleRepo repo;


    // 글 목록
    @GetMapping("board/list")
    public String list(@AuthenticationPrincipal MyUserDetails myUser, Model model, String group, String cate, String pg){

        UserEntity user =myUser.getUser();

        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        long total = service.getTotalCount(cate);
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        List<ArticleVO> articles = service.selectArticles(start, cate);

        model.addAttribute("user", user);
        model.addAttribute("articles", articles);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/list";

    }


    // 글 수정
    @GetMapping("board/modify")
    public String modify(int no, Model model, String group, String cate){

        ArticleVO article = service.selectArticle(no);

        model.addAttribute("article", article);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/modify";
    }

    // 글 수정
    @PostMapping("board/modify")
    public String modify(ArticleVO vo,Model model){

        log.info("here1");
        log.info("vo : " + vo);
        service.updateArticle(vo);

        return "redirect:/board/view?group="+vo.getGroup()+"&cate="+vo.getCate()+"&no="+vo.getNo();
    }

    // 글 보기
    @GetMapping("board/view")
    public String view(int no, Model model, String group, String cate){

        ArticleVO article = service.selectArticle(no);

        model.addAttribute("article", article);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/view";
    }

    // 글 쓰기
    @GetMapping("board/write")
    public String write(Model model, String group, String cate){

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);

        return "board/write";
    }

    // 글 쓰기
    @PostMapping("board/write")
    public String write(ArticleVO vo, String group, String cate){

        service.insertArticle(vo);

        return "redirect:/board/list?group="+group+"&cate="+cate;
    }

    // 글 삭제
    @GetMapping("board/delete")
    public String delete(int no, String group, String cate){

        service.deleteArticle(no);

        return "redirect:/board/list?group="+group+"&cate="+cate;
    }



}

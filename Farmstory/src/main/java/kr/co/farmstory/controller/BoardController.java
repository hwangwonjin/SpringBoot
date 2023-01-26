package kr.co.farmstory.controller;

import kr.co.farmstory.dao.ArticleDAO;
import kr.co.farmstory.repository.ArticleRepo;
import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private ArticleService service;

    @Autowired
    private ArticleRepo repo;


    @GetMapping("board/list")
    public String list(Model model, String group, String cate){

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/list";

    }



    @GetMapping("board/modify")
    public String modify(Model model, String group, String cate){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/modify";
    }

    @GetMapping("board/view")
    public String view(Model model, String group, String cate){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/view";
    }

    @GetMapping("board/write")
    public String write(Model model, String group, String cate){

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);

        return "board/write";
    }

    @PostMapping("board/write")
    public String write(ArticleVO vo, String group, String cate){

        service.insertArticle(vo);

        return "redirect:/board/list?group="+group+"&cate="+cate;
    }

}

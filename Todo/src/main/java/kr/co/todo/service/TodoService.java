package kr.co.todo.service;

import kr.co.todo.dao.TodoDAO;
import kr.co.todo.vo.TodoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoDAO dao;

    // 리스트 insert
    public int insertTodoList(TodoVO vo){
        return dao.insertTodoList(vo);
    }

    // 리스트 조회하기
    public Map<Integer, List<TodoVO>> selectTodoList(){
        List<TodoVO> list =dao.selectTodoList();
        return list.stream().collect(Collectors.groupingBy(TodoVO::getStatus));
    }

    // 리스트 업데이트
    public int updateTodoList(Map<String, String> data){
        return dao.updateTodoList(data);
    }

    // 리스트 삭제
    public int deleteTodoList(TodoVO vo){
        return dao.deleteTodoList(vo);
    }
}

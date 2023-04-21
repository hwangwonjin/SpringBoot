package kr.co.todo.dao;

import kr.co.todo.vo.TodoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TodoDAO {

    // insert
    public int insertTodoList(TodoVO vo);

    // 리스트 불러오기
    public List<TodoVO> selectTodoList();

    // 리스트 업데이트
    public int updateTodoList(Map<String, String> data);

    // 리스트 삭제
    public int deleteTodoList(TodoVO vo);
}

package kr.ed.haebeop.persistence;

import kr.ed.haebeop.domain.Board;
import kr.ed.haebeop.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<Board> boardList(Page page) throws Exception;
    public Board boardDetail(int bno) throws Exception;
    public void boardInsert(Board domain) throws Exception;
    public void boardDelete(int bno) throws Exception;
    public void boardEdit(Board domain) throws Exception;
    public void visitCount(int bno) throws Exception;
    public int totalCount(Page page) throws Exception;
}

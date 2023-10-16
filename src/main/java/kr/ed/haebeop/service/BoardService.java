package kr.ed.haebeop.service;

import kr.ed.haebeop.domain.Board;

import java.util.List;

public interface BoardService {
    public List<Board> boardList() throws Exception;
    public Board boardDetail(int bno) throws Exception;
    public void boardInsert(Board dto) throws Exception;
    public void boardDelete(int bno) throws Exception;
    public void boardEdit(Board dto) throws Exception;
}

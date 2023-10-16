package kr.ed.haebeop.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ed.haebeop.repository.BoardRepository;
import kr.ed.haebeop.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Board> boardList() throws Exception {
        return boardRepository.boardList();
    }

    @Override
    public Board boardDetail(int seq) throws Exception {
        return boardRepository.boardDetail(seq);
    }

    @Override
    public void boardInsert(Board dto) throws Exception {
        boardRepository.boardInsert(dto);
    }

    @Override
    public void boardDelete(int seq) throws Exception {
        boardRepository.boardDelete(seq);
    }

    @Override
    public void boardEdit(Board dto) throws Exception {
        boardRepository.boardEdit(dto);
    }
}
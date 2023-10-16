package kr.ed.haebeop.repository;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ed.haebeop.domain.Board;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Board> boardList() throws Exception {
        return sqlSession.selectList("board.boardList");
    }

    @Override
    public Board boardDetail(int seq) throws Exception {
        sqlSession.update("board.countUp", seq);
        return sqlSession.selectOne("board.boardDetail", seq);
    }

    @Override
    public void boardInsert(Board dto) throws Exception {
        sqlSession.insert("board.boardInsert", dto);
    }

    @Override
    public void boardDelete(int seq) throws Exception {
        sqlSession.delete("board.boardDelete", seq);
    }

    @Override
    public void boardEdit(Board dto) throws Exception {
        sqlSession.update("board.boardEdit", dto);
    }

    @Override
    public int addBoard(Board board) {
        return 0;
    }
}
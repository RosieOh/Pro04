package kr.ed.haebeop.repository;

import kr.ed.haebeop.domain.Qna;
import kr.ed.haebeop.domain.QnaComment;
import kr.ed.haebeop.util.Page;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnaRepositoryImpl implements QnaRepository {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public List<Qna> QnaList(Page page) throws Exception {
        return sqlSession.selectList("qna.qnaList", page);
    }

    @Override
    public Qna qnaDetail(int qno) throws Exception {
        sqlSession.update("qna.countUp", qno);
        return sqlSession.selectOne("qna.qnaDetail", qno);
    }

    @Override
    public void qnaInsert(Qna domain) throws Exception {
        sqlSession.insert("qna.qnaInsert", domain);
        sqlSession.update("qna.qnaInsertUpdate");
    }

    @Override
    public void qnaDelete(int qno) throws Exception {
        sqlSession.delete("qna.qnaDelete", qno);
    }

    @Override
    public void qnaEdit(Qna domain) throws Exception {
        sqlSession.update("qna.qnaEdit", domain);
    }

    @Override
    public int totalCount(Page page) throws Exception {
        return sqlSession.selectOne("qna.totalCount", page);
    }

    @Override
    public List<Qna> qnaSelectBest() throws Exception {
        return sqlSession.selectOne("qna.qnaSelectBest");
    }

    @Override
    public List<Qna> qnaSelectVisit() throws Exception {
        return sqlSession.selectList("qna.qnaSelectVisit");
    }

    @Override
    public List<QnaComment> qnaCommentList(int qno) throws Exception {
        return sqlSession.selectList("qna.qnaCommentList", qno);
    }

    @Override
    public void qnaCommentInsert(QnaComment domain) throws Exception {
        sqlSession.insert("qna.qnaCommentInsert", domain);
    }

    @Override
    public void qnaCommentDelete(int cno) throws Exception {
        sqlSession.delete("qna.qnaCommentDelete", cno);
    }

    @Override
    public List<Qna> qnaCommentCount() throws Exception {
        return sqlSession.selectList("qna.qnaCommentCount");
    }
}

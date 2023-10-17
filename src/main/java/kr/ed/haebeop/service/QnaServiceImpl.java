package kr.ed.haebeop.service;

import kr.ed.haebeop.domain.Qna;
import kr.ed.haebeop.domain.QnaComment;
import kr.ed.haebeop.repository.QnaRepository;
import kr.ed.haebeop.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaServiceImpl implements QnaService{

    @Autowired
    private QnaRepository qnaRepository;

    @Override
    public List<Qna> QnaList(Page page) throws Exception {
        return qnaRepository.QnaList(page);
    }

    @Override
    public Qna qnaDetail(int qno) throws Exception {
        return qnaRepository.qnaDetail(qno);
    }

    @Override
    public void qnaInsert(Qna domain) throws Exception {
        qnaRepository.qnaInsert(domain);
    }

    @Override
    public void qnaDelete(Qna domain) throws Exception {
        qnaRepository.qnaDelete(domain);
    }

    @Override
    public void qnaEdit(Qna domain) throws Exception {
        qnaRepository.qnaEdit(domain);
    }

    @Override
    public int totalCount(Page page) throws Exception {
        return qnaRepository.totalCount(page);
    }

    @Override
    public List<Qna> qnaSelectBest() throws Exception {
        return qnaRepository.qnaSelectBest();
    }

    @Override
    public List<Qna> qnaSelectVisit() throws Exception {
        return qnaRepository.qnaSelectVisit();
    }

    @Override
    public List<QnaComment> qnaCommentList(int qno) throws Exception {
        return qnaRepository.qnaCommentList(qno);
    }

    @Override
    public void qnaCommentInsert(QnaComment domain) throws Exception {
        qnaRepository.qnaCommentInsert(domain);
    }

    @Override
    public void qnaCommentDelete(int cno) throws Exception {
        qnaRepository.qnaCommentDelete(cno);
    }

    @Override
    public List<Qna> qnaCommentCount() throws Exception {
        return qnaRepository.qnaCommentCount();
    }
}

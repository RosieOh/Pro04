package kr.ed.haebeop.repository;

import kr.ed.haebeop.domain.Free;
import kr.ed.haebeop.domain.FreeComment;
import kr.ed.haebeop.domain.Record;
import kr.ed.haebeop.util.Page;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FreeRepositoryImpl implements FreeRepository {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Free> freeList(Page page) throws Exception {
        return sqlSession.selectList("free.freeList", page);
    }

    @Transactional
    @Override
    public Free freeDetail(int fno) throws Exception {
        sqlSession.update("free.hitsUp", fno);
        return sqlSession.selectOne("free.freeDetail", fno);
    }

    @Override
    public void hitsDown(int fno) throws Exception {
        sqlSession.update("free.hitsDown", fno);
    }

    @Override
    public void freeInsert(Free domain) throws Exception {
        sqlSession.insert("free.freeInsert", domain);
    }

    @Override
    public void freeDelete(int bno) throws Exception {
        sqlSession.delete("free.freeDelete", bno);
    }

    @Override
    public void freeEdit(Free dto) throws Exception {
        sqlSession.update("free.freeEdit", dto);
    }

    @Override
    public int totalCount(Page page) throws Exception {
        return sqlSession.selectOne("free.totalCount", page);
    }

    @Override
    public List<Free> freeBestList() throws Exception {
        return sqlSession.selectList("free.freeBestRecList");
    }

    @Override
    public List<Free> freeBestCommentList() throws Exception {
        return sqlSession.selectList("free.freeBestCmtList");
    }

    @Override
    public List<FreeComment> freeCommentList() throws Exception {
        return sqlSession.selectList("free.freeCommentList");
    }

    @Override
    public void freeCommentInsert(FreeComment domain) throws Exception {
        sqlSession.insert("free.commentInsert", domain);
    }

    @Override
    public void freeCommentDelete(int cno) throws Exception {
        sqlSession.delete("free.commentDelete", cno);
    }

    @Override
    public List<Free> freeCommentCount() throws Exception {
        return sqlSession.selectList("free.commentCount");
    }

    @Override
    public Record findRecord(int fno, String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("fno", fno);
        map.put("id", id);
        return sqlSession.selectOne("free.findRecord",map);
    }

    @Override
    public Record memberFindRecord(Record reco) throws Exception {
        return sqlSession.selectOne("free.memberFindRecord", reco);
    }

    @Override
    public int insertRecord(Record reco) throws Exception {
        return sqlSession.insert("free.insertReco", reco);
    }

    @Override
    public int deleteRecord(Record reco) throws Exception {
        return sqlSession.delete("free.deleteRecord", reco);
    }

    @Override
    public void increaseRecord(Record reco) throws Exception {
        sqlSession.update("free.increaseRec", reco);
    }

    @Override
    public void decreaseRecord(Record reco) throws Exception {
        sqlSession.update("free.decreaseRec", reco);
    }


}
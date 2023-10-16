package kr.ed.haebeop.repository;

import kr.ed.haebeop.domain.Free;
import kr.ed.haebeop.domain.FreeComment;
import kr.ed.haebeop.domain.Record;
import kr.ed.haebeop.util.Page;

import java.util.List;

public interface FreeRepository {
    public List<Free> freeList(Page page) throws Exception;

    public Free freeDetail(int fno) throws Exception;

    public void hitsDown(int fno) throws Exception;

    public void freeInsert(Free domain) throws Exception;

    public void freeDelete(int fno) throws Exception;

    public void freeEdit(Free domain) throws Exception;

    public int totalCount(Page page) throws Exception;

    public List<Free> freeBestList() throws Exception;

    public List<Free> freeBestCommentList() throws Exception;

    public List<FreeComment> freeCommentList() throws Exception;

    public void freeCommentInsert(FreeComment domain) throws Exception;

    public void freeCommentDelete(int cno) throws Exception;

    public List<Free> freeCommentCount() throws Exception;

    public Record findRecord(int fno, String id) throws Exception;

    public Record memberFindRecord(Record record) throws Exception;

    public int insertRecord(Record record) throws Exception;

    public int deleteRecord(Record record) throws Exception;

    public void increaseRecord(Record record) throws Exception;

    public void decreaseRecord(Record record) throws Exception;

}

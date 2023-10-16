package kr.ed.haebeop.service;

import kr.ed.haebeop.domain.Free;
import kr.ed.haebeop.domain.FreeComment;
import kr.ed.haebeop.domain.Record;
import kr.ed.haebeop.repository.FreeRepository;
import kr.ed.haebeop.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeServiceImpl implements FreeService {

    @Autowired
    private FreeRepository freeRepository;

    @Override
    public List<Free> freeList(Page page) throws Exception {
        return freeRepository.freeList(page);
    }

    @Override
    public Free freeDetail(int fno) throws Exception {
        return freeRepository.freeDetail(fno);
    }

    @Override
    public void freeInsert(Free domain) throws Exception {
        freeRepository.freeInsert(domain);
    }

    @Override
    public void freeDelete(int fno) throws Exception {
        freeRepository.freeDelete(fno);
    }

    @Override
    public void hitsDown(int fno) throws Exception {
        freeRepository.hitsDown(fno);
    }

    @Override
    public void freeEdit(Free domain) throws Exception {
        freeRepository.freeEdit(domain);
    }

    @Override
    public int totalCount(Page page) throws Exception {
        return freeRepository.totalCount(page);
    }

    @Override
    public List<Free> freeBestList() throws Exception {
        return freeRepository.freeBestList();
    }

    @Override
    public List<Free> freeBestCommentList() throws Exception {
        return freeRepository.freeBestCommentList();
    }

    @Override
    public List<FreeComment> freeCommentList() throws Exception {
        return freeRepository.freeCommentList();
    }

    @Override
    public void freeCommentInsert(FreeComment domain) throws Exception {
        freeRepository.freeCommentInsert(domain);
    }

    @Override
    public void freeCommentDelete(int cno) throws Exception {
        freeRepository.freeCommentDelete(cno);
    }

    @Override
    public List<Free> freeCommentCount() throws Exception {
        // 댓글 수 불러오기 기능을 구현합니다.
        return freeRepository.freeCommentCount();
    }

    @Override
    public Record findRecord(int fno, String id) throws Exception {
        return freeRepository.findRecord(fno, id);
    }

    @Override
    public int insertRecord(Record reco) throws Exception {
        int result = 0;
        // 추천이 이미 있는지 확인하는 코드
        Record find = freeRepository.memberFindRecord(reco);

        // find가 null이면 추천이 없는 상태이므로 정보 저장
        // find가 null이 아니면 추천이 있는 상태이므로 정보 삭제
        System.out.println("find:" + find);

        if (find == null) {
            result = freeRepository.insertRecord(reco);
            freeRepository.increaseRecord(reco);
            //System.out.printf("추천 수 증가 확인 : %d\n", freeDAO.freeDetail(reco.getBno()).getRec());
        } else {
            result = freeRepository.deleteRecord(reco) * -1;
            freeRepository.decreaseRecord(reco);
            //System.out.printf("추천 수 감소 확인 : %d\n", freeDAO.freeDetail(reco.getBno()).getRec());
        }
        return result;
    }
}

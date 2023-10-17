package kr.ed.haebeop.persistence;

import kr.ed.haebeop.domain.Review;
import kr.ed.haebeop.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    public List<Review> reviewList(Page page) throws Exception;
    public Review reviewDetail(int rno) throws Exception;
    public void reviewInsert(Review rno) throws Exception;
    public void reviewDelete(int rno) throws Exception;
    public void reviewEdit(Review domain) throws Exception;
    public int totalCount(Page page) throws Exception;

}

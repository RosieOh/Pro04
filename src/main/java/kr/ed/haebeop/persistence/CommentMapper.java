package kr.ed.haebeop.persistence;

import kr.ed.haebeop.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public List<Comment> commentList(int par) throws Exception;
    public void commentInsert(Comment domain) throws Exception;
    public void commentDelete(int cno) throws Exception;

}

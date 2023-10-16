package kr.ed.haebeop.persistence;

import kr.ed.haebeop.domain.Free;
import kr.ed.haebeop.domain.FreeComment;
import kr.ed.haebeop.util.Page;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FreeMapper {

    @Select("select * from free")
    public List<Free> freeList(Page page);

    @Select("select * from free where fno = #{fno}")
    public Free freeDetail(int fno);

    @Select("select count(*) from free")
    public int totalCount(Page page);

    @Insert("insert into free values (default, #{title}, #{content}, default, 0, #{id}, 0)")
    public void freeInsert(Free free);

    @Delete("delete from free where fno = #{fno}")
    public void freeDelete(int fno);

    @Update("update free set title = #{title}, content = #{content} where fno = #{fno}")
    public void freeEdit(Free free);

    @Update("update free set visited = visited + 1 where fno = #{fno}")
    public void hitsUp(int fno);

    @Update("update free set visited = visited - 1 where fno = #{fno}")
    public void hitsDown(int fno);

    @Select("select * from free order by rec desc limit 5")
    public List<Free> freeBestList();

    @Select("SELECT f.fno, f.title, COUNT(c.fno) AS comment_count FROM free f " +
            "JOIN free_comment c ON f.fno = c.fno " +
            "GROUP BY f.fno, f.title, f.content " +
            "ORDER BY comment_count DESC LIMIT 5")
    public List<Free> freeBestCommentList();

    @Select("select * from record where fno=#{fno} and id=#{id}")
    public Record findRecord(int fno, String id);

    @Select("select * from record where bno=#{bno} and id=#{id}")
    public Record memberFindRecord(int bno, String id);

    @Insert("insert into record(fno, id, flag) values(#{fno}, #{id}, 1)")
    public void insertRecord(int fno, String id);

    @Delete("delete from record where fno=#{fno} and id=#{id}")
    public void deleteRecord(int fno, String id);

    @Update("update free set rec=rec+1 where fno=#{fno}")
    public void increaseRecord(int fno);

    @Update("update free set rec=rec-1 where fno=#{fno}")
    public void decreaseRecord(int fno);

    @Select("select * from FreeComment where fno=#{fno}")
    public List<Free> freeCommentList();

    @Insert("INSERT INTO FreeComment (bno, author, content) VALUES (#{bno}, #{author}, #{content})")
    public void freeCommentInsert(FreeComment freeComment);

    void freeCommentDelete(FreeComment domain);

    List<Free> freeCommentCount();
}

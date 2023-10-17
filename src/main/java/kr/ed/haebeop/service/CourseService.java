package kr.ed.haebeop.service;

import kr.ed.haebeop.domain.Course;
import kr.ed.haebeop.domain.Enroll;
import kr.ed.haebeop.domain.Member;
import kr.ed.haebeop.util.Page;

import java.util.List;

public interface CourseService {
    public List<Course> courseList(Page page) throws Exception;
    public Course getCourse(int cno) throws Exception;
    public void courseInsert(Course domain) throws Exception;
    public void courseDelete(int cno) throws Exception;
    public void courseUpdate(Course domain) throws Exception;
    public int courseCount(Page page) throws Exception;
    public void enrollInsert(Enroll domain2) throws Exception;
    public void updateStudentNumber(int cno) throws Exception;
    public List<Enroll> enrollList(Enroll domain2) throws Exception;
    public void complete(int cno) throws Exception;
    public Member getMemberName(String id) throws Exception;
    public Enroll isEnroll(Enroll domain2) throws Exception;
}

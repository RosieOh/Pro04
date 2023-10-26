package kr.ed.haebeop.service;

import kr.ed.haebeop.domain.Course;
import kr.ed.haebeop.domain.Enroll;
import kr.ed.haebeop.domain.Member;
import kr.ed.haebeop.util.Page;

import java.util.List;

public interface CourseService {
    public List<Course> getCourseList(Page page) throws Exception;
    public List<Course> courseList() throws Exception;
    public List<Course> getNewCourses() throws Exception;
    public Course getCourse(int cno) throws Exception;
    public void insertCourse(Course course) throws Exception;
    public void updateCourse(Course course) throws Exception;
    public void deleteCourse(int cno) throws Exception;
    public int countCourse(Page page) throws Exception;
    public void insertEnroll(Enroll enroll) throws Exception;
    public void updateStudentNumber(int cno) throws Exception;
    public List<Enroll> getEnrollList(Enroll enroll) throws Exception;
    public void complete(int eno) throws Exception;
    public Member getMemberName(String id) throws Exception;
    public Enroll isEnroll(Enroll enroll) throws Exception;
    public List<Enroll> enrollList(Page page) throws Exception;
    public void enrollDelete(int eno) throws Exception;
    public int countEnroll(Page page) throws Exception;
    public void updateMemberPoint(Member member) throws Exception;
    public void rollbackStudentNum(int cno) throws Exception;
    public void cancel(int eno) throws Exception;
    public List<Enroll> cancelList(Page page) throws Exception;
    public int countCancel(Page page) throws Exception;
}
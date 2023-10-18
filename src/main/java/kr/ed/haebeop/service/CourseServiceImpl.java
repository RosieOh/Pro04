package kr.ed.haebeop.service;

import kr.ed.haebeop.domain.Course;
import kr.ed.haebeop.domain.Enroll;
import kr.ed.haebeop.domain.Member;
import kr.ed.haebeop.persistence.CourseMapper;
import kr.ed.haebeop.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public List<Course> courseList(Page page) throws Exception {
        return courseMapper.courseList(page);
    }

    @Override
    public Course getCourse(int cno) throws Exception {
        return courseMapper.getCourse(cno);
    }

    @Override
    public void courseInsert(Course domain) throws Exception {
        courseMapper.courseInsert(domain);
    }

    @Override
    public void courseDelete(int cno) throws Exception {
        courseMapper.courseDelete(cno);
    }

    @Override
    public void courseUpdate(Course domain) throws Exception {
        courseMapper.courseUpdate(domain);
    }

    @Override
    public int courseCount(Page page) throws Exception {
        return courseMapper.courseCount(page);
    }

    @Override
    public void enrollInsert(Enroll domain2) throws Exception {
        courseMapper.enrollInsert(domain2);
    }

    @Override
    public void updateStudentNumber(int cno) throws Exception {
        courseMapper.updateStudentNumber(cno);
    }

    @Override
    public List<Enroll> enrollList(Enroll domain2) throws Exception {
        return courseMapper.enrollList(domain2);
    }

    @Override
    public void complete(int cno) throws Exception {
        courseMapper.complete(cno);
    }

    @Override
    public Member getMemberName(String id) throws Exception {
        return courseMapper.getMemberName(id);
    }

    @Override
    public Enroll isEnroll(Enroll domain2) throws Exception {
        return courseMapper.isEnroll(domain2);
    }
}

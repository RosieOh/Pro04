package kr.ed.haebeop.service;

import kr.ed.haebeop.domain.Apply;
import kr.ed.haebeop.domain.Lecture;
import kr.ed.haebeop.domain.Member;
import kr.ed.haebeop.persistence.LectureMapper;
import kr.ed.haebeop.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements LectureService{

    @Autowired
    LectureMapper lectureMapper;


    @Override
    public List<Lecture> getLectureList(Page page) throws Exception {
        return null;
    }

    @Override
    public List<Lecture> lectureList() throws Exception {
        return null;
    }

    @Override
    public List<Lecture> getNewLecture() throws Exception {
        return null;
    }

    @Override
    public void insertLecture(Lecture lecture) throws Exception {

    }

    @Override
    public void updateLecture(Lecture lecture) throws Exception {

    }

    @Override
    public void deleteLecture(Lecture lecture) throws Exception {

    }

    @Override
    public int countLecture(Lecture lecture) throws Exception {
        return 0;
    }

    @Override
    public int insertApply(Apply apply) throws Exception {
        return 0;
    }

    @Override
    public void updateStudentNumber(int lno) throws Exception {

    }

    @Override
    public List<Apply> getApplyList(Apply apply) throws Exception {
        return null;
    }

    @Override
    public void endLecture(int ano) throws Exception {

    }

    @Override
    public Member getMemberName(String id) throws Exception {
        return null;
    }

    @Override
    public Apply isApply(Apply apply) throws Exception {
        return null;
    }

    @Override
    public List<Apply> applyList(Page page) throws Exception {
        return null;
    }

    @Override
    public void deleteApply(int ano) throws Exception {

    }

    @Override
    public int countApply(Page page) throws Exception {
        return 0;
    }

    @Override
    public void updateMemberPoint(Member member) throws Exception {

    }

    @Override
    public void rollbackStudentNumber(int lno) throws Exception {

    }

    @Override
    public void cancelApply(int ano) throws Exception {

    }

    @Override
    public List<Apply> cancelApplyList(Page page) throws Exception {
        return null;
    }

    @Override
    public int countCancelApplyList(Page page) throws Exception {
        return 0;
    }
}

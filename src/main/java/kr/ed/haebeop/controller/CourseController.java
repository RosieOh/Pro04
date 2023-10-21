package kr.ed.haebeop.controller;

import kr.ed.haebeop.domain.Course;
import kr.ed.haebeop.domain.Enroll;
import kr.ed.haebeop.domain.Member;
import kr.ed.haebeop.service.CourseService;
import kr.ed.haebeop.service.MemberService;
import kr.ed.haebeop.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/course/")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private MemberService memberService;

    @Autowired
    HttpSession session;

    @GetMapping("list.do")
    public String getCourseList(HttpServletRequest request, Model model) throws Exception {
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        Page page = new Page();
        page.setSearchType(type);
        page.setSearchKeyword(keyword);
        int total = courseService.countCourse(page);

        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("curPage", curPage);

        List<Course> courseList = courseService.getCourseList(page);
        System.out.println("courseList : " + courseList);
        model.addAttribute("courseList", courseList);
        return "/course/courseList";
    }

    @RequestMapping(value = "getCourse", method = RequestMethod.GET)
    public String getCourse(@RequestParam int cno, Model model, HttpServletRequest request) throws Exception {
        Course course = courseService.getCourse(cno);
        model.addAttribute("course", course);
        Enroll enroll = new Enroll();
        if (session.getAttribute("sid") != null) {
            String id = (String) session.getAttribute("sid");
            enroll.setId(id);
            enroll.setCno(cno);
            boolean isEnroll = false;
            if (courseService.isEnroll(enroll) != null) {
                isEnroll = true;
            }
            model.addAttribute("isEnroll", isEnroll);
        }

        return "/course/getCourse";
    }

    @RequestMapping(value = "signIn", method = RequestMethod.GET)
    public String signInCourse(@RequestParam int cno, @RequestParam int book, Model model) throws Exception {
        Course course = courseService.getCourse(cno);
        String id = (String) session.getAttribute("sid");
        Member member = memberService.getMember(id);
        model.addAttribute("course", course);
        model.addAttribute("book", book);
        model.addAttribute("member", member);
        return "/course/signInCourse";
    }


    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    @Transactional
    public String insertEnrollPro(Enroll enroll, String sid, Model model, int pt) throws Exception {
        courseService.insertEnroll(enroll);
        courseService.updateStudentNumber(enroll.getCno());
        String id = (String) session.getAttribute("sid");
        Member member = new Member();
        member.setId(id);
        member.setPt(pt);
        courseService.updateMemberPoint(member);
        return "redirect:/course/mypageCourse?complete=0";
    }

    //회원의 수강 신청 정보 보기
    @RequestMapping(value = "mypageCourse", method = RequestMethod.GET)
    public String userPageCourse(Model model, HttpServletRequest request, @RequestParam int complete) throws Exception {
        String id = (String) session.getAttribute("sid");
        Enroll enroll = new Enroll();

        if (complete == 0) {
            enroll.setId(id);
            enroll.setComplete(false);
            List<Enroll> getEnrollList = courseService.getEnrollList(enroll);
            Member member = courseService.getMemberName(id);
            model.addAttribute("getEnrollList", getEnrollList);
            model.addAttribute("member", member);

            int size = courseService.getEnrollList(enroll).size();
            model.addAttribute("size", size);
            enroll.setComplete(true);
            size += courseService.getEnrollList(enroll).size();

            if (size != 0) {
                int enrollNum = (int) Math.ceil(100.0 / (double) size);
                model.addAttribute("enrollNum", enrollNum);
            }
            return "/course/mypageCourse";
        } else {
            enroll.setId(id);
            enroll.setComplete(true);
            List<Enroll> getEnrollList = courseService.getEnrollList(enroll);
            Member member = courseService.getMemberName(id);
            model.addAttribute("getEnrollList", getEnrollList);
            model.addAttribute("member", member);

            int size = courseService.getEnrollList(enroll).size();
            model.addAttribute("size", size);
            enroll.setComplete(false);
            size += courseService.getEnrollList(enroll).size();

            if (size != 0) {
                int enrollNum = (int) Math.ceil(100.0 / (double) size);
                model.addAttribute("enrollNum", enrollNum);
            }
            return "/course/completedCourse";
        }
    }

    //회원의 수강완료 버튼 누르기
    @RequestMapping(value = "complete", method = RequestMethod.POST)
    public String completePro(int eno) throws Exception {
        courseService.complete(eno);
        return "redirect:/course/mypageCourse?complete=0";
    }
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    public String cancelPro(int eno) throws Exception {
        courseService.cancel(eno);
        return "redirect:/course/mypageCourse?complete=0";
    }

    @GetMapping("insert.do")
    public String insertForm(HttpServletRequest request, Model model) {
        return "/course/courseInsert";
    }

    @PostMapping("insert.do")
    public String insertCourse(HttpServletRequest request, Model model) throws Exception {
        MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;

        MultipartFile imgFile1 = mr.getFile("imgsrc1");
        MultipartFile imgFile2 = mr.getFile("imgsrc2");
        //System.out.println(imgFile1 + " " + imgFile2);

        Course course = new Course();
        course.setCourse_name(mr.getParameter("title"));
        course.setPrice(Integer.parseInt(mr.getParameter("price")));
        course.setStart_date(mr.getParameter("start_date"));
        course.setEnd_date(mr.getParameter("end_date"));
        course.setTotal_num(Integer.parseInt(mr.getParameter("total_num")));
        course.setCurr_num(0);
        course.setTeacher_name(mr.getParameter("teacher_name"));
        course.setContent(mr.getParameter("content"));
        course.setBook_name(mr.getParameter("book_name"));
        course.setBook_price(Integer.parseInt(mr.getParameter("book_price")));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String[] timeFileNames = new String[2];

        if (!imgFile1.isEmpty()) {
            System.out.println("imgFile1.getOriginalFilename():" + imgFile1.getOriginalFilename());
            String originalFileName = imgFile1.getOriginalFilename();
            String[] names = originalFileName.split("\\.");
            String timeFileName = names[0] + "_" + sdf.format(new Date()) + "." + names[1];
            timeFileNames[0] = timeFileName;
            course.setImgsrc1(timeFileName);
        }
        if (!imgFile2.isEmpty()) {
            String originalFileName = imgFile2.getOriginalFilename();
            String[] names = originalFileName.split("\\.");
            String timeFileName = names[0] + "_" + sdf.format(new Date()) + "." + names[1];
            timeFileNames[1] = timeFileName;
            course.setImgsrc2(timeFileName);
        }
        //System.out.println("course:" + course);
        courseService.insertCourse(course);

        //String uploadDir = "C:/hansun/project04/pro04/src/main/webapp/resources/upload/";
        String uploadSev = request.getRealPath("/resources/upload/");

        try {
            if (!imgFile1.isEmpty()) {
                imgFile1.transferTo(new File(uploadSev + timeFileNames[0]));
            }
            if (!imgFile2.isEmpty()) {
                imgFile2.transferTo(new File(uploadSev + timeFileNames[1]));
            }
        } catch (IOException e) {
            e.printStackTrace(); // 오류 처리
        }
        return "redirect:list.do";
    }

    @GetMapping("delete.do")
    public String deleteCourse(HttpServletRequest request, Model model) throws Exception {
        int cno = Integer.parseInt(request.getParameter("cno"));
        courseService.deleteCourse(cno);
        return "redirect:list.do";
    }

    @GetMapping("/schedule.do")
    public String scheduleList(Model model, HttpServletRequest request) throws Exception {
        List<Course> courses = courseService.courseList();
        request.setAttribute("courses", courses);
        return "/course/scheduleList";
    }
}
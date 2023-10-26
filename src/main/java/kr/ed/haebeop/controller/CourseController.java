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

    // 강좌 목록을 가져와서 페이지에 표시하는 메서드
    @GetMapping("list.do")
    public String getCourseList(HttpServletRequest request, Model model) throws Exception {
        // 요청 파라미터에서 검색 유형(type)과 검색어(keyword)를 가져옴
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");

        // 페이지 번호를 가져오고, 기본값은 1로 설정
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        // Page 객체를 사용하여 페이징 처리를 위한 정보 설정
        Page page = new Page();
        page.setSearchType(type);
        page.setSearchKeyword(keyword);
        int total = courseService.countCourse(page);

        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        // 모델에 필요한 데이터 추가
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("curPage", curPage);

        // 강좌 목록을 가져와 모델에 추가
        List<Course> courseList = courseService.getCourseList(page);
        model.addAttribute("courseList", courseList);

        // 강좌 목록 화면을 표시
        return "/course/courseList";
    }

    // 강좌 상세 정보를 가져와 페이지에 표시하는 메서드
    @RequestMapping(value = "getCourse", method = RequestMethod.GET)
    public String getCourse(@RequestParam int cno, Model model, HttpServletRequest request) throws Exception {
        Course course = courseService.getCourse(cno);
        model.addAttribute("course", course);
        Enroll enroll = new Enroll();

        // 세션에서 사용자 아이디(sid)를 가져와서 수강신청 여부를 확인
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

        // 강좌 상세 정보 화면을 표시
        return "/course/getCourse";
    }

    // 강좌 수강 신청 화면을 표시
    @RequestMapping(value = "apply", method = RequestMethod.GET)
    public String signInCourse(@RequestParam int cno, @RequestParam int book, Model model) throws Exception {
        Course course = courseService.getCourse(cno);
        String id = (String) session.getAttribute("sid");
        Member member = memberService.getMember(id);
        model.addAttribute("course", course);
        model.addAttribute("book", book);
        model.addAttribute("member", member);
        return "/course/applyCourse";
    }

    // 강좌 수강 신청을 처리
    @RequestMapping(value = "apply", method = RequestMethod.POST)
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

    // 회원의 수강 정보 페이지 표시
    @RequestMapping(value = "mypageCourse", method = RequestMethod.GET)
    public String userPageCourse(Model model, HttpServletRequest request, @RequestParam int complete) throws Exception {
        String id = (String) session.getAttribute("sid");
        Enroll enroll = new Enroll();

        if (complete == 0) {
            // 미수료 수강 신청 정보를 가져옴
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

            // 수료 수강 신청 정보를 가져옴
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

    // 회원의 수강 신청 완료 처리
    @RequestMapping(value = "complete", method = RequestMethod.POST)
    public String completePro(int eno) throws Exception {
        courseService.complete(eno);
        return "redirect:/course/mypageCourse?complete=0";
    }

    // 회원의 수강 신청 취소 처리
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    public String cancelPro(int eno) throws Exception {
        courseService.cancel(eno);
        return "redirect:/course/mypageCourse?complete=0";
    }

    // 강좌 등록 페이지 표시
    @GetMapping("insert.do")
    public String insertForm(HttpServletRequest request, Model model) {
        return "/course/courseInsert";
    }

    // 강좌 등록 처리
    @PostMapping("insert.do")
    public String insertCourse(HttpServletRequest request, Model model) throws Exception {
        MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;

        // 강좌 정보를 받아옴
        MultipartFile imgFile1 = mr.getFile("imgsrc1");
        MultipartFile imgFile2 = mr.getFile("imgsrc2");

        Course course = new Course();
        // 강좌 정보 설정
        course.setCname(mr.getParameter("title"));
        course.setPrice(Integer.parseInt(mr.getParameter("price")));
        course.setSdate(mr.getParameter("sdate"));
        course.setEdate(mr.getParameter("edate"));
        course.setTot_num(Integer.parseInt(mr.getParameter("tot_num")));
        course.setCur_num(0);
        course.setT_name(mr.getParameter("t_name"));
        course.setContent(mr.getParameter("content"));
        course.setBname(mr.getParameter("b_name"));
        course.setBprice(Integer.parseInt(mr.getParameter("b_price")));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String[] timeFileNames = new String[2];

        if (!imgFile1.isEmpty()) {
            // 이미지 파일 업로드 처리
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
        courseService.insertCourse(course);

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

    // 강좌 삭제 처리
    @GetMapping("delete.do")
    public String deleteCourse(HttpServletRequest request, Model model) throws Exception {
        int cno = Integer.parseInt(request.getParameter("cno"));
        courseService.deleteCourse(cno);
        return "redirect:list.do";
    }

    // 강좌 일정 목록 표시
    @GetMapping("/schedule.do")
    public String scheduleList(Model model, HttpServletRequest request) throws Exception {
        List<Course> courses = courseService.courseList();
        request.setAttribute("courses", courses);
        return "/course/scheduleList";
    }
}

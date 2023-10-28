package kr.ed.haebeop.controller;

import kr.ed.haebeop.domain.Apply;
import kr.ed.haebeop.domain.Lecture;
import kr.ed.haebeop.domain.Member;
import kr.ed.haebeop.domain.Teacher;
import kr.ed.haebeop.service.LectureService;
import kr.ed.haebeop.service.MemberService;
import kr.ed.haebeop.service.TeacherService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LectureController {
    @Autowired
    private LectureService lectureService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    HttpSession session;

    //------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("list.do")
    public String getLectureList(HttpServletRequest request, Model model) throws Exception {
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        String sort = request.getParameter("sort");

        Page page = new Page();
        page.setSearchType(type);
        page.setSearchKeyword(keyword);
        int total = lectureService.countLecture(page);

        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("curPage", curPage);

        List<Lecture> lectureList = lectureService.getLectureList(page);
        if("asc".equals(sort)) {
            lectureList = lectureService.getLectureAsc(page);
        } else if("desc".equals(sort)) {
            lectureList = lectureService.getLectureDesc(page);
        }

        Map<Integer, Boolean> lectureMapping = new HashMap<>();
        for (Lecture lecture : lectureList) {
            int lectureNumber = lecture.getLec_number();
            int totalLectureNumber = lecture.getTot_number();
            boolean DeadLineLecture = lectureNumber >= totalLectureNumber * 0.9 && lectureNumber < totalLectureNumber;
            lectureMapping.put(lecture.getLno(), DeadLineLecture);
        }

        model.addAttribute("lecutureList", lectureList);
        model.addAttribute("lecuremapping", lectureMapping)
        return "/lecture/lectureList";
    }

    @GetMapping("getLecture")
    public String getLecture(@RequestParam int lno, Model model, HttpServletRequest request) throws Exception {
        Lecture lecture = lectureService.getLecture(lno);
        model.addAttribute("lecture", lecture);
        Apply apply = new Apply();
        if (session.getAttribute("sid") != null) {
            String id = (String) session.getAttribute("sid");
            apply.setId(id);
            apply.setLno(lno);
            boolean doApply = false;
            if (lectureService.doApply(apply) != null) {
                doApply = true;
            }
            model.addAttribute("doApply", doApply);
        }
        return "/lecture/getLecture";
    }

    @GetMapping("apply")
    public String signInCourse(@RequestParam int lno, @RequestParam int book, Model model) throws Exception {
        Lecture lecture = lectureService.getLecture(lno);
        String id = (String) session.getAttribute("sid");
        Member member = memberService.getMember(id);
        model.addAttribute("lecture", lecture);
        model.addAttribute("book", book);
        model.addAttribute("member", member);
        return "/lecture/applyLecture";
    }

    @PostMapping("apply")
    @Transactional
    public String insertApply(HttpServletRequest request, Model model) throws Exception {
        Apply apply = new Apply();
        apply.setLno(Integer.parseInt(request.getParameter("lno")));
        apply.setId(request.getParameter("id"));
        apply.setA_price(Integer.parseInt(request.getParameter("a_price")));
        apply.setBname("bname");
        apply.setApply_mn(Integer.parseInt(request.getParameter("apply_mn")));
        apply.setBook(Boolean.parseBoolean(request.getParameter("book")));

        int a_price = Integer.parseInt(request.getParameter("a_price"));
        int pt = Integer.parseInt(request.getParameter("pt"));
        lectureService.insertApply(apply);
        lectureService.updateStudentNumber(apply.getAno());
        String id = (String) session.getAttribute("sid");
        Member member = new Member();
        member.setId(id);
        member.setPt(pt);
        lectureService.updateMemberPoint(member);
        return "redirect:/lecture/mypageLecture?complete=0";
    }

    @GetMapping("mypageLecture")
    public String memberApplyLecture(Model model, HttpServletRequest request, int complete) throws Exception {
        String id = (String) session.getAttribute("sid");
        Apply apply = new Apply();

        if (complete == 0) {
            apply.setId(id);
            apply.setComplete(false);
            List<Apply> getApplyList = lectureService.getApplyList(apply);
            Member member = lectureService.getMemberName(id);
            model.addAttribute("getApplyList", getApplyList);
            model.addAttribute("member", member);

            int size = lectureService.getApplyList(apply).size();
            model.addAttribute("size", size);
            apply.setComplete(true);
            size += lectureService.getApplyList(apply).size();

            if(size != 0) {
                int applyNumber = (int) Math.ceil(100.0 / (double) size);
                model.addAttribute("applyNumber", applyNumber);
            }
            return "/lecture/mypageLecture";
        } else {
            apply.setId(id);
            apply.setComplete(true);
            List<Apply> getApplyList = lectureService.getApplyList(apply);
            Member member = lectureService.getMemberName(id);
            model.addAttribute("getApplyList", getApplyList);
            model.addAttribute("member", member);
            int size = lectureService.getApplyList(apply).size();
            model.addAttribute("size", size);
            apply.setComplete(false);
            size += lectureService.getApplyList(apply).size();

            if (size != 0) {
                int applyNumber = (int) Math.ceil(100.0 / (double) size);
                model.addAttribute("applyNumber", applyNumber);
            }
            return "/lecture/completLecture";
        }
    }

    @PostMapping("complete")
    public String completeLecture(int ano) throws Exception {
        lectureService.endLecture(ano);
        return "redirect:/lecture/mypageLecture?endLecture=0";
    }

    @PostMapping("cancelLecture")
    public String canceLecture(int ano) throws Exception {
        lectureService.cancelApply(ano);
        return "redirect:/lecture/mypageLecture?completeLecture=0";
    }

    @GetMapping("insert.do")
    public String insertForm(HttpServletRequest request, Model model) throws Exception {
        List<Teacher> teacherList = teacherService.teacherList();
        model.addAttribute("teacherList", teacherList);
        return "/lecture/lectureInsert";
    }

    // 강사님 이미지 3개 추가요
    @PostMapping("insert.do")
    public String insertLecture(HttpServletRequest request, Model model) throws Exception {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile imgFile1 = multipartHttpServletRequest.getFile("imgsrc1");
        MultipartFile imgFile2 = multipartHttpServletRequest.getFile("imgsrc2");
        MultipartFile imgFile3 = multipartHttpServletRequest.getFile("imgsrc3");

        Lecture lecture = new Lecture();
        lecture.setLname(multipartHttpServletRequest.getParameter("title"));
        lecture.setPrice(Integer.parseInt(multipartHttpServletRequest.getParameter("price")));
        lecture.setSdate(multipartHttpServletRequest.getParameter("sdate"));
        lecture.setEdate(multipartHttpServletRequest.getParameter("edate"));
        lecture.setTot_number(Integer.parseInt(multipartHttpServletRequest.getParameter("tot_number")));
        lecture.setLec_number(0);
        lecture.setT_name(multipartHttpServletRequest.getParameter("t_name"));
        lecture.setContent(multipartHttpServletRequest.getParameter("content"));
        lecture.setBname(multipartHttpServletRequest.getParameter("bname"));
        lecture.setBprice(Integer.parseInt(multipartHttpServletRequest.getParameter("bprice")));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyMMddHHmmssSSS");
        String[] timeFileNames = new String[2];

        if(!imgFile1.isEmpty()) {   // 문자열의 길이가 0인 경우에, true를 리턴
            System.out.println("imgFile1.getOriginFileName():" + imgFile1.getOriginalFilename());
            String realFileName = imgFile1.getOriginalFilename();
            String[] names = realFileName.split("\\.");
            String timeFileName = names[0] + "_" + simpleDateFormat.format(new Date()) +"," + names[1];
            timeFileNames[0] = timeFileName;
            lecture.setImgsrc1(timeFileName);
        }

        if(!imgFile2.isEmpty()) {   // 문자열의 길이가 0인 경우에, true를 리턴
            System.out.println("imgFile2.getOriginFileName():" + imgFile2.getOriginalFilename());
            String realFileName = imgFile2.getOriginalFilename();
            String[] names = realFileName.split("\\.");
            String timeFileName = names[0] + "_" + simpleDateFormat.format(new Date()) +"," + names[1];
            timeFileNames[1] = timeFileName;
            lecture.setImgsrc2(timeFileName);
        }

        if(!imgFile3.isEmpty()) {   // 문자열의 길이가 0인 경우에, true를 리턴
            System.out.println("imgFile3.getOriginFileName():" + imgFile1.getOriginalFilename());
            String realFileName = imgFile3.getOriginalFilename();
            String[] names = realFileName.split("\\.");
            String timeFileName = names[0] + "_" + simpleDateFormat.format(new Date()) +"," + names[1];
            timeFileNames[1] = timeFileName;
            lecture.setImgsrc3(timeFileName);
        }

        lectureService.insertLecture(lecture);

        String uploadServerComputer = request.getRealPath("/resources/upload/");
        try {
            if (!imgFile1.isEmpty()) {
                imgFile1.transferTo(new File(uploadServerComputer + timeFileNames[0]));
            }
            if (!imgFile2.isEmpty()) {
                imgFile2.transferTo(new File(uploadServerComputer + timeFileNames[1]));
            }
            if (!imgFile3.isEmpty()) {
                imgFile3.transferTo(new File(uploadServerComputer + timeFileNames[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:list.do";
    }

    @GetMapping("delete.do")
    public String deleteLecture(HttpServletRequest request, Model model) throws Exception {
        int lno = Integer.parseInt(request.getParameter("lno"));
        lectureService.deleteLecture(lno);
        return "redirect:list.do";
    }

    @GetMapping("schedule.do")
    public String lectureScheduleList(HttpServletRequest request, Model model) throws Exception {
        List<Lecture> lecture = lectureService.lectureList();
        request.setAttribute("lecture", lecture);
        return "/lecture/lectureScheduleList";
    }

    @PostMapping("list.do")
    public String applyFilters(HttpServletRequest request, Model model) throws Exception {
        String excludeFullParameter = request.getParameter("excludeFull");
        String excludeFinishedParameter = request.getParameter("excludeFinished");

        Boolean excludeFull = Boolean.parseBoolean(excludeFullParameter);
        Boolean excludeFinished = Boolean.parseBoolean(excludeFinishedParameter);
        
    }
}

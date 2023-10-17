package kr.ed.haebeop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private int cno;
    private String course_name;
    private int price;
    private String start_date;
    private String end_date;
    private int total_num;
    private int curr_num;
    private String teacher_name;
    private String content;
    private String book_name;
    private int book_price;
    private String imgsrc1;
    private String imgsrc2;
}

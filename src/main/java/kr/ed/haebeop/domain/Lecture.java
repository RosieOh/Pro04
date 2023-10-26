package kr.ed.haebeop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lecture {
    private int lno;
    private String cname;
    private int price;
    private String sdate;       // start
    private String edate;       // end
    private int tot_num;        // 전체 숫자
    private int cur_num;
    private String t_name;      // teacher
    private String content;
    private String bname;       // book name
    private int bprice;         // book price
    private String imgsrc1;
    private String imgsrc2;
}
